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
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.passage.lbc.runtime.BackendLauncher;
import org.eclipse.passage.lbc.runtime.ServerRequestHandler;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.runtime.LicensingResult;

public class JettyServerHandler implements BackendLauncher {

	private static final int JETTY_PORT_DEFAULT = 8080;

	private static Logger LOG = Logger.getLogger(JettyServerHandler.class.getName());
	private List<ServerRequestHandler> serverHandlers = new ArrayList<>();

	private Server server;

	@Override
	public LicensingResult launch(Map<String, Object> arguments) {
		String source = JettyServerHandler.class.getName();
		if (server != null) {
			return LicensingResults.createError("Jetty start: already exists", source, new IllegalStateException());
		}
		// FIXME: extract from arguments
		int port = JETTY_PORT_DEFAULT;
		server = new Server(port);
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
			return LicensingResults.createOK("Jetty start: OK", source);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			return LicensingResults.createError("Jetty start: Error", source, e);
		}
	}

	@Override
	public LicensingResult terminate() {
		String source = JettyServerHandler.class.getName();
		if (server == null) {
			return LicensingResults.createError("Jetty stop: not started", source, new IllegalStateException());
		}
		try {
			server.stop();
			LOG.info(server.getState());
			server = null;
			return LicensingResults.createOK("Jetty stop: OK", source);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			return LicensingResults.createError("Jetty stop: Error", source, e);
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
