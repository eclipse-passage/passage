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

import org.eclipse.passage.lic.api.EvaluationInstructions;
import org.eclipse.passage.lic.api.EvaluationType;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionEvaluationException;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionEvaluationService;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionEvaluatorsRegistry;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionParsingException;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionPasringRegistry;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionTokenAssessmentService;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionTokenAssessorsRegistry;
import org.eclipse.passage.lic.api.conditions.evaluation.ParsedExpression;
import org.eclipse.passage.lic.internal.base.i18n.ConditionsEvaluationMessages;

/**
 * @since 2.1
 */
public final class Authentication {

	private final ExpressionPasringRegistry parsers;
	private final ExpressionTokenAssessorsRegistry assessors;
	private final ExpressionEvaluatorsRegistry evaluators;

	public Authentication(ExpressionPasringRegistry parsers, ExpressionTokenAssessorsRegistry assessors,
			ExpressionEvaluatorsRegistry evaluators) {
		Objects.requireNonNull(parsers);
		Objects.requireNonNull(assessors);
		Objects.requireNonNull(evaluators);
		this.parsers = parsers;
		this.assessors = assessors;
		this.evaluators = evaluators;
	}

	public void verify(EvaluationInstructions instructions)
			throws ExpressionParsingException, ExpressionEvaluationException, LicensingException {
		ParsedExpression expression = new FormalizedExpression(instructions.expression(), parsers.get()).get();
		ExpressionEvaluationService evaluator = evaluators.get().service(expression.protocol());
		evaluator.evaluate(expression, assessor(instructions.type()));
	}

	private ExpressionTokenAssessmentService assessor(EvaluationType type) throws LicensingException {
		if (!assessors.get().hasService(type)) {
			throw new LicensingException(String.format(
					ConditionsEvaluationMessages.getString("BasePermissionEmittingService.no_assessment_service"), //$NON-NLS-1$
					type));
		}
		return assessors.get().service(type);
	}

}
