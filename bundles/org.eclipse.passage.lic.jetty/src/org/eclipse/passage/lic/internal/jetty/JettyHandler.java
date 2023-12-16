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
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.function.Function;

import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;
import org.eclipse.passage.lic.internal.net.api.handle.NetRequest;
import org.eclipse.passage.lic.internal.net.api.handle.NetResponse;
import org.eclipse.passage.lic.internal.net.api.handle.NetResponse.Error;

/**
 * There is one single instance of the handler for a server. All of the rest in
 * the flow is created per-request, exploited and got rid of.
 * 
 * Thus, it's the only place to keep server's {@code state}. Which, for the
 * floating server, is a persistent grant acquisition ledger.
 */
public final class JettyHandler extends Handler.Abstract {

	private final Function<NetRequest, NetResponse> handler;

	public JettyHandler(Function<NetRequest, NetResponse> handler) {
		Objects.requireNonNull(handler, "JettyHandler::handler"); //$NON-NLS-1$
		this.handler = handler;
	}

	@Override
	public boolean handle(Request request, Response answer, Callback callback) throws Exception {
		NetResponse response = handler.apply(new JettyRequest(request));
		write(response, request, answer, callback);
		return true;

	}

	private void write(NetResponse response, Request request, Response answer, Callback callback) throws IOException {
		if (response.failed()) {
			Error error = response.error();
			Response.writeError(request, answer, callback, error.code(), error.message());
			return;
		}
		answer.setStatus(HttpStatus.OK_200);
		if (!response.carriesPayload()) {
			return;
		}
		byte[] payload;
		try {
			payload = response.payload();
		} catch (Exception e) {
			throw new IOException(e);
		}
		answer.getHeaders().put(HttpHeader.CONTENT_LENGTH, payload.length);
		answer.getHeaders().put(HttpHeader.CONTENT_TYPE, contentType(response) + "; charset=UTF-8"); //$NON-NLS-1$
		answer.write(true, ByteBuffer.wrap(payload), callback);
	}

	private String contentType(NetResponse response) {
		return response.contentType().contentType();
	}

}
