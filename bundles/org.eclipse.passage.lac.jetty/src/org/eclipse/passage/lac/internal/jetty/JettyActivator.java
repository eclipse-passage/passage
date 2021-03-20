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
package org.eclipse.passage.lac.internal.jetty;

import org.eclipse.core.runtime.Platform;
import org.eclipse.passage.internal.lac.base.AgentRequestHandled;
import org.eclipse.passage.lic.internal.jetty.JettyHandler;
import org.eclipse.passage.lic.internal.jetty.JettyServer;
import org.eclipse.passage.lic.internal.net.connect.Port;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class JettyActivator implements BundleActivator {

	private final JettyServer jetty;

	public JettyActivator() {
		jetty = new JettyServer(this::handler);
	}

	@Override
	public void start(BundleContext context) throws Exception {
		jetty.launch(new Port(Platform.getApplicationArgs(), 8090));
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		jetty.terminate();
	}

	@SuppressWarnings("restriction")
	private JettyHandler handler() {
		return new JettyHandler(request -> new AgentRequestHandled(request).get());
	}

}
