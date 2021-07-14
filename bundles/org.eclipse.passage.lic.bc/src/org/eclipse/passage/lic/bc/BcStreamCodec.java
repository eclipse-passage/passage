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
package org.eclipse.passage.lic.bc;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.Security;
import java.util.Objects;
import java.util.function.Supplier;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.DigestExpectation;
import org.eclipse.passage.lic.api.io.EncryptionAlgorithm;
import org.eclipse.passage.lic.api.io.EncryptionKeySize;
import org.eclipse.passage.lic.api.io.StreamCodec;

/**
 * @since 1.1
 */
public final class BcStreamCodec implements StreamCodec {

	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	private final Supplier<LicensedProduct> product;
	private final EncryptionAlgorithm algorithm;
	private final EncryptionKeySize keySize;

	public BcStreamCodec(Supplier<LicensedProduct> product, EncryptionAlgorithm algorithm, EncryptionKeySize keySize) {
		this.product = product;
		this.algorithm = algorithm;
		this.keySize = keySize;
	}

	public BcStreamCodec(Supplier<LicensedProduct> product) {
		this(product, new EncryptionAlgorithm.Default(), new EncryptionKeySize.Default());
	}

	@Override
	public LicensedProduct id() {
		return product.get();
	}

	@Override
	public EncryptionAlgorithm algorithm() {
		return algorithm;
	}

	@Override
	public EncryptionKeySize keySize() {
		return keySize;
	}

	@SuppressWarnings("resource")
	@Override
	public void createKeyPair(OutputStream publicKey, OutputStream privateKey, String username, String password)
			throws LicensingException {
		Objects.requireNonNull(publicKey, "BcStreamCodec::createKeyPair::publicKey"); //$NON-NLS-1$
		Objects.requireNonNull(privateKey, "BcStreamCodec::createKeyPair::privateKey"); //$NON-NLS-1$
		Objects.requireNonNull(username, "BcStreamCodec::createKeyPair::username"); //$NON-NLS-1$
		Objects.requireNonNull(password, "BcStreamCodec::createKeyPair::password"); //$NON-NLS-1$
		new BcKeyPair( //
				new BcKeyPair.Targets(publicKey, privateKey), //
				new BcKeyPair.EncryptionParameters(algorithm, keySize) //
		).generate(username, password);
	}

	@SuppressWarnings("resource")
	@Override
	public void encode(InputStream input, OutputStream output, InputStream key, String username, String password)
			throws LicensingException {
		Objects.requireNonNull(input, "BcStreamCodec::encode::input"); //$NON-NLS-1$
		Objects.requireNonNull(output, "BcStreamCodec::encode::output"); //$NON-NLS-1$
		Objects.requireNonNull(key, "BcStreamCodec::encode::key"); //$NON-NLS-1$ ;
		Objects.requireNonNull(username, "BcStreamCodec::encode::username"); //$NON-NLS-1$
		Objects.requireNonNull(password, "BcStreamCodec::encode::password"); //$NON-NLS-1$
		new BcEncodedStream(product.get(), input, output)//
				.produce(new BcResidentSecretKey(key, username).get(), password);
	}

	@SuppressWarnings("resource")
	@Override
	public void decode(InputStream input, OutputStream output, InputStream key, DigestExpectation digest)
			throws LicensingException {
		Objects.requireNonNull(input, "BcStreamCodec::decode::input"); //$NON-NLS-1$
		Objects.requireNonNull(output, "BcStreamCodec::decode::output"); //$NON-NLS-1$
		Objects.requireNonNull(key, "BcStreamCodec::decode::key"); //$NON-NLS-1$ ;
		Objects.requireNonNull(digest, "BcStreamCodec::decode::digest"); //$NON-NLS-1$
		new BcDecodedStream(product.get(), input, output)//
				.produce(key, digest);
	}

}
