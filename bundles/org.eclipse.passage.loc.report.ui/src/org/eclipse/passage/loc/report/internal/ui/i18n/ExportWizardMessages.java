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

public class ExportWizardMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.passage.loc.report.internal.ui.i18n.ExportWizardMessages"; //$NON-NLS-1$

	public static String ExportWizard_errorTitle;
	public static String TargetPage_browse;
	public static String TargetPage_description;
	public static String TargetPage_open;
	public static String TargetPage_title;
	public static String ScopePage_selectAll;
	public static String ScopePage_selctNone;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, ExportWizardMessages.class);
	}

	private ExportWizardMessages() {
	}
}
