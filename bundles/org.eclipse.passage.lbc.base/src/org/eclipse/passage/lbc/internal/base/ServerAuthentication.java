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
package org.eclipse.passage.lbc.internal.base;

import org.eclipse.passage.lic.api.EvaluationInstructions;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionEvaluationException;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionEvaluatorsRegistry;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionParsingException;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionPasringRegistry;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionTokenAssessorsRegistry;
import org.eclipse.passage.lic.api.inspection.RuntimeEnvironmentRegistry;
import org.eclipse.passage.lic.base.conditions.evaluation.Authentication;
import org.eclipse.passage.lic.base.conditions.evaluation.BerlinProtocolExpressionParseService;
import org.eclipse.passage.lic.base.conditions.evaluation.SimpleMapExpressionEvaluationService;
import org.eclipse.passage.lic.base.registry.ReadOnlyRegistry;
import org.eclipse.passage.lic.oshi.HardwareAssessmentService;
import org.eclipse.passage.lic.oshi.HardwareEnvironment;

final class ServerAuthentication {

	private final EvaluationInstructions instructions;

	public ServerAuthentication(EvaluationInstructions instructions) {
		this.instructions = instructions;
	}

	void evaluate() throws ExpressionParsingException, ExpressionEvaluationException, LicensingException {
		new Authentication(parsers(), assessors(), evaluators()).verify(instructions);
	}

	private ExpressionPasringRegistry parsers() {
		return () -> new ReadOnlyRegistry<>(new BerlinProtocolExpressionParseService());
	}

	private ExpressionTokenAssessorsRegistry assessors() {
		return () -> new ReadOnlyRegistry<>(new HardwareAssessmentService(environments()));
	}

	private RuntimeEnvironmentRegistry environments() {
		return () -> new ReadOnlyRegistry<>(new HardwareEnvironment());
	}

	private ExpressionEvaluatorsRegistry evaluators() {
		return () -> new ReadOnlyRegistry<>(new SimpleMapExpressionEvaluationService());
	}

}
