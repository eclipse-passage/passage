/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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

public class ExportCustomersWizardMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.passage.loc.report.internal.ui.i18n.ExportCustomersWizardMessages"; //$NON-NLS-1$

	public static String ExposedExportCustomersWizard_dialogTitle;
	public static String PreviewPage_description;
	public static String PreviewPage_title;
	public static String PreviewPage_noinfo_company;
	public static String ScopePage_columnProduct;
	public static String ScopePage_columnSelect;
	public static String ScopePage_description;
	public static String ScopePage_title;
	public static String VisibleProgress_task;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, ExportCustomersWizardMessages.class);
	}

	private ExportCustomersWizardMessages() {
	}
}
