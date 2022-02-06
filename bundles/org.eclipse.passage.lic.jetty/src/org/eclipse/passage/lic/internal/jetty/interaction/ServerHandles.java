/*******************************************************************************
 * Copyright (c) 2021, 2022 ArSysOp
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
package org.eclipse.passage.lic.internal.jetty.interaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.passage.lic.equinox.access.LicenseProtection;
import org.eclipse.passage.lic.internal.jetty.JettyException;
import org.eclipse.passage.lic.internal.jetty.JettyServer;
import org.eclipse.passage.lic.internal.net.connect.Port;

final class ServerHandles extends Command {

	private final Logger log = LogManager.getLogger(getClass());
	private final JettyServer server;
	private final Port port;
	private final LicenseProtection license = new LicenseProtection();

	ServerHandles(JettyServer server, String name) {
		super(//
				new Scope.Of(name), //
				new String[] { //
						"start", //$NON-NLS-1$
						"stop", //$NON-NLS-1$
						"restart", //$NON-NLS-1$
						"state" //$NON-NLS-1$
				});
		this.server = server;
		this.port = new Port(8090);
	}

	public void start() {
		if (!license.check()) {
			return;
		}
		try {
			server.launch(port);
		} catch (JettyException e) {
			log.error("failed to launch Jetty server", e); //$NON-NLS-1$
		}
	}

	public void stop() {
		license.release();
		try {
			server.terminate();
		} catch (JettyException e) {
			log.error("failed to terminate Jetty server", e); //$NON-NLS-1$
		}
	}

	public void restart() {
		stop();
		start();
	}

	public void state() {
		try {
			System.out.println(server.state());
		} catch (JettyException e) {
			log.error("failed to report state of Jetty server", e); //$NON-NLS-1$
		}
	}

}
