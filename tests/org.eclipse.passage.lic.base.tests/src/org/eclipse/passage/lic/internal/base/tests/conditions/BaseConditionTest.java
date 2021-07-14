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
package org.eclipse.passage.lic.internal.base.tests.conditions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.ZonedDateTime;

import org.eclipse.passage.lic.api.EvaluationInstructions;
import org.eclipse.passage.lic.api.EvaluationType;
import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.api.conditions.ValidityPeriod;
import org.eclipse.passage.lic.api.conditions.VersionMatch;
import org.eclipse.passage.lic.base.conditions.BaseCondition;
import org.eclipse.passage.lic.base.conditions.BaseEvaluationInstructions;
import org.eclipse.passage.lic.base.conditions.BaseValidityPeriodClosed;
import org.eclipse.passage.lic.base.conditions.BaseVersionMatch;
import org.eclipse.passage.lic.base.conditions.MatchingRulePerfect;
import org.junit.Test;

public final class BaseConditionTest {

	/**
	 * Constructing a condition with {@code null} {@code identifier} must cause a
	 * failure.
	 */
	@Test(expected = NullPointerException.class)
	public void identifierIsMandatory() {
		new BaseCondition(null, feature(), versionMatch(), validityPeriod(), evaluationInstructions());
	}

	/**
	 * Constructing a condition with {@code null} {@code feature} must cause a
	 * failure.
	 */
	@Test(expected = NullPointerException.class)
	public void featureIsMandatory() {
		new BaseCondition(identifier(), null, versionMatch(), validityPeriod(), evaluationInstructions());
	}

	/**
	 * Constructing a condition with {@code null} {@code validity period} must cause
	 * a failure.
	 */
	@Test(expected = NullPointerException.class)
	public void versionMatchDefinitionIsMandatory() {
		new BaseCondition(identifier(), feature(), null, validityPeriod(), evaluationInstructions());
	}

	/**
	 * Constructing a condition with {@code null} {@code version match} must cause a
	 * failure.
	 */
	@Test(expected = NullPointerException.class)
	public void validityPeriodIsMandatory() {
		new BaseCondition(identifier(), feature(), versionMatch(), null, evaluationInstructions());
	}

	/**
	 * Constructing a condition with {@code null} {@code evaluation instructions}
	 * must cause a failure.
	 */
	@Test(expected = NullPointerException.class)
	public void evaluationInstructionsAreMandatory() {
		new BaseCondition(identifier(), feature(), versionMatch(), validityPeriod(), null);
	}

	@Test
	public void isDataTransitionObject() {
		// given
		VersionMatch match = versionMatch();
		ValidityPeriod period = validityPeriod();
		EvaluationInstructions instructions = evaluationInstructions();
		// when
		Condition condition = new BaseCondition(identifier(), feature(), match, period, instructions);
		// then
		assertEquals(identifier(), condition.identifier());
		assertEquals(feature(), condition.feature());
		assertTrue(match == condition.versionMatch());
		assertTrue(period == condition.validityPeriod());
		assertTrue(instructions == condition.evaluationInstructions());
	}

	private String identifier() {
		return "aaa"; //$NON-NLS-1$
	}

	private String feature() {
		return "test-feature"; //$NON-NLS-1$
	}

	private VersionMatch versionMatch() {
		return new BaseVersionMatch("1.2.3", new MatchingRulePerfect()); //$NON-NLS-1$
	}

	private ValidityPeriod validityPeriod() {
		return new BaseValidityPeriodClosed(ZonedDateTime.now(), ZonedDateTime.now().plusHours(1));
	}

	private EvaluationInstructions evaluationInstructions() {
		return new BaseEvaluationInstructions(new EvaluationType.Of("test"), ""); //$NON-NLS-1$//$NON-NLS-2$
	}

}
