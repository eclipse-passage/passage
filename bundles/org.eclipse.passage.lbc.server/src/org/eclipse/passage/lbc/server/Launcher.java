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
import org.eclipse.passage.lbc.internal.base.Port;
import org.eclipse.passage.lbc.server.i18n.Messages;
import org.eclipse.passage.lbc.server.jetty.JettyServer;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public final class Launcher implements BundleActivator, CommandProvider {

	private final JettyServer server = new JettyServer();

	@Override
	public void start(BundleContext context) throws Exception {
		context.registerService(CommandProvider.class.getName(), this, new Hashtable<>());
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		server.terminate();
	}

	/**
	 * Provides launch command for OSGi framework CLI
	 * 
	 * CommandProvider's naming conventions requires underscore in the beginning of
	 * the command
	 * 
	 * @param interpreter framework's command interpreter
	 * @throws Exception if there is a framework error
	 */
	public void _launch(CommandInterpreter interpreter) throws Exception {
		server.launch(port(interpreter));
	}

	/**
	 * Provides terminate command for OSGi framework CLI
	 * 
	 * CommandProvider's naming conventions requires underscore in the beginning of
	 * the command
	 * 
	 * @param interpreter framework's command interpreter
	 * @throws Exception if there is a framework error
	 */
	public void _terminate(CommandInterpreter interpreter) throws Exception {
		server.terminate();
	}

	@Override
	public String getHelp() {
		return new StringBuilder() //
				.append(String.format(Messages.launch, new Port.Default().get())) //
				.append(Messages.terminate).toString();
	}

	private Port port(CommandInterpreter interpreter) {
		return new Port.OfArgument(interpreter.nextArgument()).get();
	}
}
