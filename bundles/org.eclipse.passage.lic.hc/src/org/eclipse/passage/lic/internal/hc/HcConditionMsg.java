/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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

public class HcConditionMsg extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.lic.internal.hc.HcConditionMessages"; //$NON-NLS-1$

	public static String PORT_VALUE_NOT_SPECIFIED_ERROR;
	public static String HOST_VALUE_NOT_SPECIFIED_ERROR;

	static {
		// initialize resource bundles
		NLS.initializeMessages(BUNDLE_NAME, HcConditionMsg.class);
	}
}
