/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lbc.jetty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.passage.lbc.runtime.ServerRequestExecutor;
import org.eclipse.passage.lbc.runtime.ServerRequestHandler;

public class JettyRequestHandler extends AbstractHandler implements ServerRequestHandler {

	private List<ServerRequestExecutor> serverRequestExecutors = new ArrayList<>();

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		for (ServerRequestExecutor requestExecutor : serverRequestExecutors) {
			if (requestExecutor.checkAccesstMode(baseRequest)) {
				requestExecutor.executeRequest(request, response);
				baseRequest.setHandled(true);
			}
		}
	}

	@Override
	public void addRequestExecutor(ServerRequestExecutor executor) {
		if (!serverRequestExecutors.contains(executor)) {
			serverRequestExecutors.add(executor);
		}
	}

	@Override
	public void remRequestExecutor(ServerRequestExecutor executor) {
		if (serverRequestExecutors.contains(executor)) {
			serverRequestExecutors.remove(executor);
		}
	}
}
