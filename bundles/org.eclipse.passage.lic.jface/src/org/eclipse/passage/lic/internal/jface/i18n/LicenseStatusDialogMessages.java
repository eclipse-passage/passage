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
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.internal.jface.i18n;

import org.eclipse.osgi.util.NLS;

public class LicenseStatusDialogMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.lic.internal.jface.i18n.LicenseStatusDialogMessages"; //$NON-NLS-1$
	public static String LicenseStatusDialog_column_id;
	public static String LicenseStatusDialog_column_status;
	public static String LicenseStatusDialog_intention_copy;
	public static String LicenseStatusDialog_intention_copy_tooltip;
	public static String LicenseStatusDialog_intention_import;
	public static String LicenseStatusDialog_intention_import_tooltip;
	public static String LicenseStatusDialog_intention_request;
	public static String LicenseStatusDialog_intention_request_tooltip;
	public static String LicenseStatusDialog_intention_accept;
	public static String LicenseStatusDialog_intention_accept_tooltip;
	public static String LicenseStatusDialog_intention_diagnose;
	public static String LicenseStatusDialog_intention_diagnose_tooltip;
	public static String LicenseStatusDialog_title;
	public static String WithCertificatSummary_error;
	public static String WithCertificatSummary_ok;
	public static String WithCertificatSummary_warning;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, LicenseStatusDialogMessages.class);
	}

	private LicenseStatusDialogMessages() {
	}
}
