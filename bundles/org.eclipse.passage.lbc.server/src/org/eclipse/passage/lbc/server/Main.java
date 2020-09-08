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
package org.eclipse.passage.lbc.server;

import java.util.Hashtable;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;
import org.eclipse.passage.lbc.server.jetty.JettyServer;
import org.eclipse.passage.lbc.server.jetty.Port;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Main implements BundleActivator, CommandProvider {

	private final JettyServer server = new JettyServer();

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		bundleContext.registerService(CommandProvider.class.getName(), this, new Hashtable<>());
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
	}

	@Override
	public String getHelp() {
		StringBuilder builder = new StringBuilder();
		builder.append("launch - starts server on specific port or on default port (8090) if no port was specified"); //$NON-NLS-1$
		builder.append("terminate - stops server"); //$NON-NLS-1$
		return builder.toString();
	}

	public void _launch(CommandInterpreter interpreter) throws Exception {
		if (server.running()) {
			System.out.println("Server is already running"); //$NON-NLS-1$
			return;
		}
		server.launch(new Port.OfArgument().apply(interpreter.nextArgument()));

	}

	public void _terminate(@SuppressWarnings("unused") CommandInterpreter interpreter) throws Exception {
		if (!server.running()) {
			System.out.println("Server is not running"); //$NON-NLS-1$
			return;
		}
		server.terminate();
	}

}
