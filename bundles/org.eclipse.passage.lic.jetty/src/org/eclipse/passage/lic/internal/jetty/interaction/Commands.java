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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.passage.lic.internal.jetty.JettyServer;
import org.osgi.framework.BundleContext;

final class Commands {

	private ServerHandles server;
	private final Logger log = LogManager.getLogger(getClass());

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
		final IExtension[] extensions = Platform.getExtensionRegistry()
				.getExtensionPoint("org.eclipse.passage.lic.jetty", "commands").getExtensions(); //$NON-NLS-1$//$NON-NLS-2$
		for (IExtension extension : extensions) {
			for (IConfigurationElement config : extension.getConfigurationElements()) {
				try {
					JettyCommands created = (JettyCommands) config.createExecutableExtension("class"); //$NON-NLS-1$
					created.register(context);
				} catch (CoreException e) {
					log.error("failed to instanciate command", e); //$NON-NLS-1$
					e.printStackTrace();
				}
			}
		}

	}

}
