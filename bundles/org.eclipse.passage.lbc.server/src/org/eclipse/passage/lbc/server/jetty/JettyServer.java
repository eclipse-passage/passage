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
package org.eclipse.passage.lbc.server.jetty;

import java.util.Collections;
import java.util.Map;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jetty.server.Server;
import org.eclipse.passage.lbc.server.Dispatcher;
import org.eclipse.passage.lbc.server.Port;

public final class JettyServer implements IApplication {

	private Server server;

	@Override
	@SuppressWarnings("restriction")
	public Object start(IApplicationContext context) {
		try {
			Map<String, Object> arguments = arguments(context);
			server = new Server(new Port(arguments).get().get());
			server.setHandler(new Handler(new Dispatcher(arguments).get().get()));
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
			return Integer.valueOf(-1);
		}
		return EXIT_OK;
	}

	private Map<String, Object> arguments(IApplicationContext context) {
		// FIXME: provide a way to read arguments from configuration
		return Collections.emptyMap();
	}

	@Override
	public void stop() {
		try {
			server.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
