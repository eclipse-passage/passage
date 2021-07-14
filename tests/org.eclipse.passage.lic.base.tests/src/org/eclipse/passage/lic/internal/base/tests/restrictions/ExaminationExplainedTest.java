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
package org.eclipse.passage.lic.internal.base.tests.restrictions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.ZonedDateTime;
import java.util.Collections;

import org.eclipse.passage.lic.api.EvaluationType;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.diagnostic.TroubleCode;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.api.restrictions.RestrictionLevel;
import org.eclipse.passage.lic.base.BaseLicensedProduct;
import org.eclipse.passage.lic.base.conditions.BaseCondition;
import org.eclipse.passage.lic.base.conditions.BaseEvaluationInstructions;
import org.eclipse.passage.lic.base.conditions.BaseValidityPeriodClosed;
import org.eclipse.passage.lic.base.conditions.BaseVersionMatch;
import org.eclipse.passage.lic.base.conditions.MatchingRuleCompatible;
import org.eclipse.passage.lic.base.conditions.UnknownConditionOrigin;
import org.eclipse.passage.lic.base.conditions.evaluation.BasePermission;
import org.eclipse.passage.lic.base.requirements.BaseFeature;
import org.eclipse.passage.lic.base.requirements.BaseRequirement;
import org.eclipse.passage.lic.base.restrictions.BaseExaminationCertificate;
import org.eclipse.passage.lic.base.restrictions.BaseRestriction;
import org.eclipse.passage.lic.base.restrictions.ExaminationExplained;
import org.junit.Test;

public final class ExaminationExplainedTest {

	@Test(expected = NullPointerException.class)
	public void doesNotExplainNull() {
		new ExaminationExplained(null);
	}

	@Test
	public void explains() {
		String explanation = new ExaminationExplained(certificate()).get().trim();
		assertFalse(explanation.isEmpty());
		assertTrue(explanation.contains("woha!")); //$NON-NLS-1$
		assertTrue(explanation.contains("christmas")); //$NON-NLS-1$
		assertTrue(explanation.contains("just do not like you")); //$NON-NLS-1$
		assertTrue(explanation.contains("failed-feature")); //$NON-NLS-1$
	}

	private ExaminationCertificate certificate() {
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
								new TroubleCode.Of(777, "just do not like you")))); //$NON-NLS-1$
	}

	private LicensedProduct product() {
		return new BaseLicensedProduct("Story", "2.12.75"); //$NON-NLS-1$//$NON-NLS-2$
	}

}
