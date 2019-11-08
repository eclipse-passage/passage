/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.api.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.passage.lic.api.conditions.ConditionMiner;

/**
 * Coder and decoder for licensing data, used by {@link ConditionMiner}
 *
 * @since 0.4.0
 */
public interface StreamCodec {

	/**
	 * Key of encoding algorithm.
	 *
	 * @since 0.4.0
	 */
	String getKeyAlgo();

	/**
	 * Encoding key size.
	 *
	 * @since 0.4.0
	 */
	int getKeySize();

	/**
	 * Create a public/private keys pair and store them to {@code publicKeyPath} and {@code privateKeyPath} respectively.
	 *
	 * @param publicKeyPath  file system path for <i>public key</i> to be generated
	 * @param privateKeyPath file system path for <i>private key</i> to be generated
	 * @param username       of the keys owner user
	 * @param password       of the keys owner user
	 * @throws IOException in case of any i/o misbehaviour
	 * @since 0.4.0
	 */
	void createKeyPair(String publicKeyPath, String privateKeyPath, String username, String password)
			throws IOException;

	/**
	 * Encode {@code input} stream data with a private key retrieved form the given {@code key} stream.
	 * Fill {@code output} stream with the encoded data.
	 *
	 * @param input    source of data to be encoded
	 * @param output   target stream to place encoded data into
	 * @param key      source for a private key
	 * @param username of the private key owner user
	 * @param password of the private key owner user
	 * @throws IOException in case of any i/o misbehaviour
	 * @since 0.4.0
	 */
	void encodeStream(InputStream input, OutputStream output, InputStream key, String username, String password)
			throws IOException;

	/**
	 * Decode the {@code input} stream with the <i>public key</i> and store decoded data to {@code output} stream.
	 *
	 * @param input  source stream with encoded data
	 * @param output target stream for decoded data
	 * @param key    stream containing the <i>public key</i> for decoding
	 * @param digest expected digest for еру public key source stream {@code key} to be validated prior decoding
	 * @throws IOException in case of any i/o denial or misbehaviour
	 * @since 0.4.0
	 */
	Object decodeStream(InputStream input, OutputStream output, InputStream key, byte[] digest) throws IOException;

}
