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
package org.eclipse.passage.lic.internal.base.tests.restrictions;

import java.time.ZonedDateTime;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationType;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.requirements.Feature;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.RestrictionLevel;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.base.conditions.BaseCondition;
import org.eclipse.passage.lic.internal.base.conditions.BaseEvaluationInstructions;
import org.eclipse.passage.lic.internal.base.conditions.BaseValidityPeriodClosed;
import org.eclipse.passage.lic.internal.base.conditions.BaseVersionMatch;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRuleCompatible;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRuleEquivalent;
import org.eclipse.passage.lic.internal.base.conditions.evaluation.BasePermission;
import org.eclipse.passage.lic.internal.base.requirements.BaseFeature;
import org.eclipse.passage.lic.internal.base.requirements.BaseRequirement;

@SuppressWarnings("restriction")
final class TestState {

	private final LicensedProduct product;
	private final Feature featureFirst;
	private final Feature featureSecond;
	private final Condition conditionFirst;
	private final Condition conditionSecond;
	private final Condition conditionSecondObsolete;
	private final Requirement requirementFirst;
	private final Requirement requirementSecond;
	private final Permission permissionFirst;
	private final Permission permissionSecond;
	private final Permission permissionSecondObsolete;

	TestState() {
		product = new BaseLicensedProduct("test-product", "4.3.18-hotfix"); //$NON-NLS-1$//$NON-NLS-2$
		featureFirst = new BaseFeature("the-first-feature", //$NON-NLS-1$
				"1.12.0", //$NON-NLS-1$
				"Feature the first", //$NON-NLS-1$
				getClass().getName());
		featureSecond = new BaseFeature("the-second-feature", //$NON-NLS-1$
				"6.4.37", //$NON-NLS-1$
				"Feature the second", //$NON-NLS-1$
				getClass().getName());
		conditionFirst = new BaseCondition(featureFirst.identifier(), //
				new BaseVersionMatch(//
						"1.1.0", //$NON-NLS-1$
						new MatchingRuleCompatible()), //
				new BaseValidityPeriodClosed(//
						ZonedDateTime.now().minusDays(1), //
						ZonedDateTime.now().plusDays(1)), //
				new BaseEvaluationInstructions(//
						new EvaluationType.Of("does not matter"), //$NON-NLS-1$
						"does not matter eitehr") //$NON-NLS-1$
		);
		conditionSecond = new BaseCondition(featureSecond.identifier(), //
				new BaseVersionMatch(//
						"6.4.0", //$NON-NLS-1$
						new MatchingRuleEquivalent()), //
				new BaseValidityPeriodClosed(//
						ZonedDateTime.now(), //
						ZonedDateTime.now().plusDays(1)), //
				new BaseEvaluationInstructions(//
						new EvaluationType.Of("does not matter"), //$NON-NLS-1$
						"does not matter eitehr") //$NON-NLS-1$
		);
		conditionSecondObsolete = new BaseCondition(featureSecond.identifier(), //
				new BaseVersionMatch(//
						"5.4.18", //$NON-NLS-1$
						new MatchingRuleEquivalent()), //
				new BaseValidityPeriodClosed(//
						ZonedDateTime.now(), //
						ZonedDateTime.now().plusDays(1)), //
				new BaseEvaluationInstructions(//
						new EvaluationType.Of("does not matter"), //$NON-NLS-1$
						"does not matter eitehr") //$NON-NLS-1$
		);
		requirementFirst = new BaseRequirement(featureFirst, new RestrictionLevel.Error(), this);
		requirementSecond = new BaseRequirement(featureSecond, new RestrictionLevel.Warning(), this);
		permissionFirst = new BasePermission(//
				product, //
				conditionFirst, //
				ZonedDateTime.now().minusDays(1), //
				ZonedDateTime.now().plusDays(1));
		permissionSecond = new BasePermission(//
				product, //
				conditionSecond, //
				ZonedDateTime.now(), //
				ZonedDateTime.now().plusHours(1));
		permissionSecondObsolete = new BasePermission(//
				product, //
				conditionSecondObsolete, //
				ZonedDateTime.now(), //
				ZonedDateTime.now().plusHours(1));

	}

	LicensedProduct product() {
		return product;
	}

	Requirement requirementFirst() {
		return requirementFirst;
	}

	Permission permissionFirst() {
		return permissionFirst;
	}

	Requirement requirementSecond() {
		return requirementSecond;
	}

	Permission permissionSecond() {
		return permissionSecond;
	}

	Permission permissionSecondObsolete() {
		return permissionSecondObsolete;
	}

}
