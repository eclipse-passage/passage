/*******************************************************************************
 * Copyright (c) 2020, 2022 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation, further support
 *******************************************************************************/
package org.eclipse.passage.lic.internal.jetty.interaction;

import org.eclipse.passage.lic.internal.jetty.JettyHandler;
import org.eclipse.passage.lic.internal.jetty.JettyServer;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public abstract class LicensedJettyActivator implements BundleActivator {

	private final JettyServer jetty;
	private ServerHandles server;

	public LicensedJettyActivator() {
		configureLogging();
		this.jetty = new JettyServer(this::handler);
	}

	@Override
	public final void start(BundleContext context) throws Exception {
		registerCommands(context);
		server.start(); // temporal coupling here: server is initialized during Commands registration
	}

	@Override
	public final void stop(BundleContext context) throws Exception {
		server.stop();
	}

	protected abstract void configureLogging();

	private void registerCommands(BundleContext context) {
		Commands commands = new Commands();
		commands.register(context, jetty, name());
		this.server = commands.server();
		registerCustomCommands(context);
	}

	protected abstract String name();

	protected abstract JettyHandler handler();

	protected abstract void registerCustomCommands(BundleContext context);

}
