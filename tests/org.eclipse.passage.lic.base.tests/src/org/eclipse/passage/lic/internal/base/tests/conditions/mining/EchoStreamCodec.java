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
package org.eclipse.passage.lic.internal.base.tests.conditions.mining;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.DigestExpectation;
import org.eclipse.passage.lic.api.io.EncryptionAlgorithm;
import org.eclipse.passage.lic.api.io.EncryptionKeySize;
import org.eclipse.passage.lic.api.io.StreamCodec;
import org.eclipse.passage.lic.internal.base.tests.conditions.mining.LocalConditionsTest.Spy;

final class EchoStreamCodec implements StreamCodec {

	private final LicensedProduct product;
	private final Spy spy;

	EchoStreamCodec(LicensedProduct product, Spy spy) {
		this.product = product;
		this.spy = spy;
	}

	@Override
	public LicensedProduct id() {
		return product;
	}

	@Override
	public EncryptionAlgorithm algorithm() {
		return new EncryptionAlgorithm.Of("no-alg"); //$NON-NLS-1$
	}

	@Override
	public EncryptionKeySize keySize() {
		return new EncryptionKeySize.Of(0);
	}

	@Override
	public void createKeyPair(OutputStream publicKey, OutputStream privateKey, String username, String password)
			throws LicensingException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void encode(InputStream input, OutputStream output, InputStream key, String username, String password)
			throws LicensingException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void decode(InputStream input, OutputStream output, InputStream key, DigestExpectation digest)
			throws LicensingException {
		echo(input, output);
	}

	private void echo(InputStream input, OutputStream output) throws LicensingException {
		PrintWriter writer = new PrintWriter(output); // do not close
		LineNumberReader reader = new LineNumberReader(new InputStreamReader(input)); // do not close
		try {
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				writer.println(line);
				spy.decodeLine();
			}
			writer.flush();
		} catch (IOException e) {
			throw new LicensingException("streaming from empty to vacant somehow failed!", e); //$NON-NLS-1$
		}
	}

}
