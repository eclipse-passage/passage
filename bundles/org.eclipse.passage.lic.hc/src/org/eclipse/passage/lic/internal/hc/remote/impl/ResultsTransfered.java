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
package org.eclipse.passage.lic.internal.hc.remote.impl;

import java.io.InputStream;
import java.net.HttpURLConnection;

import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType;

public final class ResultsTransfered {

	private final byte[] data;
	private final int code;
	private final String message;
	private final ContentType contentType;

	public ResultsTransfered(HttpURLConnection connection) throws Exception {
		code = connection.getResponseCode();
		message = connection.getResponseMessage();
		data = read(connection); // should be eager
		contentType = new ContentType.Of(connection.getHeaderField("Content-Type")); //$NON-NLS-1$
	}

	private byte[] read(HttpURLConnection connection) throws Exception {
		byte[] content = new byte[connection.getContentLength()];
		try (InputStream source = connection.getInputStream()) {
			source.read(content); // read all and close the connection briefly
		}
		return content;
	}

	public ContentType contentType() {
		return contentType;
	}

	public byte[] data() {
		return data;
	}

	public int code() {
		return code;
	}

	public String message() {
		return message;
	}

}
