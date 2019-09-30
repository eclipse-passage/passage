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
package org.eclipse.passage.loc.internal.licenses.core;

import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.licenses.LicenseGrantDescriptor;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;
import org.eclipse.passage.loc.internal.licenses.core.i18n.LicensesCoreMessages;

public class LicenseMail {

	private static final String MAILTO = "mailto:"; //$NON-NLS-1$
	private static final String MAILTO_SUBJECT = "?subject="; //$NON-NLS-1$
	private static final String MAILTO_BODY = "&body="; //$NON-NLS-1$
	private static final String MAILTO_SEPARATOR = "%0A"; //$NON-NLS-1$

	private final LicensePackDescriptor licensePack;

	public LicenseMail(LicensePackDescriptor licensePack) {
		this.licensePack = licensePack;
	}

	public String getDetails() {
		return getDetails(System.lineSeparator());
	}

	public String getDetails(String separator) {
		if (licensePack == null) {
			return ""; //$NON-NLS-1$
		}
		StringBuilder builder = new StringBuilder();
		builder.append(NLS.bind(LicensesCoreMessages.LicenseRequest_request_lbl, licensePack.getRequestIdentifier()));
		builder.append(separator).append(separator);
		builder.append(NLS.bind(LicensesCoreMessages.LicenseRequest_plan_lbl, licensePack.getPlanIdentifier()));
		builder.append(separator);
		builder.append(NLS.bind(LicensesCoreMessages.LicenseRequest_package_lbl, licensePack.getIdentifier()));
		builder.append(separator);
		builder.append(NLS.bind(LicensesCoreMessages.LicenseRequest_product_lbl, licensePack.getProductIdentifier()));
		builder.append(separator);
		builder.append(
				NLS.bind(LicensesCoreMessages.LicenseRequest_product_version_lbl, licensePack.getProductVersion()));
		builder.append(separator);

		for (LicenseGrantDescriptor grant : licensePack.getLicenseGrants()) {
			builder.append(NLS.bind(LicensesCoreMessages.LicenseRequest_feature_lbl, grant.getFeatureIdentifier()));
			builder.append(separator);
			String conditionExpression = grant.getConditionExpression();
			if (conditionExpression != null && !conditionExpression.isEmpty()) {
				builder.append(NLS.bind(LicensesCoreMessages.LicenseRequest_condition_expr_lbl, conditionExpression));
				builder.append(separator);
			}
		}
		builder.append(separator);
		builder.append(NLS.bind(LicensesCoreMessages.LicenseRequest_user_lbl, licensePack.getUserIdentifier()));
		builder.append(separator);
		builder.append(NLS.bind(LicensesCoreMessages.LicenseRequest_user_name_lbl, licensePack.getUserFullName()));
		builder.append(separator).append(separator);
		builder.append(NLS.bind(LicensesCoreMessages.LicenseRequest_issue_date_lbl, licensePack.getIssueDate()));
		builder.append(separator);
		return builder.toString();
	}

	public String getMailTo() {
		StringBuilder builder = new StringBuilder(MAILTO);
		builder.append(licensePack.getUserIdentifier());
		builder.append(MAILTO_SUBJECT);
		builder.append(LicensesCoreMessages.LicenseRequest_mailto_subject_lbl);
		builder.append(MAILTO_BODY);
		builder.append(NLS.bind(LicensesCoreMessages.LicenseRequest_mailto_appeal_lbl, licensePack.getUserFullName()));
		builder.append(MAILTO_SEPARATOR);
		builder.append(LicensesCoreMessages.LicenseRequest_mailto_body_base_lbl);
		builder.append(MAILTO_SEPARATOR).append(MAILTO_SEPARATOR);
		builder.append(LicensesCoreMessages.LicenseRequest_mailto_body_details_lbl);
		builder.append(MAILTO_SEPARATOR);
		builder.append(getDetails(MAILTO_SEPARATOR));
		return builder.toString();
	}

}
