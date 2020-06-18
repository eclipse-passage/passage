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
package org.eclipse.passage.lic.internal.base.conditions;

import java.util.Date;

import org.eclipse.passage.lic.internal.api.conditions.EvaluationInstructions;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationType;
import org.eclipse.passage.lic.internal.api.conditions.ValidityPeriod;
import org.eclipse.passage.lic.internal.api.conditions.VersionMatch;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class BaseConditionTest {

	/**
	 * Constructing a condition with {@code null} {@code identifier} must cause a
	 * failure.
	 */
	@Test(expected = NullPointerException.class)
	public void featureIsMandatory() {
		new BaseCondition(null, versionMatch(), validityPeriod(), evaluationInstructions());
	}

	/**
	 * Constructing a condition with {@code null} {@code validity period} must cause
	 * a failure.
	 */
	@Test(expected = NullPointerException.class)
	public void versionMatchDefinitionIsMandatory() {
		new BaseCondition(feature(), null, validityPeriod(), evaluationInstructions());
	}

	/**
	 * Constructing a condition with {@code null} {@code version match} must cause a
	 * failure.
	 */
	@Test(expected = NullPointerException.class)
	public void validityPeriodIsMandatory() {
		new BaseCondition(feature(), versionMatch(), null, evaluationInstructions());
	}

	/**
	 * Constructing a condition with {@code null} {@code evaluation instructions}
	 * must cause a failure.
	 */
	@Test(expected = NullPointerException.class)
	public void evaluationInstructionsAreMandatory() {
		new BaseCondition(feature(), versionMatch(), validityPeriod(), null);
	}

	private String feature() {
		return "test-feature"; //$NON-NLS-1$
	}

	private VersionMatch versionMatch() {
		return new BaseVersionMatch("1.2.3", new MatchingRulePerfect()); //$NON-NLS-1$
	}

	private ValidityPeriod validityPeriod() {
		return new BaseValidityPeriodClosed(new Date(), new Date(System.currentTimeMillis() + 3_600_000));
	}

	private EvaluationInstructions evaluationInstructions() {
		return new BaseEvaluationInstructions(new EvaluationType.Of("test"), ""); //$NON-NLS-1$//$NON-NLS-2$
	}

}
