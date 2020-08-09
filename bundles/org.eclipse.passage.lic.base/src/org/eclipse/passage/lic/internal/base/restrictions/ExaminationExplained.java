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
package org.eclipse.passage.lic.internal.base.restrictions;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.diagnostic.TroubleCode;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.api.restrictions.Restriction;
import org.eclipse.passage.lic.internal.base.diagnostic.SumOfLists;
import org.eclipse.passage.lic.internal.base.i18n.ExaminationExplanedMessages;

//FIXME: work for CachingSupplier
@SuppressWarnings("restriction")
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
				date(), //
				certificate.restrictions().size()))//
				.append("\r\n"); //$NON-NLS-1$
		appendFeatures(features, out);
		appendRestriction(out);
		appendPermissions(out);
		return out.toString();
	}

	private void appendFeatures(List<String> features, StringBuilder out) {
		out.append(ExaminationExplanedMessages.getString("ExaminationExplained.features_prelude")); //$NON-NLS-1$
		features.forEach(feature -> out//
				.append("\t") //$NON-NLS-1$
				.append(feature)//
				.append("\r\n")); //$NON-NLS-1$
	}

	private void appendRestriction(StringBuilder out) {
		out.append(ExaminationExplanedMessages.getString("ExaminationExplained.restrictions_prelude")); //$NON-NLS-1$
		certificate.restrictions().forEach(restriction -> out//
				.append("\t ") //$NON-NLS-1$
				.append(ExaminationExplanedMessages.getString("ExaminationExplained.restriction_feature")) //$NON-NLS-1$
				.append(" [") //$NON-NLS-1$
				.append(feature(restriction))//
				.append("] ") //$NON-NLS-1$
				.append(ExaminationExplanedMessages.getString("ExaminationExplained.restriction_reason")) //$NON-NLS-1$
				.append(" [") //$NON-NLS-1$
				.append(reason(restriction.reason()))//
				.append("] ") //$NON-NLS-1$
		);
	}

	private String reason(TroubleCode code) {
		return String.format("%d: %s", code.code(), code.explanation());//$NON-NLS-1$
	}

	private void appendPermissions(StringBuilder out) {
		out.append(ExaminationExplanedMessages.getString("ExaminationExplained.permissions_prelude")); //$NON-NLS-1$
	}

	private List<String> features() {
		List<String> permitted = certificate.participants().stream()//
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

	private String date() {
		return DateTimeFormatter.RFC_1123_DATE_TIME.format(certificate.stamp());
	}
}
