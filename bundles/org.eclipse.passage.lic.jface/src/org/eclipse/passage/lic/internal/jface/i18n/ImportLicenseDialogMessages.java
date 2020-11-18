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

public class ImportLicenseDialogMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.lic.internal.jface.i18n.ImportLicenseDialogMessages"; //$NON-NLS-1$
	public static String ImportLicenseDialog_browse_dialog_title;
	public static String ImportLicenseDialog_column_evaluation;
	public static String ImportLicenseDialog_column_feature;
	public static String ImportLicenseDialog_column_period;
	public static String ImportLicenseDialog_import_title;
	public static String ImportLicenseDialog_import_tooltip;
	public static String ImportLicenseDialog_io_error;
	public static String ImportLicenseDialog_lic_read_failed;
	public static String ImportLicenseDialog_path_label;
	public static String ImportLicenseDialog_prelude;
	public static String ImportLicenseDialog_title;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, ImportLicenseDialogMessages.class);
	}

	private ImportLicenseDialogMessages() {
	}
}
