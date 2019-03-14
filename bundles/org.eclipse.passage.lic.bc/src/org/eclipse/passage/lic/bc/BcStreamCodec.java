/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.bc;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.BCPGOutputStream;
import org.bouncycastle.bcpg.HashAlgorithmTags;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPCompressedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedData;
import org.bouncycastle.openpgp.PGPKeyPair;
import org.bouncycastle.openpgp.PGPKeyRingGenerator;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPLiteralDataGenerator;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.PGPOnePassSignature;
import org.bouncycastle.openpgp.PGPOnePassSignatureList;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.PGPSignature;
import org.bouncycastle.openpgp.PGPSignatureGenerator;
import org.bouncycastle.openpgp.PGPSignatureList;
import org.bouncycastle.openpgp.PGPSignatureSubpacketGenerator;
import org.bouncycastle.openpgp.PGPSignatureSubpacketVector;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.bc.BcPGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.jcajce.JcaPGPObjectFactory;
import org.bouncycastle.openpgp.jcajce.JcaPGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor;
import org.bouncycastle.openpgp.operator.PBESecretKeyEncryptor;
import org.bouncycastle.openpgp.operator.PGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.PGPContentVerifierBuilderProvider;
import org.bouncycastle.openpgp.operator.PGPDigestCalculator;
import org.bouncycastle.openpgp.operator.PGPDigestCalculatorProvider;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentVerifierBuilderProvider;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPDigestCalculatorProviderBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPKeyPair;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyDecryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyEncryptorBuilder;
import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.io.StreamCodec;
import org.osgi.service.component.annotations.Activate;

public class BcStreamCodec implements StreamCodec {

	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	private String keyAlgo = BcProperties.KEY_ALGO_DEFAULT;
	private int keySize = BcProperties.KEY_SIZE_DEFAULT;
	private int hashAlgo = PGPUtil.SHA512;

	private LicensingConfiguration configuration = LicensingConfigurations.INVALID;

	@Activate
	public void activate(Map<String, Object> properties) {
		configuration = LicensingConfigurations.create(properties);
		keyAlgo = BcProperties.extractKeyAlgo(properties);
		keySize = BcProperties.extractKeySize(properties);
	}

	@Override
	public String getKeyAlgo() {
		return keyAlgo;
	}

	@Override
	public int getKeySize() {
		return keySize;
	}

	@Override
	public void createKeyPair(String publicKeyPath, String privateKeyPath, String username, String password)
			throws IOException {

		Path pathPublicKey = Paths.get(publicKeyPath);
		Path pathPrivateKey = Paths.get(privateKeyPath);

		PGPKeyRingGenerator keyRingGen;
		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance(keyAlgo, BouncyCastleProvider.PROVIDER_NAME);
			generator.initialize(keySize);

			KeyPair keyPair = generator.generateKeyPair();

			PGPDigestCalculator sha1Calc = new JcaPGPDigestCalculatorProviderBuilder().build()
					.get(HashAlgorithmTags.SHA1);

			PGPKeyPair pgpKeyPair = new JcaPGPKeyPair(PGPPublicKey.RSA_GENERAL, keyPair, new Date());

			JcaPGPContentSignerBuilder certificationSignerBuilder = new JcaPGPContentSignerBuilder(
					pgpKeyPair.getPublicKey().getAlgorithm(), HashAlgorithmTags.SHA1);

			JcePBESecretKeyEncryptorBuilder jcePBESecretKeyEncryptorBuilder = new JcePBESecretKeyEncryptorBuilder(
					PGPEncryptedData.CAST5, sha1Calc);

			PBESecretKeyEncryptor secretKeyEcriptor = jcePBESecretKeyEncryptorBuilder
					.setProvider(BouncyCastleProvider.PROVIDER_NAME).build(password.toCharArray());

			PGPSignatureSubpacketGenerator subpacketGenerator = new PGPSignatureSubpacketGenerator();

			PGPSignatureSubpacketVector subpacketVector = null;

			subpacketGenerator.setKeyExpirationTime(false, 1000L);

			subpacketVector = subpacketGenerator.generate();

			keyRingGen = new PGPKeyRingGenerator(PGPSignature.POSITIVE_CERTIFICATION, pgpKeyPair, username, sha1Calc,
					subpacketVector, null, certificationSignerBuilder, secretKeyEcriptor);

			keyRingGen.addSubKey(pgpKeyPair);
		} catch (Exception e) {
			throw new IOException("Error working with key ring", e);
		}

		PGPPublicKeyRing keyRingPublic = keyRingGen.generatePublicKeyRing();
		PGPSecretKeyRing keyRingSecret = keyRingGen.generateSecretKeyRing();

		File privateKey = pathPrivateKey.toFile();

		try (FileOutputStream fos = new FileOutputStream(privateKey);
				ArmoredOutputStream output = new ArmoredOutputStream(new BufferedOutputStream(fos))) {
			keyRingSecret.encode(output);
		} catch (IOException e) {
			throw new IOException("Error writing private key", e);
		}

		File publicKey = pathPublicKey.toFile();
		try (FileOutputStream fos = new FileOutputStream(publicKey);
				ArmoredOutputStream output = new ArmoredOutputStream(new BufferedOutputStream(fos))) {
			keyRingPublic.encode(output);
		} catch (IOException e) {
			throw new IOException("Error writing public key", e);
		}
	}

	@Override
	public void encodeStream(InputStream input, OutputStream output, InputStream keyRing, String username,
			String password) throws IOException {
		PGPSecretKey key = findKey(keyRing, username);
		try {
			OutputStream aos = new ArmoredOutputStream(output);
			PGPCompressedDataGenerator generator = new PGPCompressedDataGenerator(PGPCompressedData.ZLIB);
			BCPGOutputStream generated = new BCPGOutputStream(generator.open(aos));
			PGPContentSignerBuilder csBuilder = new JcaPGPContentSignerBuilder(key.getPublicKey().getAlgorithm(),
					hashAlgo);

			PGPSignatureGenerator signatureGenerator = new PGPSignatureGenerator(csBuilder);
			PGPDigestCalculatorProvider calcProvider = new JcaPGPDigestCalculatorProviderBuilder()
					.setProvider(BouncyCastleProvider.PROVIDER_NAME).build();
			PBESecretKeyDecryptor decryptor = new JcePBESecretKeyDecryptorBuilder(calcProvider)
					.setProvider(BouncyCastleProvider.PROVIDER_NAME).build(password.toCharArray());
			PGPPrivateKey privateKey = key.extractPrivateKey(decryptor);
			signatureGenerator.init(PGPSignature.BINARY_DOCUMENT, privateKey);

			final Iterator<String> it = key.getPublicKey().getUserIDs();
			if (it.hasNext()) {
				final PGPSignatureSubpacketGenerator generator1 = new PGPSignatureSubpacketGenerator();
				generator1.setSignerUserID(false, it.next());
				signatureGenerator.setHashedSubpackets(generator1.generate());
			}

			signatureGenerator.generateOnePassVersion(false).encode(generated);
			final PGPLiteralDataGenerator literalDataGenerator = new PGPLiteralDataGenerator();
			final OutputStream literalDataStream = literalDataGenerator.open(generated, PGPLiteralData.BINARY,
					"ignored", new Date(), new byte[1024]);
			int ch;
			while ((ch = input.read()) >= 0) {
				literalDataStream.write(ch);
				signatureGenerator.update((byte) ch);
			}
			literalDataGenerator.close();
			signatureGenerator.generate().encode(generated);
			generator.close();
			aos.close();
		} catch (Exception e) {
			String message = String.format("Encoding error for configuration %s", configuration);
			throw new IOException(message, e);
		}
	}

	private PGPSecretKey findKey(InputStream key, String username) throws IOException {
		try (InputStream decoder = PGPUtil.getDecoderStream(key)) {
			final PGPSecretKeyRingCollection keyRingCollection = new JcaPGPSecretKeyRingCollection(decoder);
			Iterator<PGPSecretKeyRing> keyRings = keyRingCollection.getKeyRings();
			while (keyRings.hasNext()) {
				PGPSecretKeyRing keyRing = keyRings.next();
				Iterator<PGPSecretKey> secretKeys = keyRing.getSecretKeys();
				while (secretKeys.hasNext()) {
					PGPSecretKey secretKey = secretKeys.next();
					if (!secretKey.isSigningKey()) {
						continue;
					}
					Iterator<?> userIDs = secretKey.getUserIDs();
					while (userIDs.hasNext()) {
						Object next = userIDs.next();
						if (Objects.equals(username, next)) {
							return secretKey;
						}
					}
				}
			}
		} catch (Exception e) {
			throw new IOException("Unable to find signing key", e);
		}
		throw new IOException("Unable to find signing key");
	}

	@Override
	public Object decodeStream(InputStream input, OutputStream output, InputStream key, byte[] digest)
			throws IOException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int chk;
		while ((chk = key.read()) >= 0) {
			baos.write(chk);
		}
		byte[] publicKeyRing = baos.toByteArray();
		if (digest != null) {
			final byte[] calculatedDigest = BcDigest.calculateDigest(publicKeyRing);
			for (int i = 0; i < calculatedDigest.length; i++) {
				if (calculatedDigest[i] != (digest[i])) {
					String message = String.format("Key ring digest does not match for configuration %s",
							configuration);
					throw new IOException(message);
				}
			}
		}
		try (final InputStream decoderInputStream = PGPUtil.getDecoderStream(input)) {
			final ByteArrayInputStream keyIn = new ByteArrayInputStream(publicKeyRing);

			PGPObjectFactory pgpFactory = new JcaPGPObjectFactory(decoderInputStream);
			final PGPCompressedData firstCompressed = (PGPCompressedData) pgpFactory.nextObject();
			pgpFactory = new JcaPGPObjectFactory(firstCompressed.getDataStream());
			final PGPOnePassSignatureList slist1 = (PGPOnePassSignatureList) pgpFactory.nextObject();
			final PGPOnePassSignature slist1s1 = slist1.get(0);
			final PGPLiteralData literalData = (PGPLiteralData) pgpFactory.nextObject();

			try (final InputStream literalStream = literalData.getInputStream();
					InputStream decoderStream = PGPUtil.getDecoderStream(keyIn)) {
				final BcPGPPublicKeyRingCollection pgpRing = new BcPGPPublicKeyRingCollection(decoderStream);
				long decodeKeyId = slist1s1.getKeyID();

				final PGPPublicKey decodeKey = pgpRing.getPublicKey(decodeKeyId);
				if (decodeKey == null) {
					String message = String.format("Public key invalid for configuration %s", configuration);
					throw new IOException(message);
				}
				final PGPContentVerifierBuilderProvider cvBuilder = new JcaPGPContentVerifierBuilderProvider();
				slist1s1.init(cvBuilder, decodeKey);

				ByteArrayOutputStream untrusted = new ByteArrayOutputStream();
				int ch;
				while ((ch = literalStream.read()) >= 0) {
					slist1s1.update((byte) ch);
					untrusted.write(ch);
				}
				final PGPSignatureList slist2 = (PGPSignatureList) pgpFactory.nextObject();
				PGPSignature slist2s1 = slist2.get(0);
				if (slist1s1.verify(slist2s1)) {
					byte[] verified = untrusted.toByteArray();
					output.write(verified);
				}
				return Long.valueOf(decodeKeyId);
			}
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			String message = String.format("Decoding error for configuration %s", configuration);
			throw new IOException(message, e);
		}
	}
}
