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
 *     Hannes Wellmann (IILS mbH) - Complete Migration to Jetty-12
 *******************************************************************************/
package org.eclipse.passage.lic.internal.jetty;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.util.Fields;
import org.eclipse.passage.lic.internal.net.api.handle.NetRequest;

public final class JettyRequest implements NetRequest {

	private final Request origin;
	private final Fields parameters;

	public JettyRequest(Request origin) {
		Objects.requireNonNull(origin, "JettyRequest::origin"); //$NON-NLS-1$
		this.origin = origin;
		this.parameters = Request.extractQueryParameters(origin);
	}

	@Override
	public String parameter(String name) {
		return parameters.getValue(name);
	}

	@Override
	public byte[] content() throws IOException {
		byte[] content = new byte[(int) origin.getLength()];
		try (InputStream stream = Request.asInputStream(origin)) {
			stream.read(content);
		}
		return content;
	}

}
