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

import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
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
				certificate.restrictions().size()));

		return out.toString();
	}

	private List<String> features() {
		return certificate.participants().stream()//
				.map(p -> String.format(ExaminationExplanedMessages.getString("ExaminationExplained.feature_format"), //$NON-NLS-1$
						p.condition().feature(), p //
								.condition().versionMatch().version()))//
				.distinct() //
				.collect(Collectors.toList());
	}

	private String date() {
		return DateTimeFormatter.RFC_1123_DATE_TIME.format(certificate.stamp());
	}
}
