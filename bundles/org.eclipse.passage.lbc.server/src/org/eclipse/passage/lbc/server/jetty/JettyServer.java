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
package org.eclipse.passage.lbc.server.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.passage.lbc.server.i18n.Messages;

public final class JettyServer {

	private Server server;

	public void launch(Port port) {
		try {
			server = new Server(port.get());
			server.setHandler(new Handler());
			server.start();
			System.out.println(String.format(Messages.started, port.get()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void terminate() {
		try {
			server.stop();
			System.out.println(String.format(Messages.stopped));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean running() {
		return server != null && server.isRunning();
	}

}
