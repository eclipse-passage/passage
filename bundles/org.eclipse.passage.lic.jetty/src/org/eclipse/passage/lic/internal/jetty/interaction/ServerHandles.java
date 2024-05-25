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

import java.util.Arrays;
import java.util.List;

import org.eclipse.passage.lic.cli.LicenseProtection;
import org.eclipse.passage.lic.internal.jetty.JettyException;
import org.eclipse.passage.lic.internal.jetty.JettyServer;
import org.eclipse.passage.lic.internal.net.connect.BindAddress;
import org.eclipse.passage.lic.internal.net.connect.Port;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class ServerHandles extends Command {

	private final Logger log = LoggerFactory.getLogger(getClass());
	private final JettyServer server;
	private final LicenseProtection license = new LicenseProtection();
	private final BindAddress listen;
	private final Port port;

	ServerHandles(JettyServer server, String name) {
		super(new Scope.Of(name), new Handlers().get());
		this.server = server;
		this.listen = new BindAddress("0.0.0.0"); //$NON-NLS-1$
		this.port = new Port(8090);
	}

	public void start() {
		if (!license.check()) {
			return;
		}
		try {
			server.launch(listen, port);
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

	// TODO: l10n
	public void state() {
		try {
			System.out.println(server.state() + " on " + where()); //$NON-NLS-1$
		} catch (JettyException e) {
			log.error("failed to report state of Jetty server", e); //$NON-NLS-1$
		}
	}

	private String where() {
		return listen.get().orElse("-") //$NON-NLS-1$
				+ ":" //$NON-NLS-1$
				+ port.get().map(i -> i.toString()).orElse("-"); //$NON-NLS-1$
	}

	public ServerHandles(Scope scope, String[] names, JettyServer server, BindAddress listen, Port port) {
		super(scope, names);
		this.server = server;
		this.listen = listen;
		this.port = port;
	}

	@Override
	protected List<String> commands() {
		return new Handlers().get();
	}

	@Override // TODO: l10n
	public String usage() {
		return "Change ot report running state of the server"; //$NON-NLS-1$
	}

	private static final class Handlers extends Name {

		protected Handlers() {
			super(Arrays.asList( //
					"start", //$NON-NLS-1$
					"stop", //$NON-NLS-1$
					"restart", //$NON-NLS-1$
					"state" //$NON-NLS-1$
			));
		}

	}

}
