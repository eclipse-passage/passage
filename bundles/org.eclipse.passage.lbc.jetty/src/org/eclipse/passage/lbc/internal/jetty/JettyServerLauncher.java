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

package org.eclipse.passage.lbc.internal.jetty;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.passage.lbc.api.BackendLauncher;
import org.eclipse.passage.lbc.api.BackendRequestDispatcher;
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.net.LicensingNet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

@Component
public class JettyServerLauncher implements BackendLauncher {

	private static final int JETTY_PORT_DEFAULT = 8080;

	private static Logger logger = Log.getLogger(JettyServerLauncher.class.getName());

	private Server server;

	private Map<String, BackendRequestDispatcher> requestDispatchers = new HashMap<>();

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void bindBackendRequestDispatcher(BackendRequestDispatcher dispatcher, Map<String, Object> context) {
		logger.info(String.format("Bind BackendRequestDispatcher %s with context %s", dispatcher, context)); //$NON-NLS-1$
		String roleId = String.valueOf(context.get(LicensingNet.ROLE));
		requestDispatchers.put(roleId, dispatcher);
	}

	public void unbindBackendRequestDispatcher(BackendRequestDispatcher dispatcher, Map<String, Object> context) {
		logger.info(String.format("Unbind BackendRequestDispatcher %s with context %s", dispatcher, context)); //$NON-NLS-1$
		String roleId = String.valueOf(context.get(LicensingNet.ROLE));
		requestDispatchers.remove(roleId, dispatcher);
	}

	@Override
	public LicensingResult launch(Map<String, Object> arguments) {
		String source = JettyServerLauncher.class.getName();
		if (server != null) {
			return LicensingResults.createError(JettyMessages.JettyServerLauncher_e_start_exists, source, new IllegalStateException());
		}
		// FIXME: extract from arguments
		int port = JETTY_PORT_DEFAULT;
		server = new Server(port);
		try {
			server.setHandler(new JettyRequestHandler(requestDispatchers.values()));
			server.start();
			logger.info(server.getState());
			return LicensingResults.createOK(JettyMessages.JettyServerLauncher_ok_start, source);
		} catch (Exception e) {
			logger.warn(e.getMessage());
			return LicensingResults.createError(JettyMessages.JettyServerLauncher_e_start, source, e);
		}
	}

	@Override
	public LicensingResult terminate() {
		String source = JettyServerLauncher.class.getName();
		if (server == null) {
			return LicensingResults.createError(JettyMessages.JettyServerLauncher_e_stop_not_started, source, new IllegalStateException());
		}
		try {
			server.stop();
			logger.info(server.getState());
			server = null;
			return LicensingResults.createOK(JettyMessages.JettyServerLauncher_ok_stop, source);
		} catch (Exception e) {
			logger.warn(e.getMessage());
			return LicensingResults.createError(JettyMessages.JettyServerLauncher_e_stop, source, e);
		}
	}

}
