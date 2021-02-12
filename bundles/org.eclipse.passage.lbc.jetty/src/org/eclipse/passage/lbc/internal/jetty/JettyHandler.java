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
import java.io.PrintWriter;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.passage.lbc.internal.base.BaseFlotingRequestHandled;
import org.eclipse.passage.lbc.internal.base.EagerFloatingState;
import org.eclipse.passage.lbc.internal.base.api.FloatingState;
import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.internal.net.handle.NetResponse;

/**
 * There is one single instance of the handler for a server. All of the rest in
 * the flow is created per-request, exploited and got rid of.
 * 
 * Thus, it's the only place to keep server's {@code state}. Which, for the
 * floating server, is a persistent grant acquisition ledger.
 */
final class JettyHandler extends AbstractHandler {

	private final FloatingState state = new EagerFloatingState();

	@Override
	public void handle(String target, Request request, HttpServletRequest wrapper, HttpServletResponse envelope)
			throws IOException, ServletException {
		write(response(wrapper), envelope);
		request.setHandled(true);
	}

	private NetResponse response(HttpServletRequest request) {
		return new BaseFlotingRequestHandled(new JettyRequest(request, state)).get();
	}

	private void write(NetResponse response, HttpServletResponse envelope) throws IOException {
		envelope.setContentType(new ContentType.Xml().contentType());
		envelope.setCharacterEncoding("UTF-8"); //$NON-NLS-1$
		if (response.failed()) {
			envelope.sendError(response.error().code(), response.error().message());
			return;
		}
		envelope.setStatus(HttpServletResponse.SC_OK);
		if (response.carriesPayload()) {
			byte[] payload;
			try {
				payload = response.payload();
			} catch (Exception e) {
				throw new IOException(e);
			}
			envelope.setContentLength(payload.length);
			try (PrintWriter out = envelope.getWriter()) {
				out.write(new String(payload, Charset.forName("UTF-8"))); //$NON-NLS-1$
				out.flush();
			}
			envelope.flushBuffer();
		}
	}

}
