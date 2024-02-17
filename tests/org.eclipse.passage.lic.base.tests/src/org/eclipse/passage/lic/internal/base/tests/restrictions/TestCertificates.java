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
package org.eclipse.passage.lic.internal.base.tests.restrictions;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import org.eclipse.passage.lic.api.EvaluationType;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.api.diagnostic.TroubleCode;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.api.restrictions.RestrictionLevel;
import org.eclipse.passage.lic.api.tests.fakes.agreements.FakeAgreementState;
import org.eclipse.passage.lic.api.tests.fakes.agreements.FakeResolvedAgreement;
import org.eclipse.passage.lic.base.BaseLicensedProduct;
import org.eclipse.passage.lic.base.agreements.BaseAgreementToAccept;
import org.eclipse.passage.lic.base.agreements.UnacceptedAgreementRestriction;
import org.eclipse.passage.lic.base.conditions.BaseCondition;
import org.eclipse.passage.lic.base.conditions.BaseEvaluationInstructions;
import org.eclipse.passage.lic.base.conditions.BaseValidityPeriodClosed;
import org.eclipse.passage.lic.base.conditions.BaseVersionMatch;
import org.eclipse.passage.lic.base.conditions.MatchingRuleCompatible;
import org.eclipse.passage.lic.base.conditions.UnknownConditionOrigin;
import org.eclipse.passage.lic.base.conditions.evaluation.BasePermission;
import org.eclipse.passage.lic.base.diagnostic.code.LicenseDoesNotMatch;
import org.eclipse.passage.lic.base.diagnostic.code.TentativeAccess;
import org.eclipse.passage.lic.base.requirements.BaseFeature;
import org.eclipse.passage.lic.base.requirements.BaseRequirement;
import org.eclipse.passage.lic.base.restrictions.BaseExaminationCertificate;
import org.eclipse.passage.lic.base.restrictions.BaseRestriction;

@SuppressWarnings("restriction")
final class TestCertificates {

	ExaminationCertificate verbose() {
		return new BaseExaminationCertificate(//
				Collections.singletonMap(//
						new BaseRequirement(//
								new BaseFeature(//
										"properly-licensed-feature", //$NON-NLS-1$
										"1.2.3", //$NON-NLS-1$
										"Feature to Succeed", //$NON-NLS-1$
										"This test"), //$NON-NLS-1$
								new RestrictionLevel.Of("oops"), //$NON-NLS-1$
								"This test"), //$NON-NLS-1$
						new BasePermission(//
								product(), //
								new BaseCondition(//
										"condition-identifier", //$NON-NLS-1$
										"properly-licensed-feature", //$NON-NLS-1$
										new BaseVersionMatch("1.2.3", new MatchingRuleCompatible()), //$NON-NLS-1$
										new BaseValidityPeriodClosed(//
												ZonedDateTime.now().minusDays(11), //
												ZonedDateTime.now().plusDays(11)),
										new BaseEvaluationInstructions(//
												new EvaluationType.Of("christmas"), //$NON-NLS-1$
												"nose=red" //$NON-NLS-1$
										)), //
								ZonedDateTime.now(), //
								ZonedDateTime.now().plusDays(2), //
								new UnknownConditionOrigin())//
				), //
				Collections.singleton(//
						new BaseRestriction(//
								product(), //
								new BaseRequirement(//
										new BaseFeature(//
												"failed-feature", //$NON-NLS-1$
												"17.2.1", //$NON-NLS-1$
												"Feature to fail", //$NON-NLS-1$
												"This test"), //$NON-NLS-1$
										new RestrictionLevel.Of("woha!"), //$NON-NLS-1$
										"This test"), //$NON-NLS-1$
								new TroubleCode.Of(777, "just do not like you"))) //$NON-NLS-1$
		);
	}

	ExaminationCertificate onlyBearableRestrictions() {
		return new BaseExaminationCertificate(//
				Collections.emptyMap(), // no permissions
				Collections.singleton(//
						new BaseRestriction(//
								product(), //
								warningProtectedFeature(), //
								new TentativeAccess()))//
		);
	}

	ExaminationCertificate withSevereRestrictions() {
		return withSevereRestrictions("very-much-protected-feature"); //$NON-NLS-1$
	}

	ExaminationCertificate withSevereRestrictions(String feature) {
		return new BaseExaminationCertificate(//
				Collections.emptyMap(), // no permissions
				Collections.singleton(//
						new BaseRestriction(//
								product(), //
								errorProtectedFeature(feature), //
								new LicenseDoesNotMatch())//
				));
	}

	ExaminationCertificate withAgreementRestrictions() {
		return withAgreementRestrictions("not-very-much-protected-feature"); //$NON-NLS-1$
	}

	ExaminationCertificate withAgreementRestrictions(String feature) {
		AgreementToAccept agreement = new BaseAgreementToAccept(//
				warningProtectedFeature(feature), //
				new FakeResolvedAgreement(), //
				new FakeAgreementState(false));
		return new BaseExaminationCertificate(//
				Collections.emptyMap(), // no permissions
				List.of(new UnacceptedAgreementRestriction(product(), agreement).get()), //
				List.of(agreement)//
		);
	}

	ExaminationCertificate agreementAccepted() {
		AgreementToAccept agreement = new BaseAgreementToAccept(//
				warningProtectedFeature(), //
				new FakeResolvedAgreement(), //
				new FakeAgreementState(true));
		return new BaseExaminationCertificate(//
				Collections.emptyMap(), // no permissions
				Collections.emptyList(), // no restrictions
				List.of(agreement)//
		);
	}

	private LicensedProduct product() {
		return new BaseLicensedProduct("Story", "2.12.75"); //$NON-NLS-1$//$NON-NLS-2$
	}

	private BaseRequirement warningProtectedFeature() {
		return warningProtectedFeature("not-very-much-protected-feature"); //$NON-NLS-1$
	}

	private BaseRequirement warningProtectedFeature(String feature) {
		return new BaseRequirement(//
				new BaseFeature(//
						feature, //
						"0.0.1", //$NON-NLS-1$
						"Feature for a user to try, but notably", //$NON-NLS-1$
						"Does not need to be error-level protected"), //$NON-NLS-1$
				new RestrictionLevel.Warning(), //
				"Requirement is not mandatory to satisfy");//$NON-NLS-1$
	}

	private BaseRequirement errorProtectedFeature(String feature) {
		return new BaseRequirement(//
				new BaseFeature(//
						feature, //
						"12.1.0", //$NON-NLS-1$
						"Mature an precious feature", //$NON-NLS-1$
						"Does need to ve error-level protected"), //$NON-NLS-1$
				new RestrictionLevel.Error(), //
				"Requirement is mandatory to satisfy"); //$NON-NLS-1$
	}

}
