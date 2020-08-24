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
package org.eclipse.passage.lic.internal.jface.i18n;

import org.eclipse.osgi.util.NLS;

public class EnvironmentStateDialogMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.lic.internal.jface.i18n.EnvironmentStateDialogMessages"; //$NON-NLS-1$
	public static String EnvironmentStatesDialog_env_error;
	public static String EnvironmentStatesDialog_title;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, EnvironmentStateDialogMessages.class);
	}

	private EnvironmentStateDialogMessages() {
	}
}
