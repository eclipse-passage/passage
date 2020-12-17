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
package org.eclipse.passage.lbc.internal.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.passage.lbc.internal.base.Port;
import org.eclipse.passage.lbc.internal.jetty.i18n.Messages;

final class JettyServer {

	private final Logger logger = Log.getLogger(JettyServer.class);

	private Server server;

	void launch(Port port) throws JettyException {
		try {
			server = new Server(port.get().get());
			server.setHandler(new JettyHandler());
			server.start();
			logger.info(String.format(Messages.started, port.get()));
		} catch (Exception exception) {
			throw new JettyException(//
					String.format(Messages.error_onstart, exception.getClass(), exception.getMessage()), //
					exception);
		}
	}

	void terminate() throws JettyException {
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
