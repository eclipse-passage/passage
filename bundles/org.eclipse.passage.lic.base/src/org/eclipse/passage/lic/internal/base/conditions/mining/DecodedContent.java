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
package org.eclipse.passage.lic.internal.base.conditions.mining;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.io.DigestExpectation;
import org.eclipse.passage.lic.internal.api.io.KeyKeeper;
import org.eclipse.passage.lic.internal.api.io.StreamCodec;

public final class DecodedContent {

	private final Path source;
	private final KeyKeeper key;
	private final StreamCodec codec;

	public DecodedContent(Path source, KeyKeeper key, StreamCodec codec) {
		this.source = source;
		this.key = key;
		this.codec = codec;
	}

	public byte[] get() throws IOException, LicensingException {
		try (FileInputStream encoded = new FileInputStream(source.toFile());
				ByteArrayOutputStream decoded = new ByteArrayOutputStream();
				InputStream ring = key.productPublicKey()) {
			codec.decode(encoded, decoded, ring, new DigestExpectation.None());
			decoded.flush();
			return decoded.toByteArray();
		}
	}

}
