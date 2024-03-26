/*******************************************************************************
 * Copyright (c) 2024 ArSysOp
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

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.api.restrictions.Restriction;

/**
 * 
 * @since 2.11
 */
public final class CertificateIsSufficient implements Predicate<Optional<ExaminationCertificate>> {

	private final String feature;

	public CertificateIsSufficient(String feature) {
		this.feature = Objects.requireNonNull(feature);
	}

	@Override
	public boolean test(Optional<ExaminationCertificate> certificate) {
		if (!certificate.isPresent()) {
			return false;
		}
		if (anyRestrictionForFeature(certificate.get())) {
			return false;
		}
		if (anyAgreementForFeatureIsNotAccepted(certificate.get())) {
			return false;
		}
		return true;
	}

	private boolean anyRestrictionForFeature(ExaminationCertificate certificate) {
		return certificate.restrictions().stream()//
				.filter(this::notBearable)//
				.anyMatch(this::relatesToFeature);
	}

	private boolean anyAgreementForFeatureIsNotAccepted(ExaminationCertificate certificate) {
		return certificate.agreements().stream()//
				.filter(this::notAccepted)//
				.anyMatch(this::relatesToFeature);
	}

	private boolean notBearable(Restriction restriction) {
		return new RestrictionMustPauseExecution().test(restriction);
	}

	private boolean relatesToFeature(Restriction restriction) {
		return relatesToFeature(restriction.unsatisfiedRequirement());
	}

	private boolean relatesToFeature(AgreementToAccept agreement) {
		return relatesToFeature(agreement.origin());
	}

	private boolean relatesToFeature(Requirement reqirement) {
		return feature.equals(reqirement.feature().identifier());
	}

	private boolean notAccepted(AgreementToAccept agreement) {
		return !agreement.acceptance().accepted();
	}

}
