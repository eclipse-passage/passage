/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp and others
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Elena Parovyshnaya <elena.parovyshnaya@gmail.com> - initial API and implementation
 *     ArSysOp - ongoing support
 *******************************************************************************/
package org.eclipse.passage.loc.internal.licenses.ui.i18n;

import org.eclipse.osgi.util.NLS;

public class LicensesUiMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.passage.loc.internal.licenses.ui.i18n.LicensesUiMessages"; //$NON-NLS-1$

	public static String LicenseExportHandler_e_period_invalid;
	public static String LicenseExportHandler_error_message;
	public static String LicenseExportHandler_error_title;
	public static String LicenseExportHandler_period_message;
	public static String LicenseExportHandler_period_title;
	public static String LicenseExportHandler_success_title;
	public static String LicenseExportHandler_success_description;
	public static String LicensesUi_select_license_plan;
	public static String IssuedLicensesReportHandler_unavailableTitle;
	public static String IssuedLicensesReportHandler_unavailableMessage;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, LicensesUiMessages.class);
	}

	private LicensesUiMessages() {
	}
}
