/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.bc;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.function.Supplier;

import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.BCPGOutputStream;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPCompressedDataGenerator;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPLiteralDataGenerator;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSignature;
import org.bouncycastle.openpgp.PGPSignatureGenerator;
import org.bouncycastle.openpgp.PGPSignatureSubpacketGenerator;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor;
import org.bouncycastle.openpgp.operator.PGPDigestCalculatorProvider;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPDigestCalculatorProviderBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyDecryptorBuilder;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.internal.bc.i18n.BcMessages;

final class BcEncodedStream {

	private final LicensedProduct product;
	private final InputStream input;
	private final OutputStream output;

	BcEncodedStream(LicensedProduct product, InputStream input, OutputStream output) {
		this.product = product;
		this.input = input;
		this.output = output;
	}

	void produce(PGPSecretKey key, String password) throws LicensingException {
		try (//
				OutputStream armored = new ArmoredOutputStream(output); //
				ClosableGenerator<PGPCompressedDataGenerator> generator = //
						new ClosableGenerator<>(//
								new PGPCompressedDataGenerator(PGPCompressedData.ZLIB), //
								PGPCompressedDataGenerator::close);
				BCPGOutputStream stream = new BCPGOutputStream(generator.get().open(armored))) {
			PGPSignatureGenerator signer = signer(key, password);
			signer.generateOnePassVersion(false).encode(stream);
			updateSignatureGenerator(signer, stream);
			signer.generate().encode(stream);
		} catch (IOException | PGPException e) {
			throw new LicensingException(//
					String.format(BcMessages.getString("BcStreamCodec_enconde_error"), product), //$NON-NLS-1$
					e);
		}
	}

	private PGPSignatureGenerator signer(PGPSecretKey key, String password) throws PGPException {
		PGPSignatureGenerator signer = new PGPSignatureGenerator(
				new JcaPGPContentSignerBuilder(key.getPublicKey().getAlgorithm(), PGPUtil.SHA512));
		PGPPrivateKey privateKey = privateKey(key, password);
		signer.init(PGPSignature.BINARY_DOCUMENT, privateKey);
		traceUser(key, signer);
		return signer;
	}

	private void traceUser(PGPSecretKey key, PGPSignatureGenerator signer) {
		Iterator<String> it = key.getPublicKey().getUserIDs();
		if (!it.hasNext()) {
			return;
		}
		PGPSignatureSubpacketGenerator generator = new PGPSignatureSubpacketGenerator();
		generator.setSignerUserID(false, it.next());
		signer.setHashedSubpackets(generator.generate());
	}

	private PGPPrivateKey privateKey(PGPSecretKey key, String password) throws PGPException {
		PGPDigestCalculatorProvider calcProvider = new JcaPGPDigestCalculatorProviderBuilder()
				.setProvider(BouncyCastleProvider.PROVIDER_NAME).build();
		PBESecretKeyDecryptor decryptor = new JcePBESecretKeyDecryptorBuilder(calcProvider)
				.setProvider(BouncyCastleProvider.PROVIDER_NAME).build(password.toCharArray());
		return key.extractPrivateKey(decryptor);
	}

	private void updateSignatureGenerator(PGPSignatureGenerator signer, BCPGOutputStream stream) throws IOException {
		try (ClosableGenerator<PGPLiteralDataGenerator> generator = new ClosableGenerator<>(//
				new PGPLiteralDataGenerator(), PGPLiteralDataGenerator::close);
				OutputStream literalDataStream = generator.get().open(//
						stream, //
						PGPLiteralData.BINARY, //
						"ignored", //$NON-NLS-1$
						new Date(), //
						new byte[1024])) {
			int ch;
			while ((ch = input.read()) >= 0) {
				literalDataStream.write(ch);
				signer.update((byte) ch);
			}
		}
	}

	private static final class ClosableGenerator<G> implements Closeable, Supplier<G> {
		private final G generator;
		private final Usher<G> usher;

		ClosableGenerator(G generator, Usher<G> usher) {
			this.generator = generator;
			this.usher = usher;
		}

		@Override
		public void close() throws IOException {
			usher.close(generator);
		}

		@Override
		public G get() {
			return generator;
		}

	}

	@FunctionalInterface
	private interface Usher<G> {

		void close(G generator) throws IOException;
	}
}
