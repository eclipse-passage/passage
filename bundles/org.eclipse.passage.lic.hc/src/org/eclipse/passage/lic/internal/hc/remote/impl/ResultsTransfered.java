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
import java.util.Collections;

import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType.Of;
import org.eclipse.passage.lic.internal.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.internal.base.diagnostic.code.ServiceFailedOnMorsel;

public final class ResultsTransfered {

	private final byte[] data;
	private final int code;
	private final String message;
	private final ContentType contentType;

	public ResultsTransfered(HttpURLConnection connection) throws Exception {
		code = connection.getResponseCode();
		message = connection.getResponseMessage();
		contentType = readContentType(connection);
		data = readContent(connection); // should be eager
	}

	private Of readContentType(HttpURLConnection connection) {
		return new ContentType.Of(connection.getHeaderField("Content-Type")); //$NON-NLS-1$
	}

	private byte[] readContent(HttpURLConnection connection) throws Exception {
		byte[] content = new byte[connection.getContentLength()];
		try (InputStream source = connection.getInputStream()) {
			if (!successful()) {
				source.read(content); // read all and close the connection briefly
			}
		}
		return content;
	}

	public ContentType contentType() {
		return contentType;
	}

	public byte[] data() {
		return data;
	}

	public boolean successful() {
		return code == HttpURLConnection.HTTP_OK;
	}

	public Diagnostic diagnose() {
		if (successful()) {
			return new BaseDiagnostic();
		}
		return new BaseDiagnostic(//
				Collections.emptyList(), //
				Collections.singletonList(//
						new Trouble(//
								new ServiceFailedOnMorsel(), //
								String.format("%d: %s", code, message)) //$NON-NLS-1$
				));
	}

}
