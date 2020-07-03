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
package org.eclipse.passage.lic.internal.bc;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.io.DigestExpectation;
import org.eclipse.passage.lic.internal.api.io.EncryptionAlgorithm;
import org.eclipse.passage.lic.internal.api.io.EncryptionKeySize;
import org.eclipse.passage.lic.internal.api.io.StreamCodec;

@SuppressWarnings("restriction")
public final class BcStreamCodec implements StreamCodec {

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

	@Override
	public void createKeyPair(Path publicKey, Path privateKey, String username, String password)
			throws LicensingException {
		new BcKeyPair( //
				new BcKeyPair.Targets(publicKey, privateKey), //
				new BcKeyPair.EncryptionParameters(algorithm, keySize) //
		).generate(username, password);
	}

	@Override
	public void encode(InputStream input, OutputStream output, InputStream key, String username, String password)
			throws LicensingException {
		new BcEncodedStream(product.get(), input, output)//
				.produce(new BcResidentSecretKey(key, username).get(), password);
	}

	@Override
	public void decode(InputStream input, OutputStream output, InputStream key, DigestExpectation digest)
			throws LicensingException {
		// TODO Auto-generated method stub

	}

}
