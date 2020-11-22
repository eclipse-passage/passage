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
package org.eclipse.passage.lbc.internal.jetty.tobemoved;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.passage.lbc.internal.api.tobemoved.FloatingResponse;
import org.eclipse.passage.lbc.internal.base.tobemoved.BaseFlotingRequestHandled;
import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType;

public final class JettyHandler extends AbstractHandler {

	@Override
	public void handle(String target, Request request, HttpServletRequest wrapper, HttpServletResponse envelope)
			throws IOException, ServletException {
		write(response(wrapper), envelope);
	}

	private FloatingResponse response(HttpServletRequest request) {
		return new BaseFlotingRequestHandled(new JettyRequest(request)).get();
	}

	private void write(FloatingResponse response, HttpServletResponse envelope) throws IOException {
		if (response.failed()) {
			envelope.sendError(response.error().code(), response.error().message());
			return;
		}
		try (ServletOutputStream stream = envelope.getOutputStream()) {
			response.write(stream);
		}
		envelope.setContentType(new ContentType.Xml().contentType());
		envelope.setStatus(HttpServletResponse.SC_OK);
	}
}
