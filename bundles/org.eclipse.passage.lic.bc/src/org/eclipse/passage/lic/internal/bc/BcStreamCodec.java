package org.eclipse.passage.lic.internal.bc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.io.DigestExpectation;
import org.eclipse.passage.lic.internal.api.io.EncryptionAlgorithm;
import org.eclipse.passage.lic.internal.api.io.EncryptionKeySize;
import org.eclipse.passage.lic.internal.api.io.StreamCodec;

@SuppressWarnings("restriction")
public final class BcStreamCodec implements StreamCodec {

	private final LicensedProduct product;
	private final EncryptionAlgorithm algorithm;
	private final EncryptionKeySize keySize;

	public BcStreamCodec(LicensedProduct product, EncryptionAlgorithm algorithm, EncryptionKeySize keySize) {
		this.product = product;
		this.algorithm = algorithm;
		this.keySize = keySize;
	}

	@Override
	public LicensedProduct id() {
		return product;
	}

	@Override
	public EncryptionAlgorithm algorithm() {
		return algorithm;
	}

	@Override
	public EncryptionKeySize keySize() {
		return keySize;
	}

	@Override
	public void createKeyPair(String publicKeyPath, String privateKeyPath, String username, String password)
			throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void encode(InputStream input, OutputStream output, InputStream key, String username, String password)
			throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void decode(InputStream input, OutputStream output, InputStream key, DigestExpectation digest)
			throws IOException {
		// TODO Auto-generated method stub

	}

}
