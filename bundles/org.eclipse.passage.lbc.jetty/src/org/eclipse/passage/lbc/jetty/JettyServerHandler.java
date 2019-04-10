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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.passage.lbc.runtime.ServerHandler;
import org.eclipse.passage.lbc.runtime.ServerRequestHandler;

public class JettyServerHandler implements ServerHandler {

	private static final int JETTY_PORT_DEFAULT = 8080;

	private static Logger LOG = Logger.getLogger(JettyServerHandler.class.getName());
	private List<ServerRequestHandler> serverHandlers = new ArrayList<>();

	private Server server;

	@Override
	public void launch() {
		server = new Server(JETTY_PORT_DEFAULT);
		try {

			HandlerList handlers = new HandlerList();
			for (ServerRequestHandler handler : serverHandlers) {
				if (handler instanceof Handler) {
					handlers.addHandler((Handler) handler);
				}
			}
			server.setHandler(handlers);
			server.start();
			LOG.info(server.getState());
		} catch (Exception e) {
			LOG.info(e.getMessage());
		}
	}

	@Override
	public void terminate() {
		if (server != null) {
			try {
				server.stop();
				LOG.info(server.getState());
			} catch (Exception e) {
				LOG.info(e.getMessage());
			}
		}
	}

	@Override
	public void addServerRequestHandler(ServerRequestHandler handler) {
		this.serverHandlers.add(handler);
	}

	@Override
	public void remServerRequestHandler(ServerRequestHandler handler) {
		this.serverHandlers.remove(handler);
	}
}
