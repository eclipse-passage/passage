/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lbc.internal.jetty.i18n;

import org.eclipse.osgi.util.NLS;

public class JettyMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.lbc.internal.jetty.i18n.JettyMessages"; //$NON-NLS-1$
	public static String JettyServerLauncher_e_start;
	public static String JettyServerLauncher_e_start_exists;
	public static String JettyServerLauncher_e_stop;
	public static String JettyServerLauncher_e_stop_not_started;
	public static String JettyServerLauncher_ok_start;
	public static String JettyServerLauncher_ok_stop;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, JettyMessages.class);
	}

	private JettyMessages() {
	}
}
