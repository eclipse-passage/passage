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
package org.eclipse.passage.lic.base.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.eclipse.passage.lic.api.io.StreamCodec;

public class NullStreamCodec implements StreamCodec {

	public static final NullStreamCodec INSTANCE = new NullStreamCodec();

	@Override
	public String getKeyAlgo() {
		return String.valueOf(null);
	}

	@Override
	public int getKeySize() {
		return 0;
	}

	@Override
	public void createKeyPair(String publicKeyPath, String privateKeyPath, String username, String password)
			throws IOException {
		Files.createFile(Paths.get(publicKeyPath));
		Files.createFile(Paths.get(privateKeyPath));
	}

	@Override
	public void encodeStream(InputStream input, OutputStream output, InputStream key, String username, String password)
			throws IOException {
		transfer(input, output);
	}

	@Override
	public Object decodeStream(InputStream input, OutputStream output, InputStream key, byte[] digest)
			throws IOException {
		transfer(input, output);
		return null;
	}

	public static void transfer(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int len;
		while ((len = in.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}
	}

}
