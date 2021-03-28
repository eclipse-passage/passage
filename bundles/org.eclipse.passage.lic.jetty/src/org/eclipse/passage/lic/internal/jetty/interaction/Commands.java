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
package org.eclipse.passage.lic.internal.jetty.interaction;

import org.eclipse.passage.lic.internal.jetty.JettyServer;
import org.osgi.framework.BundleContext;

final class Commands {

	private ServerHandles server;

	void register(BundleContext context, JettyServer jetty, String name) {
		registerSelfStatus(context);
		registerServerHandles(context, jetty, name);
	}

	ServerHandles server() {
		return server;
	}

	private void registerSelfStatus(BundleContext context) {
		new LicenseStatus().register(context);
	}

	private void registerServerHandles(BundleContext context, JettyServer jetty, String name) {
		this.server = new ServerHandles(jetty, name);
		this.server.register(context);
	}

}
