/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.passage.lbc.api.BackendRequestDispatcher;

class JettyRequestHandler extends AbstractHandler {

	private final List<BackendRequestDispatcher> requestDispatchers = new ArrayList<>();

	JettyRequestHandler(Iterable<BackendRequestDispatcher> dispatchers) {
		dispatchers.forEach(requestDispatchers::add);
	}

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		for (BackendRequestDispatcher requestExecutor : requestDispatchers) {
			if (requestExecutor.canDispatchRequest(baseRequest)) {
				requestExecutor.dispatchRequest(request, response);
				baseRequest.setHandled(true);
			}
		}
	}

}
