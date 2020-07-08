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
package org.eclipse.passage.lic.internal.base.conditions.evaluation;

import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionEvaluationService;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionProtocol;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionTokenAssessmentService;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ParsedExpression;

@SuppressWarnings("restriction")
public final class SimpleMapExpressionEvaluationService implements ExpressionEvaluationService {

	private final ExpressionProtocol format = new ExpressionProtocol.Ands();

	@Override
	public ExpressionProtocol id() {
		return format;
	}

	@Override
	public boolean evaluate(ParsedExpression expression, ExpressionTokenAssessmentService evaluator) {
		// FIXME: ytbd: if not instanceof - swear to diagnose
		SimpleMapExpression simple = (SimpleMapExpression) expression;
		// FIXME: ytbd: need report which check is failed
		return simple.keys().stream() //
				.allMatch(key -> evaluator.equal(key, simple.expected(key)));
	}

}
