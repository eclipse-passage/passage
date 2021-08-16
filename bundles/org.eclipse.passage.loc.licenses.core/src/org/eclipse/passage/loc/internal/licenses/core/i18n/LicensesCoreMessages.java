/*******************************************************************************
 * Copyright (c) 2019, 2021 ArSysOp and others
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
package org.eclipse.passage.loc.internal.licenses.core.i18n;

import org.eclipse.osgi.util.NLS;

public final class LicensesCoreMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.passage.loc.internal.licenses.core.i18n.LicensesCoreMessages"; //$NON-NLS-1$

	public static String EmfObjectPersisted_failed;
	public static String LicenseOperatorServiceImpl_error_io;
	public static String LicenseOperatorServiceImpl_export_error;
	public static String LicenseOperatorServiceImpl_export_success;
	public static String LicenseOperatorServiceImpl_failed_to_save_decoded;
	public static String LicenseOperatorServiceImpl_status_invalid_licensing_request;
	public static String LicenseOperatorServiceImpl_w_no_encoding;
	public static String LicensesSelectionCommandAdvisor_select_lic_plan;
	public static String LicenseDomain_instance_duplication_message;
	public static String LicenseRequest_package_lbl;
	public static String LicenseRequest_plan_lbl;
	public static String LicenseRequest_request_lbl;
	public static String LicenseRequest_product_lbl;
	public static String LicenseRequest_product_version_lbl;
	public static String LicenseRequest_feature_lbl;
	public static String LicenseRequest_condition_expr_lbl;
	public static String LicenseRequest_user_lbl;
	public static String LicenseRequest_user_name_lbl;
	public static String LicenseRequest_issue_date_lbl;
	public static String LicenseRequest_mailto_subject_lbl;
	public static String LicenseRequest_mailto_appeal_lbl;
	public static String LicenseRequest_mailto_body_base_lbl;
	public static String LicenseRequest_mailto_body_details_lbl;
	public static String LicenseRequest_error_attachment_not_exist;
	public static String LicenseOperatorServiceImpl_floating_no_codec;
	public static String LicenseOperatorServiceImpl_floating_save_decoded_failed;
	public static String LicenseOperatorServiceImpl_floating_save_encoded_failed;
	public static String LicenseOperatorServiceImpl_floating_save_product_key;
	public static String LicenseOperatorServiceImpl_floating_save_encoded_file_error;
	public static String LicenseOperatorServiceImpl_failed_to_find_agreement_file;
	public static String LicenseOperatorServiceImpl_failed_to_attach_agreement;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, LicensesCoreMessages.class);
	}

	private LicensesCoreMessages() {
	}
}
