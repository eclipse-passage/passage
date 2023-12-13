/*******************************************************************************
 * Copyright (c) 2021, 2023 ArSysOp
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
package org.eclipse.passage.lic.internal.jetty;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import org.eclipse.jetty.http.HttpField;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.server.Request;
import org.eclipse.passage.lic.internal.net.api.handle.NetRequest;

public final class JettyRequest implements NetRequest {

	private final Request origin;

	public JettyRequest(Request origin) {
		Objects.requireNonNull(origin, "JettyRequest::origin"); //$NON-NLS-1$
		this.origin = origin;
	}

	@Override
	public String parameter(String name) {
		//FIXME: AF: most probably this is it
		return Request.extractQueryParameters(origin).getValue(name);
	}

	@Override
	public String remoteAddress() {
		return Request.getRemoteAddr(origin);
	}

	@Override
	public byte[] content() throws IOException {
		//FIXME: AF: field can be null
		HttpField field = origin.getHeaders().getField(HttpHeader.CONTENT_LENGTH);
		//FIXME: AF: they expect long here
		long length = field.getLongValue();
		byte[] content = new byte[(int) length];
		try (InputStream stream = Request.asInputStream(origin)) {
			stream.read(content);
		}
		return content;
	}
	
}
