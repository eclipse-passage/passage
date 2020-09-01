/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.loc.internal.licenses.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.email.EmailDescriptor;
import org.eclipse.passage.lic.email.Mailing;
import org.eclipse.passage.lic.licenses.LicenseGrantDescriptor;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;
import org.eclipse.passage.loc.internal.api.IssuedLicense;
import org.eclipse.passage.loc.internal.licenses.core.i18n.LicensesCoreMessages;

@SuppressWarnings("restriction")
public class EmailTemplate {

	private final String separator = "%0A"; //$NON-NLS-1$
	private final String dotEml = ".eml"; //$NON-NLS-1$

	private final Mailing mailing;

	public EmailTemplate(Mailing mailing) {
		Objects.requireNonNull(mailing);
		this.mailing = mailing;
	}

	public List<String> details(LicensePackDescriptor licensePack) {
		List<String> builder = new ArrayList<>();
		builder.add(NLS.bind(LicensesCoreMessages.LicenseRequest_request_lbl, licensePack.getRequestIdentifier()));
		builder.add(""); //$NON-NLS-1$
		builder.add(NLS.bind(LicensesCoreMessages.LicenseRequest_plan_lbl, licensePack.getPlanIdentifier()));
		Optional.ofNullable(licensePack.getIdentifier())//
				.ifPresent(x -> builder.add(NLS.bind(LicensesCoreMessages.LicenseRequest_package_lbl, x)));
		builder.add(""); //$NON-NLS-1$
		builder.add(NLS.bind(LicensesCoreMessages.LicenseRequest_product_lbl, licensePack.getProductIdentifier()));
		builder.add(NLS.bind(LicensesCoreMessages.LicenseRequest_product_version_lbl, licensePack.getProductVersion()));
		for (LicenseGrantDescriptor grant : licensePack.getLicenseGrants()) {
			builder.add(NLS.bind(LicensesCoreMessages.LicenseRequest_feature_lbl, grant.getFeatureIdentifier()));
			String conditionExpression = grant.getConditionExpression();
			if (conditionExpression != null && !conditionExpression.isEmpty()) {
				builder.add(NLS.bind(LicensesCoreMessages.LicenseRequest_condition_expr_lbl, conditionExpression));
			}
		}
		builder.add(""); //$NON-NLS-1$
		builder.add(NLS.bind(LicensesCoreMessages.LicenseRequest_user_lbl, licensePack.getUserIdentifier()));
		builder.add(NLS.bind(LicensesCoreMessages.LicenseRequest_user_name_lbl, licensePack.getUserFullName()));
		builder.add(""); //$NON-NLS-1$
		Optional.ofNullable(licensePack.getIssueDate())//
				.ifPresent(x -> builder.add(NLS.bind(LicensesCoreMessages.LicenseRequest_issue_date_lbl, x)));
		builder.add(""); //$NON-NLS-1$
		return builder;
	}

	public String mailTo(LicensePackDescriptor licensePack) {
		StringBuilder builder = new StringBuilder("mailto:"); //$NON-NLS-1$
		builder.append(licensePack.getUserIdentifier());
		builder.append("?subject="); //$NON-NLS-1$
		builder.append(LicensesCoreMessages.LicenseRequest_mailto_subject_lbl);
		builder.append("&body="); //$NON-NLS-1$
		builder.append(NLS.bind(LicensesCoreMessages.LicenseRequest_mailto_appeal_lbl, licensePack.getUserFullName()));
		builder.append(separator);
		builder.append(LicensesCoreMessages.LicenseRequest_mailto_body_base_lbl);
		builder.append(separator).append(separator);
		builder.append(LicensesCoreMessages.LicenseRequest_mailto_body_details_lbl);
		builder.append(separator);
		builder.append(details(licensePack).stream().collect(Collectors.joining(separator)));
		return builder.toString();
	}

	public File createEmlFile(String from, LicensePackDescriptor licensePack, IssuedLicense result) throws IOException {
		File attachment = result.encrypted().toFile();
		File emlFile = new File(attachment.toString() + dotEml);
		try (FileOutputStream stream = new FileOutputStream(emlFile)) {
			Mailing service = mailing;
			EmailDescriptor descriptor = service.createMail(licensePack.getUserIdentifier(), from,
					LicensesCoreMessages.LicenseRequest_mailto_subject_lbl, //
					details(licensePack).stream().collect(Collectors.joining(System.lineSeparator())), //
					Collections.singleton(attachment.getPath()));
			service.writeEml(descriptor, stream, new BiConsumer<String, Throwable>() {
				@Override
				public void accept(String message, Throwable t) {
					Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, message, t);
				}
			});

		} catch (IOException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
			throw e;
		}
		return emlFile;
	}

}
