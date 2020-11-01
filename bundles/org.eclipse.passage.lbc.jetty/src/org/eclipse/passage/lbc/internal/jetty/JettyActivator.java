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

import java.util.HashMap;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class JettyActivator implements BundleActivator {

	private final JettyServer jetty;

	public JettyActivator() {
		jetty = new JettyServer();
	}

	@Override
	public void start(BundleContext context) throws Exception {
		// FIXME: extract configuration parameters
		jetty.launch(new HashMap<>());
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		jetty.terminate();
	}

}
