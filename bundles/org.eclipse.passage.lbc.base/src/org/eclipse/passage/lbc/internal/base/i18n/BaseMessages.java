/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lbc.internal.base.i18n;

import org.eclipse.osgi.util.NLS;

public final class BaseMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.passage.lbc.internal.base.i18n.BaseMessages"; //$NON-NLS-1$

	public static String AssembledConditions_validity_period_type_unknown;
	public static String ReassemblingMiningTool_path_failed;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, BaseMessages.class);
	}

	private BaseMessages() {
	}
}
