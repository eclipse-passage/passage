/*******************************************************************************
 * Copyright (c) 2020, 2026 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support and improvements
 *******************************************************************************/
package org.eclipse.passage.lic.api.tests.conditions.evaluation;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionEvaluationException;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionEvaluationService;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionTokenAssessmentService;
import org.eclipse.passage.lic.api.conditions.evaluation.ParsedExpression;
import org.eclipse.passage.lic.api.tests.fakes.conditions.evaluation.FakeExpressionTokenAssessmentService;
import org.eclipse.passage.lic.api.tests.fakes.conditions.evaluation.FakeParsedExpression;
import org.junit.jupiter.api.Test;

public abstract class ExpressionEvaluationServiceContractTest {

	@Test
	public final void prohibitsNullExpression() {
		assertThrows(NullPointerException.class,
				() -> prohibitsNullArgument(null, new FakeExpressionTokenAssessmentService()));
	}

	@Test
	public final void prohibitsNullAssessor() {
		assertThrows(NullPointerException.class, () -> prohibitsNullArgument(new FakeParsedExpression(), null));
	}

	@Test
	public final void canOnlyEvaluateExpressionOfSameProtocl() throws ExpressionEvaluationException {
		assertThrows(ExpressionEvaluationException.class, () -> evaluator().evaluate(new FakeParsedExpression("fake"), //$NON-NLS-1$
				new FakeExpressionTokenAssessmentService()));
	}

	private void prohibitsNullArgument(ParsedExpression expression, ExpressionTokenAssessmentService assessor) {
		try {
			evaluator().evaluate(expression, assessor);
		} catch (ExpressionEvaluationException e) {
			fail("No evaluation activity is expected to be started for invalid incoming data"); //$NON-NLS-1$
		}
	}

	protected abstract ExpressionEvaluationService evaluator();
}
