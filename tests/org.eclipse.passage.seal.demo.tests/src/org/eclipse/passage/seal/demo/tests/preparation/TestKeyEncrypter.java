package org.eclipse.passage.seal.demo.tests.preparation;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.StreamCodec;
import org.eclipse.passage.lic.base.BaseLicensedProduct;
import org.eclipse.passage.lic.base.io.PassageFileExtension.LicenseDecrypted;
import org.eclipse.passage.lic.base.io.PassageFileExtension.LicenseEncrypted;
import org.eclipse.passage.lic.bc.BcStreamCodec;
import org.eclipse.passage.lic.internal.emf.EObjectFromBytes;
import org.eclipse.passage.lic.keys.model.api.KeyPair;
import org.eclipse.passage.loc.internal.api.workspace.Keys;

@SuppressWarnings("restriction")
final class TestKeyEncrypter {

	static {
		EcorePlugin.ExtensionProcessor.process(TestKeyEncrypter.class.getClassLoader());
	}

	public static void main(String[] args) throws Exception {
		LicensedProduct product = new BaseLicensedProduct("anti-human-magic.product", "0.2.1"); //$NON-NLS-1$ //$NON-NLS-2$
		{
			Path base = Path.of(".").toRealPath(); //$NON-NLS-1$
			String id = "3251ddf1-bd2c-48e4-993a-26fbf7eb3a42"; //$NON-NLS-1$
			Path licenseDir = base.resolve(Path.of("resource", "lics", product.identifier(), product.version())); //$NON-NLS-1$//$NON-NLS-2$
			Path decrypted = licenseDir.resolve(id + new LicenseDecrypted().get());
			Path encrypted = licenseDir.resolve(id + new LicenseEncrypted().get());
			Path keyBase = base.resolve("resource"); //$NON-NLS-1$
			new TestKeyEncrypter(keyBase, product, decrypted, encrypted).writeEncoded();
		}
	}

	private final Path keyBase;
	private final LicensedProduct product;
	private final Path decrypted;
	private final Path encrypted;

	private TestKeyEncrypter(Path keyBase, LicensedProduct product, Path decrypted, Path encrypted) {
		this.keyBase = keyBase;
		this.product = product;
		this.decrypted = decrypted;
		this.encrypted = encrypted;
	}

	// From PersistedEncoded

	private Path writeEncoded() throws LicensingException, IOException, Exception {
		String password = product.identifier() + "###" + product.version(); //$NON-NLS-1$
		try (InputStream input = Files.newInputStream(decrypted);
				OutputStream output = Files.newOutputStream(encrypted);
				InputStream key = privateKeyStream()) {
			StreamCodec codec = new BcStreamCodec(() -> product);
			codec.encode(input, output, key, product.identifier(), password);
			return encrypted;
		}
	}

	// From ProductKeys

	private InputStream privateKeyStream() throws Exception {
		Path keysFile = keyBase.resolve(product.identifier() + "_" + product.version() + "." + Keys.xmi.id()); //$NON-NLS-1$ //$NON-NLS-2$
		try (var s = Files.newInputStream(keysFile)) {
			byte[] content = s.readAllBytes();
			KeyPair pair = new EObjectFromBytes<KeyPair>(content, KeyPair.class, XMIResourceImpl::new).get();
			return new ByteArrayInputStream(pair.getScr().getBytes());
		}
	}
}
