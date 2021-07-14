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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.Optional;

import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.PGPOnePassSignature;
import org.bouncycastle.openpgp.PGPOnePassSignatureList;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPSignature;
import org.bouncycastle.openpgp.PGPSignatureList;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.bc.BcPGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.jcajce.JcaPGPObjectFactory;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentVerifierBuilderProvider;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.DigestExpectation;
import org.eclipse.passage.lic.internal.bc.i18n.BcMessages;

final class BcDecodedStream {

	private final LicensedProduct product;
	private final InputStream input;
	private final OutputStream output;

	BcDecodedStream(LicensedProduct product, InputStream input, OutputStream output) {
		this.product = product;
		this.input = input;
		this.output = output;
	}

	void produce(InputStream publicKeyRing, DigestExpectation digest) throws LicensingException {
		byte[] ring = ring(publicKeyRing, digest);
		try (//
				InputStream decodedInput = PGPUtil.getDecoderStream(input);
				InputStream compressed = compressedData(decodedInput).getDataStream()) {
			PGPObjectFactory factory = new JcaPGPObjectFactory(compressed);
			PGPOnePassSignature signature = signature(factory);
			try (InputStream literal = literalDataStream(factory);
					InputStream decoder = PGPUtil.getDecoderStream(new ByteArrayInputStream(ring))) {
				PGPPublicKey decodeKey = decodeKey(new BcPGPPublicKeyRingCollection(decoder), signature.getKeyID());
				signature.init(new JcaPGPContentVerifierBuilderProvider(), decodeKey);
				writeVerifiedDecodedOutput(literal, signature, factory);
			}
		} catch (Exception e) {
			throw new LicensingException( //
					String.format(BcMessages.getString("BcStreamCodec_deconde_error"), product), //$NON-NLS-1$ ,
					e);
		}
	}

	private void writeVerifiedDecodedOutput(InputStream literal, PGPOnePassSignature signature,
			PGPObjectFactory factory) throws Exception {
		ByteArrayOutputStream untrusted = new ByteArrayOutputStream();
		int ch;
		while ((ch = literal.read()) >= 0) {
			signature.update((byte) ch);
			untrusted.write(ch);
		}
		if (signature.verify(pairSignature(factory))) {
			output.write(untrusted.toByteArray()); // verified now
		}
	}

	private PGPPublicKey decodeKey(BcPGPPublicKeyRingCollection rings, long id) throws Exception {
		Optional<PGPPublicKey> key = Optional.ofNullable(rings.getPublicKey(id));
		if (!key.isPresent()) {
			throw new IOException(//
					String.format(BcMessages.getString("BcStreamCodec_encode_error_public_key"), product)); //$NON-NLS-1$
		}
		return key.get();
	}

	private PGPOnePassSignature signature(PGPObjectFactory factory) throws IOException {
		return ((PGPOnePassSignatureList) factory.nextObject()).get(0);
	}

	private PGPSignature pairSignature(PGPObjectFactory factory) throws IOException {
		return ((PGPSignatureList) factory.nextObject()).get(0);
	}

	private InputStream literalDataStream(PGPObjectFactory factory) throws IOException {
		return ((PGPLiteralData) factory.nextObject()).getInputStream();
	}

	private PGPCompressedData compressedData(InputStream decoder) throws LicensingException, IOException {
		PGPObjectFactory factory = new JcaPGPObjectFactory(decoder);
		Optional<PGPCompressedData> compressed = Optional.ofNullable((PGPCompressedData) factory.nextObject());
		if (!compressed.isPresent()) {
			throw new LicensingException(//
					String.format(BcMessages.getString("BcStreamCodec_encode_error_data"), product)); //$NON-NLS-1$
		}
		return compressed.get();
	}

	private byte[] ring(InputStream publicKeyRing, DigestExpectation digest) throws LicensingException {
		byte[] ring = publicKeyRing(publicKeyRing);
		verifyKey(digest, ring);
		return ring;
	}

	private byte[] publicKeyRing(InputStream key) throws LicensingException {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			int chk;
			while ((chk = key.read()) >= 0) {
				baos.write(chk);
			}
			return baos.toByteArray();
		} catch (Exception e) {
			throw new LicensingException(//
					String.format(BcMessages.getString("BcStreamCodec_encode_error_public_key"), product)); //$NON-NLS-1$
		}
	}

	private void verifyKey(DigestExpectation digest, byte[] key) throws LicensingException {
		if (!digest.expected()) {
			return;
		}
		if (Objects.deepEquals(new BcDigest(key).get(), digest.value())) {
			return;
		}
		throw new LicensingException(//
				String.format(BcMessages.getString("BcStreamCodec_encode_error_digest"), product)); //$NON-NLS-1$
	}

}
