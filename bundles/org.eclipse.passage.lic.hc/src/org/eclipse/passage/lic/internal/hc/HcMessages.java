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
package org.eclipse.passage.lic.internal.hc;

import org.eclipse.osgi.util.NLS;

public class HcMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.lic.internal.hc.HcMessages"; //$NON-NLS-1$

	public static String HcConditionMiner_e_host_invalid;
	public static String HcConditionMiner_e_port_invalid;

	public static String HttpRequests_e_host_invalid;
	public static String HttpRequests_e_port_invalid;

	static {
		// initialize resource bundles
		NLS.initializeMessages(BUNDLE_NAME, HcMessages.class);
	}
}
