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
package org.eclipse.passage.lic.internal.jface;

import org.eclipse.osgi.util.NLS;

public class JFaceMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.lic.internal.jface.JFaceMessages"; //$NON-NLS-1$
	public static String BasePageContributor_e_create_page_instance;
	public static String ConditionLocationPage_e_not_available;
	public static String ConditionTypePage_e_not_available;
	public static String RestrictionLevelPage_e_not_available;
	public static String RestrictionLevelPage_select_btn_text;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, JFaceMessages.class);
	}

	private JFaceMessages() {
	}
}
