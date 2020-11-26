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
package org.eclipse.passage.lbc.internal.jetty;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.passage.lbc.internal.api.FloatingState;
import org.eclipse.passage.lbc.internal.api.RawRequest;

final class JettyRequest implements RawRequest {

	private final HttpServletRequest origin;
	private final FloatingState state;

	JettyRequest(HttpServletRequest origin, FloatingState state) {
		this.origin = origin;
		this.state = state;
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

	@Override
	public FloatingState state() {
		return state;
	}

}
