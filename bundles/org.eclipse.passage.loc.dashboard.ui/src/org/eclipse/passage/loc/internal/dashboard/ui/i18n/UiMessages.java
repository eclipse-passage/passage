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
package org.eclipse.passage.loc.internal.dashboard.ui.i18n;

import org.eclipse.osgi.util.NLS;

public class UiMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.loc.internal.dashboard.ui.i18n.UiMessages"; //$NON-NLS-1$
	public static String DashboardDetailsPart_feature_create;
	public static String DashboardDetailsPart_feature_description;
	public static String DashboardDetailsPart_feature_set_create;
	public static String DashboardDetailsPart_feature_set_description;
	public static String DashboardDetailsPart_feature_version_create;
	public static String DashboardDetailsPart_feature_version_description;
	public static String DashboardDetailsPart_license_pack_create;
	public static String DashboardDetailsPart_license_pack_description;
	public static String DashboardDetailsPart_product_create;
	public static String DashboardDetailsPart_product_description;
	public static String DashboardDetailsPart_product_line_create;
	public static String DashboardDetailsPart_product_line_description;
	public static String DashboardDetailsPart_product_version_create;
	public static String DashboardDetailsPart_product_version_description;
	public static String DashboardDetailsPart_product_version_feature_create;
	public static String DashboardDetailsPart_product_version_feature_description;
	public static String DashboardDetailsPart_title;
	public static String DashboardDetailsPart_user_create;
	public static String DashboardDetailsPart_user_description;
	public static String DashboardDetailsPart_user_origin_create;
	public static String DashboardDetailsPart_user_origin_description;
	public static String DashboardDetailsPart_welcome;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, UiMessages.class);
	}

	private UiMessages() {
	}
}
