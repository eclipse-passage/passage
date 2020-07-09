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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.tests.conditions.evaluation.ExpressionEvaluationServiceContractTest;
import org.eclipse.passage.lic.api.tests.fakes.conditions.evaluation.FakeExpressionTokenAssessmentService;
import org.eclipse.passage.lic.api.tests.fakes.conditions.evaluation.FakeParsedExpression;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationType;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionEvaluationException;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionEvaluationService;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionProtocol;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionTokenAssessmentService;
import org.eclipse.passage.lic.internal.base.conditions.evaluation.SimpleMapExpression;
import org.eclipse.passage.lic.internal.base.conditions.evaluation.SimpleMapExpressionEvaluationService;
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
		Biased assessor = new Biased()//
				.withAnswer("ok", true) //$NON-NLS-1$
				.withAnswer("nok", false); //$NON-NLS-1$
		try {

			evaluator().evaluate(//
					expression(//
							new String[] { "ok", "!1" }, //$NON-NLS-1$//$NON-NLS-2$
							new String[] { "nok", "?!" }), //$NON-NLS-1$//$NON-NLS-2$
					assessor);
		} catch (ExpressionEvaluationException e) {
			assertTrue(assessor.asked.size() >= 1);
			throw e;
		}
	}

	@Test(expected = ExpressionEvaluationException.class)
	public void failOnEmptyExpression() throws ExpressionEvaluationException {
		evaluator().evaluate(expression(), new FakeExpressionTokenAssessmentService());
	}

	@Test
	public void canSucceed() {
		Biased assessor = new Biased()//
				.withAnswer("ok1", true) //$NON-NLS-1$
				.withAnswer("ok2", true); //$NON-NLS-1$
		try {
			evaluator().evaluate(//
					expression(//
							new String[] { "ok1", ":)" }, //$NON-NLS-1$//$NON-NLS-2$
							new String[] { "ok2", ":)" }), //$NON-NLS-1$//$NON-NLS-2$
					assessor);
		} catch (ExpressionEvaluationException e) {
			fail("Intended to succeed"); //$NON-NLS-1$
		}
		assertEquals(2, assessor.asked.size()); // each key is asked
	}

	private SimpleMapExpression expression(String[]... pairs) {
		return new SimpleMapExpression(//
				new ExpressionProtocol.Ands(), // $NON-NLS-1$
				Arrays.stream(pairs) //
						.collect(Collectors.<String[], String, String>toMap(pair -> pair[0], pair -> pair[1])));
	}

	private static final class Biased implements ExpressionTokenAssessmentService {
		private final Map<String, Boolean> answers = new HashMap<>();
		private final Set<String> asked = new HashSet<>();

		Biased withAnswer(String key, boolean answer) {
			answers.put(key, answer);
			return this;
		}

		@Override
		public EvaluationType id() {
			return new EvaluationType.Of("biased"); //$NON-NLS-1$
		}

		@Override
		public boolean equal(String key, String value) throws ExpressionEvaluationException {
			asked.add(key);
			return answers.containsKey(key) ? answers.get(key) : false;
		}

	}

}
