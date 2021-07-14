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
import java.util.function.Function;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.DigestExpectation;
import org.eclipse.passage.lic.base.io.BaseDigestExpectation;
import org.eclipse.passage.lic.base.io.FileContent;
import org.eclipse.passage.lic.bc.BcDigest;
import org.eclipse.passage.lic.bc.BcStreamCodec;
import org.junit.Test;

public final class StreamDecodingTest extends BcStreamCodecTest {

	@Test
	public void decodingIsFunctional() throws IOException {
		Path[] actors;
		try {
			actors = decode();
		} catch (LicensingException e) {
			fail("Decoding for valid data is not supposed to fail"); //$NON-NLS-1$
			throw new RuntimeException();// unreachable
		}
		Path origin = actors[0];
		Path encoded = actors[1];
		Path decoded = actors[2];
		assertTrue(Files.size(decoded) > 0);
		assertFalse(Objects.deepEquals(//
				new FileContent(encoded).get(), //
				new FileContent(decoded).get()));
		assertTrue(Objects.deepEquals(//
				new FileContent(origin).get(), //
				new FileContent(decoded).get()));
	}

	/**
	 * Decoding with a foreign key must fail even if it is not demanded to be
	 * assessed by digest expectation.
	 */
	@Test
	public void properKeyIsRequired() throws IOException {
		try {
			decodeWithKey(PairInfo::secondInfo);
		} catch (LicensingException e) {
			return;
		}
		fail("Decoding with incorrect key must fail"); //$NON-NLS-1$
	}

	@Test
	public void validDigestDoesNotFailDecoding() throws IOException {
		try {
			decodeWithDigest(key -> new BaseDigestExpectation(new BcDigest(key).get()));
		} catch (LicensingException e) {
			fail("Decoding for valid data is not supposed to fail"); //$NON-NLS-1$
		}
	}

	@Test
	public void invalidDigestFailsDecoding() throws IOException {
		try {
			decodeWithDigest(key -> new BaseDigestExpectation(new byte[0]));
		} catch (LicensingException e) {
			assertTrue(e.getMessage().contains("digest")); //$NON-NLS-1$
			return;
		}
		fail("Unsatisfiable digest expectation must stop decoding"); //$NON-NLS-1$
	}

	private Path encoded(Path origin, Path privateKey, String user, String pass) throws IOException {
		Path encoded = new TmpFile(root).file(".txt"); //$NON-NLS-1$
		try (//
				InputStream source = new FileInputStream(origin.toFile());
				OutputStream destination = new FileOutputStream(encoded.toFile());
				InputStream key = new FileInputStream(privateKey.toFile())) {
			new BcStreamCodec(this::product).encode(source, destination, key, user, pass);
		} catch (LicensingException e) {
			fail("Failed to encode a file: check corresponding tests set"); //$NON-NLS-1$
		}
		return encoded;
	}

	@Test(expected = NullPointerException.class)
	public void sourceIsMandatory() throws IOException {
		try (OutputStream output = anOutput(); InputStream key = anInput()) {
			decodeNull(null, output, key, new DigestExpectation.None());
		}
	}

	@Test(expected = NullPointerException.class)
	public void targetIsMandatory() throws IOException {
		try (InputStream input = anInput(); InputStream key = anInput()) {
			decodeNull(input, null, key, new DigestExpectation.None());
		}
	}

	@Test(expected = NullPointerException.class)
	public void keyIsMandatory() throws IOException {
		try (InputStream input = anInput(); OutputStream output = anOutput()) {
			decodeNull(input, output, null, new DigestExpectation.None());
		}
	}

	@Test(expected = NullPointerException.class)
	public void digestIsMandatory() throws IOException {
		try (InputStream input = anInput(); OutputStream output = anOutput(); InputStream key = anInput()) {
			decodeNull(input, output, key, null);
		}
	}

	private void decodeNull(InputStream input, OutputStream output, InputStream key, DigestExpectation digest) {
		try {
			new BcStreamCodec(this::product).decode(input, output, key, digest);
		} catch (LicensingException e) {
			fail("Incorrect incoming data are not intended to cause any decoding activity"); //$NON-NLS-1$
		}
	}

	private Path[] decode() throws IOException, LicensingException {
		return decode(pair -> pair.firstInfo(), key -> new DigestExpectation.None());
	}

	private Path[] decodeWithDigest(ThrowingDigestSupplier digest) throws IOException, LicensingException {
		return decode(pair -> pair.firstInfo(), digest);
	}

	private Path[] decodeWithKey(Function<PairInfo<Path>, Path> publicKey) throws IOException, LicensingException {
		return decode(publicKey, key -> new DigestExpectation.None());
	}

	private Path[] decode(Function<PairInfo<Path>, Path> publicKey, ThrowingDigestSupplier digest)
			throws IOException, LicensingException {
		// given
		String user = "Suer"; //$NON-NLS-1$
		String pass = "Vyer"; //$NON-NLS-1$
		PairInfo<Path> pair = pair(user, pass);
		Path origin = new TmpFile(root).fileWithContent();
		Path encoded = encoded(origin, pair.secondInfo(), user, pass);
		Path decoded = new TmpFile(root).file(".txt"); //$NON-NLS-1$
		assumeTrue(Files.size(decoded) == 0);

		// when
		try (//
				InputStream source = new FileInputStream(encoded.toFile());
				OutputStream destination = new FileOutputStream(decoded.toFile());
				InputStream key = new FileInputStream(publicKey.apply(pair).toFile())) {
			new BcStreamCodec(this::product).decode(source, destination, key, digest.forKey(pair.firstInfo()));
		}
		return new Path[] { origin, encoded, decoded };
	}

	private interface ThrowingDigestSupplier {
		DigestExpectation forKey(Path key) throws IOException;
	}

}
