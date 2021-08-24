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
package org.eclipse.passage.lic.base.restrictions;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.api.diagnostic.TroubleCode;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.api.restrictions.Restriction;
import org.eclipse.passage.lic.base.diagnostic.SumOfLists;
import org.eclipse.passage.lic.base.diagnostic.TroubleExplained;
import org.eclipse.passage.lic.internal.base.i18n.ExaminationExplanedMessages;

//FIXME: work for CachingSupplier
/**
 * 
 * @since 2.1
 */
public final class ExaminationExplained implements Supplier<String> {

	private final ExaminationCertificate certificate;

	public ExaminationExplained(ExaminationCertificate certificate) {
		Objects.requireNonNull(certificate, "ExaminationExplained::certificate"); //$NON-NLS-1$
		this.certificate = certificate;
	}

	@Override
	public String get() {
		StringBuilder out = new StringBuilder();
		List<String> features = features();
		out.append(String.format(//
				ExaminationExplanedMessages.getString("ExaminationExplained.prelude"), //$NON-NLS-1$
				features.size(), //
				date(certificate.stamp()), //
				certificate.restrictions().size()))//
				.append("\r\n"); //$NON-NLS-1$
		appendFeatures(features, out);
		appendRestriction(out);
		appendPermissions(out);
		appendAgreements(out);
		return out.toString();
	}

	private void appendFeatures(List<String> features, StringBuilder out) {
		out.append(String.format(ExaminationExplanedMessages.getString(//
				"ExaminationExplained.features_prelude"), features.size())); //$NON-NLS-1$
		features.forEach(feature -> out//
				.append("\t") //$NON-NLS-1$
				.append(feature)//
				.append("\r\n")); //$NON-NLS-1$
	}

	private void appendRestriction(StringBuilder out) {
		out.append(String.format(ExaminationExplanedMessages.getString(//
				"ExaminationExplained.restrictions_prelude"), certificate.restrictions().size())); //$NON-NLS-1$
		certificate.restrictions().forEach(restriction -> out//
				.append("\t") //$NON-NLS-1$
				.append(ExaminationExplanedMessages.getString("ExaminationExplained.restriction_feature")) //$NON-NLS-1$
				.append(" [") //$NON-NLS-1$
				.append(feature(restriction))//
				.append("] ") //$NON-NLS-1$
				.append(ExaminationExplanedMessages.getString("ExaminationExplained.restriction_reason")) //$NON-NLS-1$
				.append(" [") //$NON-NLS-1$
				.append(reason(restriction.reason()))//
				.append("] ") //$NON-NLS-1$
				.append(ExaminationExplanedMessages.getString("ExaminationExplained.restriction_level")) //$NON-NLS-1$
				.append(" [") //$NON-NLS-1$
				.append(restriction.unsatisfiedRequirement().restrictionLevel())//
				.append("] ") //$NON-NLS-1$
				.append("\r\n") //$NON-NLS-1$
		);
	}

	private String reason(TroubleCode code) {
		return String.format("%d: %s", code.code(), code.explanation());//$NON-NLS-1$
	}

	private void appendPermissions(StringBuilder out) {
		Collection<Requirement> satisfied = certificate.satisfied();
		out.append(String.format(ExaminationExplanedMessages.getString("ExaminationExplained.permissions_prelude"), //$NON-NLS-1$
				satisfied.size()));
		satisfied.stream().map(certificate::satisfaction).forEach(permission -> out //
				.append("\t") //$NON-NLS-1$
				.append(ExaminationExplanedMessages.getString("ExaminationExplained.permission_feature")) //$NON-NLS-1$
				.append(" [") //$NON-NLS-1$
				.append(feature(permission))//
				.append("]\r\n\t\t") //$NON-NLS-1$
				.append(ExaminationExplanedMessages.getString("ExaminationExplained.permission_period")) //$NON-NLS-1$
				.append(" [") //$NON-NLS-1$
				.append(date(permission.leaseDate()))//
				.append(" - ") //$NON-NLS-1$
				.append(date(permission.expireDate()))//
				.append("]\r\n\t\t") //$NON-NLS-1$
				.append(ExaminationExplanedMessages.getString("ExaminationExplained.permission_condition")) //$NON-NLS-1$
				.append(": ") //$NON-NLS-1$
				.append(ExaminationExplanedMessages.getString("ExaminationExplained.permission_condition_version")) //$NON-NLS-1$
				.append("=[") //$NON-NLS-1$
				.append(permission.condition().versionMatch().version()) //
				.append(", ") //$NON-NLS-1$
				.append(permission.condition().versionMatch().rule().identifier()) //
				.append("] ") //$NON-NLS-1$
				.append(ExaminationExplanedMessages.getString("ExaminationExplained.permission_condition_evaluation")) //$NON-NLS-1$
				.append("=[") //$NON-NLS-1$
				.append(permission.condition().evaluationInstructions().type().identifier()).append(", ") //$NON-NLS-1$
				.append(permission.condition().evaluationInstructions().expression()) //
				.append("] ") //$NON-NLS-1$
				.append("\r\n") //$NON-NLS-1$
		);
	}

	private void appendAgreements(StringBuilder out) {
		Collection<AgreementToAccept> agreements = certificate.agreements();
		out.append(String.format("Agreements state contains %d entries.\r\n", agreements.size())); //$NON-NLS-1$
		agreements.forEach(agreement -> {
			out.append("\tagreement [") //$NON-NLS-1$
					.append(agreement.definition().path())//
					.append("]\r\n\tdeclared by [") //$NON-NLS-1$
					.append(agreement.origin())//
					.append("]\r\n\thas [") //$NON-NLS-1$
					.append(agreement.acceptance().accepted() ? "accepted" : "not accepted") //$NON-NLS-1$//$NON-NLS-2$
					.append("] state.\r\n"); //$NON-NLS-1$
			if (agreement.acceptance().error().isPresent()) {
				out.append("\terror:\r\n\t") //$NON-NLS-1$
						.append(new TroubleExplained(agreement.acceptance().error().get()).get())//
						.append("\r\n"); //$NON-NLS-1$
			}
		});
	}

	private List<String> features() {
		List<String> permitted = certificate.satisfied().stream()//
				.map(certificate::satisfaction) //
				.map(this::feature)//
				.distinct() //
				.collect(Collectors.toList());
		List<String> prohibited = certificate.restrictions().stream()//
				.map(this::feature)//
				.distinct() //
				.collect(Collectors.toList());
		return new SumOfLists<String>().apply(permitted, prohibited);
	}

	private String feature(Permission permission) {
		return String.format(ExaminationExplanedMessages.getString("ExaminationExplained.feature_format"), //$NON-NLS-1$
				permission.condition().feature(), //
				permission.condition().versionMatch().version());
	}

	private String feature(Restriction restriction) {
		return String.format(ExaminationExplanedMessages.getString("ExaminationExplained.feature_format"), //$NON-NLS-1$
				restriction.unsatisfiedRequirement().feature().identifier(), //
				restriction.unsatisfiedRequirement().feature().version());
	}

	private String date(ZonedDateTime date) {
		return DateTimeFormatter.RFC_1123_DATE_TIME.format(date);
	}
}
