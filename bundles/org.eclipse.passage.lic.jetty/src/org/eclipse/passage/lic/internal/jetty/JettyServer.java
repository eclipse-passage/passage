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
package org.eclipse.passage.lic.internal.jetty;

import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.passage.lic.internal.jetty.i18n.Messages;
import org.eclipse.passage.lic.internal.net.connect.Port;

public final class JettyServer {

	private final Logger logger = Log.getLogger(JettyServer.class);
	private final Supplier<JettyHandler> handler;
	private Server server;

	public JettyServer(Supplier<JettyHandler> handler) {
		Objects.requireNonNull(handler, "JettyServer::handler"); //$NON-NLS-1$
		this.handler = handler;
	}

	public void launch(Port port) throws JettyException {
		try {
			server = new Server(port.get().get());
			server.setHandler(handler.get());
			server.start();
			logger.info(String.format(Messages.started, port.get()));
		} catch (Exception exception) {
			throw new JettyException(//
					String.format(Messages.error_onstart, exception.getClass(), exception.getMessage()), //
					exception);
		}
	}

	public void terminate() throws JettyException {
		try {
			server.stop();
			logger.info(String.format(Messages.stopped));
		} catch (Exception exception) {
			throw new JettyException(//
					String.format(Messages.error_onstop, exception.getClass(), exception.getMessage()), //
					exception);
		}
	}

}
