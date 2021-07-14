/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
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

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;

import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.HashAlgorithmTags;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPEncryptedData;
import org.bouncycastle.openpgp.PGPKeyPair;
import org.bouncycastle.openpgp.PGPKeyRing;
import org.bouncycastle.openpgp.PGPKeyRingGenerator;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSignature;
import org.bouncycastle.openpgp.PGPSignatureSubpacketGenerator;
import org.bouncycastle.openpgp.PGPSignatureSubpacketVector;
import org.bouncycastle.openpgp.operator.PBESecretKeyEncryptor;
import org.bouncycastle.openpgp.operator.PGPDigestCalculator;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPDigestCalculatorProviderBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPKeyPair;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyEncryptorBuilder;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.EncryptionAlgorithm;
import org.eclipse.passage.lic.api.io.EncryptionKeySize;
import org.eclipse.passage.lic.internal.bc.i18n.BcMessages;

/**
 * <p>
 * Generated a couple of PGP keys ({@linkplain PGPPublicKey} and pairing
 * {@linkplain PGPSecretKey})
 * </p>
 * <ul>
 * <li>into the files pointed in the given {@linkplain Targets}</li>
 * <li>according to the configured {@linkplain EncryptionParameters}</li>
 * <li>for an owner with the passed credentials</li>
 * </ul>
 * <p>
 * Empowers
 * {@linkplain BcStreamCodec#createKeyPair(Path, Path, String, String)}.
 * </p>
 */
final class BcKeyPair {

	private final Targets targets;
	private final EncryptionParameters parameters;

	BcKeyPair(Targets targets, EncryptionParameters parameters) {
		this.targets = targets;
		this.parameters = parameters;
	}

	void generate(String username, String password) throws LicensingException {
		PGPKeyRingGenerator ring = keyRing(username, password);
		persist(ring.generateSecretKeyRing(), //
				targets.privatePath, //
				"BcStreamCodec_create_keys_error_private"); //$NON-NLS-1$
		persist(ring.generatePublicKeyRing(), //
				targets.publicPath, //
				"BcStreamCodec_create_keys_error_public");//$NON-NLS-1$
	}

	private PGPKeyRingGenerator keyRing(String username, String password) throws LicensingException {
		try {
			PGPKeyPair pair = new JcaPGPKeyPair(PGPPublicKey.RSA_GENERAL, pair(), new Date());
			PGPDigestCalculator calculator = new JcaPGPDigestCalculatorProviderBuilder()//
					.build().get(HashAlgorithmTags.SHA1);
			PGPKeyRingGenerator generator = new PGPKeyRingGenerator(PGPSignature.POSITIVE_CERTIFICATION, pair, username,
					calculator, vector(), null, signer(pair), encryptor(password, calculator));
			generator.addSubKey(pair);
			return generator;
		} catch (Exception e) {
			throw new LicensingException(BcMessages.getString("BcStreamCodec_create_keys_error_ring"), e); //$NON-NLS-1$
		}
	}

	private void persist(PGPKeyRing key, OutputStream target, String error) throws LicensingException {
		try (ArmoredOutputStream output = new ArmoredOutputStream(new BufferedOutputStream(target))) {
			key.encode(output);
			output.flush();
		} catch (IOException e) {
			throw new LicensingException(BcMessages.getString(error), e); // $NON-NLS-1$
		}
	}

	private KeyPair pair() throws NoSuchAlgorithmException, NoSuchProviderException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance(//
				parameters.algorithm.name(), BouncyCastleProvider.PROVIDER_NAME);
		generator.initialize(parameters.key.size());
		return generator.generateKeyPair();
	}

	private PBESecretKeyEncryptor encryptor(String password, PGPDigestCalculator calculator) {
		JcePBESecretKeyEncryptorBuilder builder = new JcePBESecretKeyEncryptorBuilder(//
				PGPEncryptedData.CAST5, calculator);
		return builder.setProvider(BouncyCastleProvider.PROVIDER_NAME).build(password.toCharArray());
	}

	private PGPSignatureSubpacketVector vector() {
		PGPSignatureSubpacketGenerator subpacketGenerator = new PGPSignatureSubpacketGenerator();
		subpacketGenerator.setKeyExpirationTime(false, parameters.expiration);
		return subpacketGenerator.generate();
	}

	private JcaPGPContentSignerBuilder signer(PGPKeyPair pair) {
		return new JcaPGPContentSignerBuilder(pair.getPublicKey().getAlgorithm(), HashAlgorithmTags.SHA1);
	}

	static final class Targets {

		private final OutputStream publicPath;
		private final OutputStream privatePath;

		Targets(OutputStream publicPath, OutputStream privatePath) {
			this.publicPath = publicPath;
			this.privatePath = privatePath;
		}

	}

	static final class EncryptionParameters {

		private final EncryptionAlgorithm algorithm;
		private final EncryptionKeySize key;
		private final long expiration = 1000L;

		EncryptionParameters(EncryptionAlgorithm algorithm, EncryptionKeySize key) {
			this.algorithm = algorithm;
			this.key = key;
		}

	}

}
