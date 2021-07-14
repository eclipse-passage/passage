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
package org.eclipse.passage.lic.base.conditions.evaluation;

import java.util.Objects;

import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionEvaluationException;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionEvaluationService;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionProtocol;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionTokenAssessmentService;
import org.eclipse.passage.lic.api.conditions.evaluation.ParsedExpression;
import org.eclipse.passage.lic.internal.base.i18n.ConditionsEvaluationMessages;

/**
 * @since 2.1
 */
public final class SimpleMapExpressionEvaluationService implements ExpressionEvaluationService {

	private final ExpressionProtocol format = new ExpressionProtocol.Berlin();

	@Override
	public ExpressionProtocol id() {
		return format;
	}

	@Override
	public void evaluate(ParsedExpression expression, ExpressionTokenAssessmentService assessor)
			throws ExpressionEvaluationException {
		Objects.requireNonNull(expression);
		Objects.requireNonNull(assessor);
		verifyProtocol(expression);
		SimpleMapExpression map = map(expression);
		for (String key : map.keys()) {
			boolean passed = equal(key, map.expected(key), assessor);
			if (!passed) {
				throw new ExpressionEvaluationException(String.format(ConditionsEvaluationMessages.getString(//
						"SimpleMapExpressionEvaluationService.segment_fails_evaluation"), //$NON-NLS-1$
						assessor.id().identifier(), key, map.expected(key)));
			}
		}
	}

	private boolean equal(String key, String value, ExpressionTokenAssessmentService assessor)
			throws ExpressionEvaluationException {
		try {
			return assessor.equal(key, value);
		} catch (ExpressionEvaluationException e) {
			throw new ExpressionEvaluationException(String.format(ConditionsEvaluationMessages.getString(//
					"SimpleMapExpressionEvaluationService.evaluation_fails"), //$NON-NLS-1$
					key, value, assessor.id().identifier()), e);
		}
	}

	private SimpleMapExpression map(ParsedExpression expression) throws ExpressionEvaluationException {
		if (!SimpleMapExpression.class.isInstance(expression)) {
			throw new ExpressionEvaluationException(String.format(ConditionsEvaluationMessages.getString(//
					"SimpleMapExpressionEvaluationService.foreign_expression"), // //$NON-NLS-1$
					expression.protocol()));
		}
		SimpleMapExpression map = (SimpleMapExpression) expression;
		if (map.keys().isEmpty()) {
			throw new ExpressionEvaluationException(ConditionsEvaluationMessages.getString(//
					"SimpleMapExpressionEvaluationService.no_checks")); //$NON-NLS-1$
		}
		return map;
	}

	private void verifyProtocol(ParsedExpression expression) throws ExpressionEvaluationException {
		if (!id().equals(expression.protocol())) {
			throw new ExpressionEvaluationException(String.format(
					ConditionsEvaluationMessages
							.getString("SimpleMapExpressionEvaluationService.unexpected_expression_protocol"), //$NON-NLS-1$
					expression.protocol(), id()));
		}
	}

}
