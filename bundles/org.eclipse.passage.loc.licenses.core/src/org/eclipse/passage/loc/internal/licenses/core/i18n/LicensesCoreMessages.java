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
 *     ArSysOp - ongoing support
 *******************************************************************************/
package org.eclipse.passage.loc.internal.licenses.core.i18n;

import org.eclipse.osgi.util.NLS;

public class LicensesCoreMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.loc.internal.licenses.core.i18n.LicensesCoreMessages"; //$NON-NLS-1$
	public static String LicenseOperatorServiceImpl_export_error;
	public static String LicenseOperatorServiceImpl_export_success;
	public static String LicenseOperatorServiceImpl_private_key_not_found;
	public static String LicenseOperatorServiceImpl_status_invalid_license_pack;
	public static String LicensePackClassifierInitializer_file_name_message;
	public static String LicensePackClassifierInitializer_lic_pack_title;
	public static String LicensePackClassifierInitializer_new_lic_pack_message;
	public static String LicensePackClassifierInitializer_new_lic_pack_name;
	public static String LicensesSelectionCommandAdvisor_select_lic_pack;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, LicensesCoreMessages.class);
	}

	private LicensesCoreMessages() {
	}
}
