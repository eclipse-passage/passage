/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.jetty.ui;

import java.util.Hashtable;

import org.eclipse.passage.lic.internal.jetty.JettyServer;
import org.osgi.framework.BundleContext;

final class Commands {

	private ServerHandles server;

	void register(BundleContext context, JettyServer jetty) {
		registerSelfStatus(context);
		registerServerHandles(context, jetty);
	}

	ServerHandles server() {
		return server;
	}

	private void registerSelfStatus(BundleContext context) {
		register(context, new LicenseStatus());
	}

	private void registerServerHandles(BundleContext context, JettyServer jetty) {
		this.server = new ServerHandles(jetty);
		register(context, server);
	}

	private void register(BundleContext context, Command command) {
		Hashtable<String, Object> props = new Hashtable<>();
		props.put("osgi.command.scope", //$NON-NLS-1$
				command.scope().id());
		props.put("osgi.command.function", //$NON-NLS-1$
				command.commands());
		context.registerService(command.getClass().getName(), command, props);
	}

}
