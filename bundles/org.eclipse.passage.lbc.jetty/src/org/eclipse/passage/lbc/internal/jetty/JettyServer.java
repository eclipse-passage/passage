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

import java.util.Map;

import org.eclipse.jetty.server.Server;
import org.eclipse.passage.lbc.internal.base.Port;

public final class JettyServer {

	private Server server;

	@SuppressWarnings("restriction")
	public void launch(Map<String, Object> arguments) {
		try {
			server = new Server(new Port(arguments).get().get());
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void terminate() {
		try {
			server.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
