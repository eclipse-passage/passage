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
import static org.junit.Assume.assumeTrue;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.StreamCodec;
import org.eclipse.passage.lic.base.io.FileContent;
import org.eclipse.passage.lic.base.io.PassageFileExtension;
import org.eclipse.passage.lic.bc.BcStreamCodec;
import org.junit.Test;

public final class StreamEncodingTest extends BcStreamCodecTest {

	@Test
	public void encodingIsFunctional() throws IOException {
		// given
		Path victim = new TmpFile(root).fileWithContent();
		Path encoded = new TmpFile(root).file(".txt"); //$NON-NLS-1$
		assumeTrue(Files.size(encoded) == 0);
		String user = "Suer"; //$NON-NLS-1$
		String pass = "Vyer"; //$NON-NLS-1$

		// when
		try (//
				InputStream source = new FileInputStream(victim.toFile());
				OutputStream destination = new FileOutputStream(encoded.toFile());
				InputStream key = new FileInputStream(privateKey(user, pass).toFile())) {
			new BcStreamCodec(this::product).encode(source, destination, key, user, pass);
		} catch (LicensingException e) {
			fail("Encoding for valid data is not supposed to fail"); //$NON-NLS-1$
		}

		// then
		assertTrue(Files.size(encoded) > 0);
		assertFalse(Objects.deepEquals(//
				new FileContent(victim).get(), //
				new FileContent(encoded).get()));
	}

	/**
	 * Here we do the encoding using a random char sequence as an encryption key.
	 * This supposed to constantly fail as only properly generated keys are
	 * acceptable for encoding purpose.
	 */
	@Test
	public void properKeyIsRequired() throws IOException {
		try (//
				InputStream source = new FileInputStream(new TmpFile(root).fileWithContent().toFile());
				OutputStream destination = anOutput();
				InputStream key = new FileInputStream(new TmpFile(root).fileWithRandomContent(1024).toFile());) {
			new BcStreamCodec(this::product).encode(//
					source, destination, key, "user", "pass");//$NON-NLS-1$ //$NON-NLS-2$
		} catch (LicensingException e) {
			assertTrue(e.getMessage().contains("key")); //$NON-NLS-1$
			return;
		}
		fail("Enconding is not supposed to encrypt anything with  a random sequence of chars as a key"); //$NON-NLS-1$
	}

	@Test(expected = NullPointerException.class)
	public void sourceIsMandatory() throws IOException {
		try (OutputStream destination = anOutput(); InputStream key = anInput()) {
			encodeNull(null, destination, key);
		}
	}

	@Test(expected = NullPointerException.class)
	public void destinationIsMandatory() throws IOException {
		try (InputStream input = anInput(); InputStream key = anInput()) {
			encodeNull(input, null, key);
		}
	}

	@Test(expected = NullPointerException.class)
	public void keyIsMandatory() throws IOException {
		try (InputStream input = anInput(); OutputStream destination = anOutput()) {
			encodeNull(input, destination, null);
		}
	}

	@Test(expected = NullPointerException.class)
	public void ownerIsMandatory() throws IOException {
		try (InputStream input = anInput(); OutputStream destination = anOutput(); InputStream key = anInput()) {
			encodeNull(input, destination, key, null, "pass"); //$NON-NLS-1$
		}
	}

	@Test(expected = NullPointerException.class)
	public void passwordIsMandatory() throws IOException {
		try (InputStream input = anInput(); OutputStream destination = anOutput(); InputStream key = anInput()) {
			encodeNull(input, destination, key, "owner", null); //$NON-NLS-1$
		}
	}

	private Path privateKey(String user, String pass) throws IOException {
		Path key = new TmpFile(root).keyFile(new PassageFileExtension.PrivateKey());
		try {
			new StreamCodec.Smart(new BcStreamCodec(this::product)).createKeyPair(//
					new TmpFile(root).keyFile(new PassageFileExtension.PublicKey()), //
					key, user, pass);
		} catch (LicensingException e) {
			fail("Failed to getenate key pair: check corresponging tests set"); //$NON-NLS-1$
		}
		return key;
	}

	private void encodeNull(InputStream input, OutputStream output, InputStream key) {
		encodeNull(input, output, key, "usher", "doorZ"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private void encodeNull(InputStream input, OutputStream output, InputStream key, String user, String pass) {
		try {
			new BcStreamCodec(this::product).encode(input, output, key, user, pass);
		} catch (LicensingException e) {
			fail("Incorrect incoming data are not intended to cause any encoding activity"); //$NON-NLS-1$
		}
	}

}
