/*******************************************************************************
 * Copyright (c) 2019 ArSysOp and others
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Elena Parovyshnaya <elena.parovyshnaya@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.equinox;

import org.eclipse.osgi.util.NLS;

public class EquinoxMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.lic.equinox.EquinoxMessages"; //$NON-NLS-1$
	public static String EquinoxPaths_uri_retrieval_error;
	public static String EquinoxRestrictions_feature_is_licensed;
	public static String EquinoxRestrictions_feature_is_not_licensed;
	public static String EquinoxRestrictions_title;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, EquinoxMessages.class);
	}

	private EquinoxMessages() {
	}
}
