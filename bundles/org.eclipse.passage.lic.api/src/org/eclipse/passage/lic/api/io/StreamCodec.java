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
package org.eclipse.passage.lic.api.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Objects;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.api.registry.Service;

/**
 * Coder and decoder for licensing data, used by {@link MinedConditions} to read
 * licensing conditions data from encoded streams (like license text)
 * 
 * @since 2.1
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
	 * Create a public/private keys pair and store them to {@code publicKey} stream
	 * and {@code privateKey} stream respectively.
	 *
	 * @param publicKey  a stream for <i>public key</i> content to be stored in
	 * @param privateKey a stream for <i>private key</i> content to be stored in
	 * @param username   of the keys owner user
	 * @param password   of the keys owner password
	 * @throws LicensingException in case of any i/o misbehavior
	 */
	void createKeyPair(OutputStream publicKey, OutputStream privateKey, String username, String password)
			throws LicensingException;

	/**
	 * Encode {@code input} stream data with a private key retrieved form the given
	 * {@code key} stream. Fill {@code output} stream with the encoded data. All the
	 * streams stay opened.
	 *
	 * @param input    source of data to be encoded
	 * @param output   target stream to place encoded data into
	 * @param key      source for a private key
	 * @param username of the private key owner user
	 * @param password of the private key owner password
	 * @throws LicensingException in case of any i/o misbehavior
	 */
	void encode(InputStream input, OutputStream output, InputStream key, String username, String password)
			throws LicensingException;

	/**
	 * Decode the {@code input} stream with the <i>public key</i> and store decoded
	 * data to {@code output} stream. All the streams stay opened at the end of the
	 * call.
	 *
	 * @param input  source stream with encoded data
	 * @param output target stream for decoded data
	 * @param key    stream containing the <i>public key</i> for decoding
	 * @param digest expected digest for public key source stream {@code key} to be
	 *               validated prior decoding
	 * @throws LicensingException in case of any i/o denial or misbehavior
	 */
	void decode(InputStream input, OutputStream output, InputStream key, DigestExpectation digest)
			throws LicensingException;

	public static final class Smart implements StreamCodec {
		private final StreamCodec delegate;

		public Smart(StreamCodec delegate) {
			Objects.requireNonNull(delegate, "StreamCodec.Smart::delegate"); //$NON-NLS-1$
			this.delegate = delegate;
		}

		@Override
		public LicensedProduct id() {
			return delegate.id();
		}

		@Override
		public EncryptionAlgorithm algorithm() {
			return delegate.algorithm();
		}

		@Override
		public EncryptionKeySize keySize() {
			return delegate.keySize();
		}

		@Override
		public void createKeyPair(OutputStream publicKey, OutputStream privateKey, String username, String password)
				throws LicensingException {
			delegate.createKeyPair(publicKey, privateKey, username, password);
		}

		/**
		 * Create a public/private keys pair and store them to {@code publicKey} path
		 * and {@code privateKey} path respectively.
		 *
		 * @param publicKey  file system path for <i>public key</i> to be generated
		 * @param privateKey file system path for <i>private key</i> to be generated
		 * @param username   of the keys owner user
		 * @param password   of the keys owner password
		 * @throws LicensingException in case of any i/o misbehavior
		 */
		public void createKeyPair(Path publicKey, Path privateKey, String username, String password)
				throws LicensingException {
			try (FileOutputStream pub = new FileOutputStream(publicKey.toFile()); //
					FileOutputStream scr = new FileOutputStream(privateKey.toFile())) {
				delegate.createKeyPair(pub, scr, username, password);
			} catch (IOException e) {
				throw new LicensingException("failed to create encryption keys", e); //$NON-NLS-1$
			}

		}

		@Override
		public void encode(InputStream input, OutputStream output, InputStream key, String username, String password)
				throws LicensingException {
			delegate.encode(input, output, key, username, password);
		}

		@Override
		public void decode(InputStream input, OutputStream output, InputStream key, DigestExpectation digest)
				throws LicensingException {
			delegate.decode(input, output, key, digest);
		}

	}

}
