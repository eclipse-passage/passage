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
package org.eclipse.passage.loc.internal.users.core;

import org.eclipse.osgi.util.NLS;

public class UsersCoreMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.loc.internal.users.core.UsersCoreMessages"; //$NON-NLS-1$
	public static String UserOriginClassifierInitializer_new_file_message;
	public static String UserOriginClassifierInitializer_object_message;
	public static String UserOriginClassifierInitializer_object_name;
	public static String UserOriginClassifierInitializer_object_title;
	public static String UsersSelectionCommandAdvisor_select_user;
	public static String UsersSelectionCommandAdvisor_select_user_origin;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, UsersCoreMessages.class);
	}

	private UsersCoreMessages() {
	}
}
