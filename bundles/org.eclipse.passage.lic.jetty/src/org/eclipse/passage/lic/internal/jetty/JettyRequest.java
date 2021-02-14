/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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

import javax.servlet.http.HttpServletRequest;

import org.eclipse.passage.lic.internal.net.api.handle.NetRequest;

public final class JettyRequest implements NetRequest {

	private final HttpServletRequest origin;

	public JettyRequest(HttpServletRequest origin) {
		Objects.requireNonNull(origin, "JettyRequest::origin"); //$NON-NLS-1$
		this.origin = origin;
	}

	@Override
	public String parameter(String name) {
		return origin.getParameter(name);
	}

	@Override
	public byte[] content() throws IOException {
		byte[] content = new byte[origin.getContentLength()];
		try (InputStream stream = origin.getInputStream()) {
			stream.read(content);
		}
		return content;
	}

}
