package org.eclipse.passage.lic.internal.bc.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
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
		// given:
		Path pub = keyFile(new PassageFileExtension.PublicKey());
		Path secret = keyFile(new PassageFileExtension.PrivateKey());
		try {
			// when
			new BcStreamCodec(this::product).createKeyPair(pub, secret, "test-user", "test-pass-word"); //$NON-NLS-1$//$NON-NLS-2$
		} catch (LicensingException e) {
			// then
			fail("PGP key pair generation on valid data is not supposed to fail"); //$NON-NLS-1$
		}
		// then: 1) default key size, 2) not empty files 3) pair is valid

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
		return folder.newFile(Long.toHexString(System.nanoTime()) + extension.get()).toPath();
	}

	/**
	 * Creates a reference for not yet existing file
	 */
	private Path keyPath(PassageFileExtension extension) throws IOException {
		return folder.getRoot().toPath().resolve(Long.toHexString(System.nanoTime()) + extension.get());
	}

}
