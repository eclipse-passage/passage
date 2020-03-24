/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.loc.internal.users.ui.i18n;

import org.eclipse.osgi.util.NLS;

public class UsersUiMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.loc.internal.users.ui.i18n.UsersUiMessages"; //$NON-NLS-1$

	public static String ExportCustomersHandler_unavailableMessage;
	public static String ExportCustomersHandler_unavailableTitle;
	public static String SelectUser_title;
	public static String SelectUserOrigin_title;
	public static String UsersUi_select_user;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, UsersUiMessages.class);
	}

	private UsersUiMessages() {
	}
}
