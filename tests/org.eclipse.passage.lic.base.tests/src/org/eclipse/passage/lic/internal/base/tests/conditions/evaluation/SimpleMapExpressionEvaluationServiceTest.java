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
package org.eclipse.passage.lic.internal.base.tests.conditions.evaluation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionEvaluationException;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionEvaluationService;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionProtocol;
import org.eclipse.passage.lic.api.tests.conditions.evaluation.ExpressionEvaluationServiceContractTest;
import org.eclipse.passage.lic.api.tests.fakes.conditions.evaluation.FakeExpressionTokenAssessmentService;
import org.eclipse.passage.lic.api.tests.fakes.conditions.evaluation.FakeParsedExpression;
import org.eclipse.passage.lic.base.conditions.evaluation.SimpleMapExpression;
import org.eclipse.passage.lic.base.conditions.evaluation.SimpleMapExpressionEvaluationService;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class SimpleMapExpressionEvaluationServiceTest extends ExpressionEvaluationServiceContractTest {

	@Override
	protected ExpressionEvaluationService evaluator() {
		return new SimpleMapExpressionEvaluationService();
	}

	@Test(expected = ExpressionEvaluationException.class)
	public void failureOnUnexpectedExpression() throws ExpressionEvaluationException {
		evaluator().evaluate(new FakeParsedExpression(), new FakeExpressionTokenAssessmentService());

	}

	@Test(expected = ExpressionEvaluationException.class)
	public void segmantFailureIsContagious() throws ExpressionEvaluationException {
		BiasedAssessor assessor = new BiasedAssessor()//
				.withAnswer("ok", "1") //$NON-NLS-1$//$NON-NLS-2$
				.withAnswer("nok", "not-2"); //$NON-NLS-1$//$NON-NLS-2$
		try {

			evaluator().evaluate(//
					expression(//
							new String[] { "ok", "1" }, //$NON-NLS-1$//$NON-NLS-2$
							new String[] { "nok", "2" }), //$NON-NLS-1$//$NON-NLS-2$
					assessor);
		} catch (ExpressionEvaluationException e) {
			// at least the failing segment has been assessed
			assertTrue(assessor.askedKeys().contains("nok")); //$NON-NLS-1$
			throw e;
		}
	}

	@Test(expected = ExpressionEvaluationException.class)
	public void failOnEmptyExpression() throws ExpressionEvaluationException {
		evaluator().evaluate(expression(), new FakeExpressionTokenAssessmentService());
	}

	@Test
	public void canSucceed() {
		BiasedAssessor assessor = new BiasedAssessor()//
				.withAnswer("ok1", ":)") //$NON-NLS-1$//$NON-NLS-2$
				.withAnswer("ok2", ":)"); //$NON-NLS-1$//$NON-NLS-2$
		try {
			evaluator().evaluate(//
					expression(//
							new String[] { "ok1", ":)" }, //$NON-NLS-1$//$NON-NLS-2$
							new String[] { "ok2", ":)" }), //$NON-NLS-1$//$NON-NLS-2$
					assessor);
		} catch (ExpressionEvaluationException e) {
			fail("Intended to succeed"); //$NON-NLS-1$
		}
		assertEquals(2, assessor.askedKeys().size()); // each segment has been assessed
	}

	private SimpleMapExpression expression(String[]... pairs) {
		return new SimpleMapExpression(//
				new ExpressionProtocol.Berlin(), // $NON-NLS-1$
				Arrays.stream(pairs)//
						.collect(Collectors.toMap(//
								pair -> pair[0], //
								pair -> pair[1])));
	}

}
