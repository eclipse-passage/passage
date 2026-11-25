/*******************************************************************************
 * Copyright (c) 2026 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation; further evolution
 *******************************************************************************/
package org.eclipse.passage.lic.bc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Objects;
import java.util.function.Supplier;

import javax.crypto.Cipher;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.DigestExpectation;
import org.eclipse.passage.lic.api.io.EncryptionAlgorithm;
import org.eclipse.passage.lic.api.io.EncryptionKeySize;
import org.eclipse.passage.lic.internal.bc.i18n.BcMessages;

final class NativeKeys {

	private static final String PRIVATE_KEY = "PRIVATE KEY"; //$NON-NLS-1$
	private static final String PUBLIC_KEY = "PUBLIC KEY"; //$NON-NLS-1$

	private final Supplier<LicensedProduct> product;
	private final EncryptionAlgorithm algorithm;
	private final EncryptionKeySize keySize;

	NativeKeys(Supplier<LicensedProduct> product, EncryptionAlgorithm algorithm, EncryptionKeySize keySize) {
		super();
		this.product = Objects.requireNonNull(product);
		this.algorithm = Objects.requireNonNull(algorithm);
		this.keySize = Objects.requireNonNull(keySize);
	}

	private void verifyKey(DigestExpectation digest, byte[] key) throws LicensingException {
		if (!digest.expected()) {
			return;
		}
		if (Objects.deepEquals(digest(key), digest.value())) {
			return;
		}
		throw new LicensingException(//
				String.format(BcMessages.getString("BcStreamCodec_encode_error_digest"), product)); //$NON-NLS-1$
	}

	private byte[] digest(byte[] key) throws LicensingException {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512"); //$NON-NLS-1$
			digest.update(key);
			return digest.digest();
		} catch (NoSuchAlgorithmException e) {
			throw new LicensingException(e);
		}
	}

	void generateKeys(OutputStream publicKey, OutputStream privateKey) throws LicensingException {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorithm.name());
			keyGen.initialize(keySize.size());
			KeyPair pair = keyGen.generateKeyPair();
			persist(pair.getPrivate(), //
					PRIVATE_KEY, privateKey, //
					"BcStreamCodec_create_keys_error_private"); //$NON-NLS-1$
			persist(pair.getPublic(), //
					"PUBLIC KEY", //$NON-NLS-1$
					publicKey, //
					"BcStreamCodec_create_keys_error_public");//$NON-NLS-1$
		} catch (NoSuchAlgorithmException e) {
			throw new LicensingException(e);
		}
	}

	private void persist(Key key, String type, OutputStream target, String error) throws LicensingException {
		PrintWriter writer = new PrintWriter(target, false, StandardCharsets.US_ASCII);
		writer.printf(begin(type));
		writer.printf("\n"); //$NON-NLS-1$
		writer.flush();
		try (OutputStream b64 = Base64.getMimeEncoder(64, new byte[] { '\n' }).wrap(target)) {
			b64.write(key.getEncoded());
			b64.flush();
		} catch (IOException e) {
			throw new LicensingException(BcMessages.getString(error), e); // $NON-NLS-1$
		}
		writer.printf("\n"); //$NON-NLS-1$
		writer.printf(end(type));
		writer.printf("\n"); //$NON-NLS-1$
		writer.flush();
	}

	private String begin(String type) {
		return String.format("-----BEGIN %s-----", type); //$NON-NLS-1$
	}

	private String end(String type) {
		return String.format("-----END %s-----", type); //$NON-NLS-1$
	}

	void encode(InputStream input, OutputStream output, InputStream key) throws LicensingException {
		try {
			PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decode64(key.readAllBytes(), PRIVATE_KEY));
			KeyFactory factory = KeyFactory.getInstance(algorithm.name());
			PrivateKey pk = factory.generatePrivate(spec);
			int max = (keySize.size() / 8) - 11; // 11 bytes of PKCS1 padding
			processFile(input, output, pk, Cipher.ENCRYPT_MODE, max);
		} catch (Exception e) {
			throw new LicensingException(e);
		}
	}

	void decode(InputStream input, OutputStream output, InputStream key, DigestExpectation digest)
			throws LicensingException {
		try {
			byte[] all = key.readAllBytes();
			verifyKey(digest, all);
			X509EncodedKeySpec spec = new X509EncodedKeySpec(decode64(all, PUBLIC_KEY));
			KeyFactory factory = KeyFactory.getInstance(algorithm.name());
			PublicKey pk = factory.generatePublic(spec);
			int max = keySize.size() / 8; // bytes per bit
			processFile(input, output, pk, Cipher.DECRYPT_MODE, max);
		} catch (LicensingException e) {
			throw e;
		} catch (Exception e) {
			throw new LicensingException(e);
		}
	}

	private byte[] decode64(byte[] all, String type) {
		String base64 = new String(all, StandardCharsets.UTF_8)//
				.replace(begin(type), "") //$NON-NLS-1$
				.replace(end(type), "") //$NON-NLS-1$
				.replaceAll("\\s", ""); //$NON-NLS-1$ //$NON-NLS-2$
		return Base64.getDecoder().decode(base64);
	}

	private void processFile(InputStream input, OutputStream output, Key key, int mode, int max) throws Exception {
		// FIXME: AF: perhaps we need to introduce EncryptionTransformationList
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding"); //$NON-NLS-1$
		cipher.init(mode, key);
		try (BufferedInputStream in = new BufferedInputStream(input);
				BufferedOutputStream out = new BufferedOutputStream(output)) {
			byte[] buffer = new byte[max];
			int read;
			while ((read = in.read(buffer)) != -1) {
				out.write(cipher.doFinal(buffer, 0, read));
			}
		}
	}
}
