/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.bc.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.io.DigestExpectation;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.base.io.PassageFileExtension;
import org.eclipse.passage.lic.internal.bc.BcStreamCodec;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

@SuppressWarnings("restriction")
public final class KeyPairGenerationTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void generationSucceeds() throws IOException {
		PairInfo<Integer> pair = pair((pub, secret) -> new PairSize(pub, secret));
		assertTrue(pair.secondInfo() > 0);
		assertTrue(pair.firstInfo() > 0);
	}

	@Test
	public void subsequentPairDiffer() throws IOException {
		PairInfo<byte[]> first = pair((pub, secret) -> new PairContent(pub, secret));
		PairInfo<byte[]> second = pair((pub, secret) -> new PairContent(pub, secret));
		assertFalse(first.equals(second));
	}

	/**
	 * <ul>
	 * <li>generate pair of keys</li>
	 * <li>encode random text file with {@code private} key</li>
	 * <li>decode the result with the pairing {@code public} key</li>
	 * <li>compare original and decoded files: they re expected to have equal
	 * content</li>
	 * </ul>
	 * 
	 * <p>
	 * <b>NB: </b>It is not symmetric: you can encrypt only with {@code private} key
	 * and then decipher only with the pairing {@code public}.
	 * </p>
	 * 
	 * @throws IOException in case of file system denials
	 */
	@Test
	public void generatedPairIsFunctional() throws IOException {
		// given
		Path victim = fileWithContent();
		Path encoded = file(".txt"); //$NON-NLS-1$
		Path decoded = file(".txt"); //$NON-NLS-1$
		String user = "fake.user"; //$NON-NLS-1$
		String pass = "some$pass#val&1"; //$NON-NLS-1$

		// when: first: encode
		PairInfo<Path> pair = pair((pub, secret) -> new PairKeys(pub, secret), user, pass);
		try (//
				InputStream target = new FileInputStream(victim.toFile()); //
				OutputStream destination = new FileOutputStream(encoded.toFile()); //
				InputStream key = new FileInputStream(pair.secondInfo().toFile())) {
			new BcStreamCodec(this::product).encode(target, destination, key, user, pass);
		} catch (LicensingException e) {
			fail("Encoding is not supposed to fail on valid data"); //$NON-NLS-1$
		}

		// when: second: decode
		try (//
				InputStream target = new FileInputStream(encoded.toFile()); //
				OutputStream destination = new FileOutputStream(decoded.toFile()); //
				InputStream key = new FileInputStream(pair.firstInfo().toFile())) {
			new BcStreamCodec(this::product).decode(target, destination, key, new DigestExpectation.None());
		} catch (LicensingException e) {
			e.printStackTrace();
			fail("Decoding is not supposed to fail on valid data"); //$NON-NLS-1$
		}

		// then
		assertTrue(Objects.deepEquals(//
				new FileContent(victim).get(), //
				new FileContent(decoded).get()));

	}

	@Test(expected = NullPointerException.class)
	public void publicKeyPathIsMandatory() throws LicensingException, IOException {
		new BcStreamCodec(this::product).createKeyPair(//
				null, //
				keyFile(new PassageFileExtension.PrivateKey()), //
				"u", //$NON-NLS-1$
				"p"); //$NON-NLS-1$
	}

	@Test(expected = NullPointerException.class)
	public void privateKeyPathIsMandatory() throws LicensingException, IOException {
		new BcStreamCodec(this::product).createKeyPair(//
				keyFile(new PassageFileExtension.PublicKey()), //
				null, //
				"u", //$NON-NLS-1$
				"p"); //$NON-NLS-1$
	}

	@Test(expected = NullPointerException.class)
	public void usernameIsMandatory() throws LicensingException, IOException {
		new BcStreamCodec(this::product).createKeyPair(//
				keyFile(new PassageFileExtension.PublicKey()), //
				keyFile(new PassageFileExtension.PrivateKey()), //
				null, //
				"p"); //$NON-NLS-1$
	}

	@Test(expected = NullPointerException.class)
	public void passwordIsMandatory() throws LicensingException, IOException {
		new BcStreamCodec(this::product).createKeyPair(//
				keyFile(new PassageFileExtension.PublicKey()), //
				keyFile(new PassageFileExtension.PrivateKey()), //
				"u", //$NON-NLS-1$
				null);
	}

	@Test
	public void absentPrivateKeyFileIsCreated() throws LicensingException, IOException {
		// given
		Path privatePath = keyPath(new PassageFileExtension.PrivateKey());
		// when
		new BcStreamCodec(this::product).createKeyPair(//
				keyFile(new PassageFileExtension.PublicKey()), //
				privatePath, //
				"u", //$NON-NLS-1$
				"p"); //$NON-NLS-1$
		// then
		assertFileExists(privatePath);
	}

	@Test
	public void absentPublicKeyFileIsCreated() throws LicensingException, IOException {
		// given
		Path publicPath = keyPath(new PassageFileExtension.PublicKey());
		// when
		new BcStreamCodec(this::product).createKeyPair(//
				publicPath, //
				keyFile(new PassageFileExtension.PrivateKey()), //
				"u", //$NON-NLS-1$
				"p"); //$NON-NLS-1$
		// then
		assertFileExists(publicPath);
	}

	private <I> PairInfo<I> pair(ThrowingCtor<I> ctor) throws IOException {
		return pair(ctor, "test-user", "test-pass-word");//$NON-NLS-1$//$NON-NLS-2$
	}

	private <I> PairInfo<I> pair(ThrowingCtor<I> ctor, String user, String pass) throws IOException {
		// given:
		Path pub = keyFile(new PassageFileExtension.PublicKey());
		Path secret = keyFile(new PassageFileExtension.PrivateKey());
		BcStreamCodec codec = new BcStreamCodec(this::product);
		try {
			// when
			codec.createKeyPair(pub, secret, user, pass);
		} catch (LicensingException e) {
			// then
			fail("PGP key pair generation on valid data is not supposed to fail"); //$NON-NLS-1$
		}
		return ctor.create(pub, secret);
	}

	private void assertFileExists(Path file) {
		assertTrue(Files.exists(file));
		assertTrue(Files.isRegularFile(file));
	}

	private LicensedProduct product() {
		return new BaseLicensedProduct("test-product", "1.0.18"); //$NON-NLS-1$//$NON-NLS-2$
	}

	/**
	 * Physically creates an empty file with demanded extension
	 */
	private Path keyFile(PassageFileExtension extension) throws IOException {
		return file(extension.get());
	}

	private Path file(String extension) throws IOException {
		return folder.newFile(Long.toHexString(System.nanoTime()) + extension).toPath();
	}

	/**
	 * Creates a reference for not yet existing file
	 */
	private Path keyPath(PassageFileExtension extension) throws IOException {
		return folder.getRoot().toPath().resolve(Long.toHexString(System.nanoTime()) + extension.get());
	}

	private Path fileWithContent() throws IOException {
		Path path = folder.newFile(Long.toHexString(System.nanoTime()) + ".txt").toPath(); //$NON-NLS-1$
		try (PrintWriter writer = new PrintWriter(path.toFile())) {
			writer.println("content row 1"); //$NON-NLS-1$
			writer.println("content row 2"); //$NON-NLS-1$
			writer.println("content row 3"); //$NON-NLS-1$
		}
		return path;
	}

}
