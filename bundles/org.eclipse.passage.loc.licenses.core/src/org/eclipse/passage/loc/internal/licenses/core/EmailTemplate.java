/*******************************************************************************
 * Copyright (c) 2019, 2021 ArSysOp
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
import org.eclipse.passage.lic.licenses.PersonalFeatureGrantDescriptor;
import org.eclipse.passage.lic.licenses.PersonalLicensePackDescriptor;
import org.eclipse.passage.loc.internal.api.IssuedLicense;
import org.eclipse.passage.loc.internal.licenses.core.i18n.LicensesCoreMessages;

public class EmailTemplate {

	private final String separator = "%0A"; //$NON-NLS-1$
	private final String dotEml = ".eml"; //$NON-NLS-1$

	private final Mailing mailing;

	public EmailTemplate(Mailing mailing) {
		Objects.requireNonNull(mailing);
		this.mailing = mailing;
	}

	public List<String> details(PersonalLicensePackDescriptor pack) {
		List<String> builder = new ArrayList<>();
		builder.add(NLS.bind(LicensesCoreMessages.LicenseRequest_request_lbl, pack.getLicense().getIdentifier()));
		builder.add(""); //$NON-NLS-1$
		builder.add(NLS.bind(LicensesCoreMessages.LicenseRequest_plan_lbl, pack.getLicense().getPlan()));
		Optional.ofNullable(pack.getLicense().getIdentifier())//
				.ifPresent(x -> builder.add(NLS.bind(LicensesCoreMessages.LicenseRequest_package_lbl, x)));
		builder.add(""); //$NON-NLS-1$
		builder.add(NLS.bind(LicensesCoreMessages.LicenseRequest_product_lbl,
				pack.getLicense().getProduct().getIdentifier()));
		builder.add(NLS.bind(LicensesCoreMessages.LicenseRequest_product_version_lbl,
				pack.getLicense().getProduct().getVersion()));
		for (PersonalFeatureGrantDescriptor grant : pack.getGrants()) {
			builder.add(NLS.bind(LicensesCoreMessages.LicenseRequest_feature_lbl, grant.getFeature().getIdentifier()));
			String conditionExpression = grant.getUserAuthentication().getExpression();
			if (conditionExpression != null && !conditionExpression.isEmpty()) {
				builder.add(NLS.bind(LicensesCoreMessages.LicenseRequest_condition_expr_lbl, conditionExpression));
			}
		}
		builder.add(""); //$NON-NLS-1$
		builder.add(
				NLS.bind(LicensesCoreMessages.LicenseRequest_user_lbl, pack.getLicense().getUser().getIdentifier()));
		builder.add(NLS.bind(LicensesCoreMessages.LicenseRequest_user_name_lbl, pack.getLicense().getUser().getName()));
		builder.add(""); //$NON-NLS-1$
		Optional.ofNullable(pack.getLicense().getIssueDate())//
				.ifPresent(x -> builder.add(NLS.bind(LicensesCoreMessages.LicenseRequest_issue_date_lbl, x)));
		builder.add(""); //$NON-NLS-1$
		return builder;
	}

	public String mailTo(PersonalLicensePackDescriptor pack) {
		StringBuilder builder = new StringBuilder("mailto:"); //$NON-NLS-1$
		builder.append(pack.getLicense().getUser().getIdentifier());
		builder.append("?subject="); //$NON-NLS-1$
		builder.append(LicensesCoreMessages.LicenseRequest_mailto_subject_lbl);
		builder.append("&body="); //$NON-NLS-1$
		builder.append(
				NLS.bind(LicensesCoreMessages.LicenseRequest_mailto_appeal_lbl, pack.getLicense().getUser().getName()));
		builder.append(separator);
		builder.append(LicensesCoreMessages.LicenseRequest_mailto_body_base_lbl);
		builder.append(separator).append(separator);
		builder.append(LicensesCoreMessages.LicenseRequest_mailto_body_details_lbl);
		builder.append(separator);
		builder.append(details(pack).stream().collect(Collectors.joining(separator)));
		return builder.toString();
	}

	public File createEmlFile(String from, PersonalLicensePackDescriptor licensePack, IssuedLicense result)
			throws IOException {
		File attachment = result.encrypted().toFile();
		File emlFile = new File(attachment.toString() + dotEml);
		try (FileOutputStream stream = new FileOutputStream(emlFile)) {
			Mailing service = mailing;
			EmailDescriptor descriptor = service.createMail(licensePack.getLicense().getUser().getIdentifier(), from,
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
