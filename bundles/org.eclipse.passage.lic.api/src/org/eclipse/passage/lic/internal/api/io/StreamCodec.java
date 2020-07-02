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
package org.eclipse.passage.lic.internal.api.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.internal.api.registry.Service;

/**
 * Coder and decoder for licensing data, used by {@link MinedConditions} to read
 * licensing conditions data from encoded streams (like license text)
 */
public interface StreamCodec extends Service<LicensedProduct> {
	/**
	 * Identifier of an encoding algorithm used by the codec.
	 */
	EncryptionAlgorithm algorithm();

	/**
	 * Encoding key size.
	 */
	EncryptionKeySize keySize();

	/**
	 * Create a public/private keys pair and store them to {@code publicKeyPath} and
	 * {@code privateKeyPath} respectively.
	 *
	 * @param publicKeyPath  file system path for <i>public key</i> to be generated
	 * @param privateKeyPath file system path for <i>private key</i> to be generated
	 * @param username       of the keys owner user
	 * @param password       of the keys owner user
	 * @throws IOException in case of any i/o misbehavior
	 */
	void createKeyPair(String publicKeyPath, String privateKeyPath, String username, String password)
			throws IOException;

	/**
	 * Encode {@code input} stream data with a private key retrieved form the given
	 * {@code key} stream. Fill {@code output} stream with the encoded data.
	 *
	 * @param input    source of data to be encoded
	 * @param output   target stream to place encoded data into
	 * @param key      source for a private key
	 * @param username of the private key owner user
	 * @param password of the private key owner user
	 * @throws IOException in case of any i/o misbehavior
	 */
	void encode(InputStream input, OutputStream output, InputStream key, String username, String password)
			throws IOException;

	/**
	 * Decode the {@code input} stream with the <i>public key</i> and store decoded
	 * data to {@code output} stream.
	 *
	 * @param input  source stream with encoded data
	 * @param output target stream for decoded data
	 * @param key    stream containing the <i>public key</i> for decoding
	 * @param digest expected digest for public key source stream {@code key} to be
	 *               validated prior decoding
	 * @throws IOException in case of any i/o denial or misbehavior
	 */
	void decode(InputStream input, OutputStream output, InputStream key, DigestExpectation digest) throws IOException;

}
