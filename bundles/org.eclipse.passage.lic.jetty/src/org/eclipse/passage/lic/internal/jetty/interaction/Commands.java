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

import java.util.List;

import org.eclipse.passage.lic.internal.equinox.ServiceExtensions;
import org.eclipse.passage.lic.internal.jetty.JettyServer;
import org.osgi.framework.BundleContext;

final class Commands {

	private ServerHandles server;

	void register(BundleContext context, JettyServer jetty, String name) {
		registerSelfLicensingCommands(context);
		registerServerHandles(context, jetty, name);
		registerFromExtension(context);
	}

	ServerHandles server() {
		return server;
	}

	private void registerSelfLicensingCommands(BundleContext context) {
		new LicenseStatus().register(context);
		new LicenseRequest().register(context);
	}

	private void registerServerHandles(BundleContext context, JettyServer jetty, String name) {
		this.server = new ServerHandles(jetty, name);
		this.server.register(context);
	}

	private void registerFromExtension(BundleContext context) {
		commands().forEach(command -> command.register(context));
	}

	private List<JettyCommands> commands() {
		return new ServiceExtensions<JettyCommands>(//
				"org.eclipse.passage.lic.jetty", //$NON-NLS-1$
				"commands", //$NON-NLS-1$
				JettyCommands.class)//
						.get();
	}
}
