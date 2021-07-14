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
package org.eclipse.passage.lic.internal.base.tests.conditions.evaluation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.passage.lic.api.EvaluationType;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionEvaluationException;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionTokenAssessmentService;

final class BiasedAssessor implements ExpressionTokenAssessmentService {

	private final Map<String, String> answers = new HashMap<>();
	private final Set<String> asked = new HashSet<>();
	private final EvaluationType type;

	BiasedAssessor(String type) {
		this.type = new EvaluationType.Of(type);
	}

	BiasedAssessor() {
		this("biased"); //$NON-NLS-1$
	}

	BiasedAssessor withAnswer(String key, String answer) {
		answers.put(key, answer);
		return this;
	}

	@Override
	public EvaluationType id() {
		return type;
	}

	@Override
	public boolean equal(String key, String value) throws ExpressionEvaluationException {
		asked.add(key);
		return answers.containsKey(key) ? answers.get(key).equals(value) : false;
	}

	Set<String> askedKeys() {
		return asked;
	}
}
