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
package org.eclipse.passage.lic.internal.bc.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Objects;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.DigestExpectation;
import org.eclipse.passage.lic.api.io.StreamCodec;
import org.eclipse.passage.lic.api.io.StreamCodec.Smart;
import org.eclipse.passage.lic.base.io.FileContent;
import org.eclipse.passage.lic.base.io.PassageFileExtension;
import org.eclipse.passage.lic.bc.BcStreamCodec;
import org.junit.Test;

public final class KeyPairGenerationTest extends BcStreamCodecTest {

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
	 * <p>
	 * Side test: {@code BcStreamCodec} does not have state.
	 * </p>
	 * 
	 * @throws IOException in case of file system denials
	 */
	@Test
	public void generatedPairIsFunctional() throws IOException {
		// given
		Path origin = new TmpFile(root).fileWithContent();
		Path encoded = new TmpFile(root).file(".txt"); //$NON-NLS-1$
		Path decoded = new TmpFile(root).file(".txt"); //$NON-NLS-1$
		String user = "fake.user"; //$NON-NLS-1$
		String pass = "some$pass#val&1"; //$NON-NLS-1$

		// when: first: create pair
		PairInfo<Path> pair = pair(user, pass);
		try (//
				InputStream target = new FileInputStream(origin.toFile()); //
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
				new FileContent(origin).get(), //
				new FileContent(decoded).get()));

	}

	@Test(expected = NullPointerException.class)
	public void publicKeyPathIsMandatory() throws LicensingException, IOException {
		codec().createKeyPair(//
				null, //
				new TmpFile(root).keyFile(new PassageFileExtension.PrivateKey()), //
				"u", //$NON-NLS-1$
				"p"); //$NON-NLS-1$
	}

	@Test(expected = NullPointerException.class)
	public void privateKeyPathIsMandatory() throws LicensingException, IOException {
		codec().createKeyPair(//
				new TmpFile(root).keyFile(new PassageFileExtension.PublicKey()), //
				null, //
				"u", //$NON-NLS-1$
				"p"); //$NON-NLS-1$
	}

	@Test(expected = NullPointerException.class)
	public void usernameIsMandatory() throws LicensingException, IOException {
		codec().createKeyPair(//
				new TmpFile(root).keyFile(new PassageFileExtension.PublicKey()), //
				new TmpFile(root).keyFile(new PassageFileExtension.PrivateKey()), //
				null, //
				"p"); //$NON-NLS-1$
	}

	@Test(expected = NullPointerException.class)
	public void passwordIsMandatory() throws LicensingException, IOException {
		codec().createKeyPair(//
				new TmpFile(root).keyFile(new PassageFileExtension.PublicKey()), //
				new TmpFile(root).keyFile(new PassageFileExtension.PrivateKey()), //
				"u", //$NON-NLS-1$
				null);
	}

	@Test
	public void absentPrivateKeyFileIsCreated() throws LicensingException, IOException {
		// given
		Path privatePath = new TmpFile(root).keyPath(new PassageFileExtension.PrivateKey());
		// when
		codec().createKeyPair(//
				new TmpFile(root).keyFile(new PassageFileExtension.PublicKey()), //
				privatePath, //
				"u", //$NON-NLS-1$
				"p"); //$NON-NLS-1$
		// then
		assertFileExists(privatePath);
	}

	@Test
	public void absentPublicKeyFileIsCreated() throws LicensingException, IOException {
		// given
		Path publicPath = new TmpFile(root).keyPath(new PassageFileExtension.PublicKey());
		// when
		codec().createKeyPair(//
				publicPath, //
				new TmpFile(root).keyFile(new PassageFileExtension.PrivateKey()), //
				"u", //$NON-NLS-1$
				"p"); //$NON-NLS-1$
		// then
		assertFileExists(publicPath);
	}

	private <I> PairInfo<I> pair(ThrowingCtor<I> ctor) throws IOException {
		return pair(ctor, "test-user", "test-pass-word");//$NON-NLS-1$//$NON-NLS-2$
	}

	private Smart codec() {
		return new StreamCodec.Smart(new BcStreamCodec(this::product));
	}

}
