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
 *      ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.report.internal.ui.i18n;

import org.eclipse.osgi.util.NLS;

public class ExportLicenseReportWizardMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.passage.loc.report.internal.ui.i18n.ExportLicenseReportWizardMessages"; //$NON-NLS-1$

	public static String ExposedIssuedLicensesReportWizard_dialogTitle;

	public static String PreviewPage_description;

	public static String PreviewPage_explained;
	public static String PreviewPage_path;

	public static String PreviewPage_period;
	public static String PreviewPage_title;

	public static String PlansPage_columnId;
	public static String PlansPage_columnName;
	public static String PlansPage_title;
	public static String PlansPage_description;

	public static String ConfigPage_title;
	public static String ConfigPage_description;
	public static String ConfigPage_dateFrom_title;
	public static String ConfigPage_dateTo_title;

	public static String ConfigPage_explain_title;
	public static String ConfigPage_explain_tooltip;
	public static String ConfigPage_invalidDates;

	public static String VisibleProgress_task;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, ExportLicenseReportWizardMessages.class);
	}

	private ExportLicenseReportWizardMessages() {
	}
}
