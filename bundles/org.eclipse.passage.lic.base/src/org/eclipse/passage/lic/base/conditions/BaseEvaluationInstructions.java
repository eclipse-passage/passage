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
package org.eclipse.passage.lic.base.conditions;

import java.util.Objects;

import org.eclipse.passage.lic.api.EvaluationInstructions;
import org.eclipse.passage.lic.api.EvaluationType;

/**
 * @since 2.1
 */
public final class BaseEvaluationInstructions implements EvaluationInstructions {

	private final EvaluationType type;
	private final String expression;

	public BaseEvaluationInstructions(EvaluationType type, String expression) {
		Objects.requireNonNull(type, "BaseEvaluationInstructions::type"); //$NON-NLS-1$
		Objects.requireNonNull(expression, "BaseEvaluationInstructions::expression"); //$NON-NLS-1$
		this.type = type;
		this.expression = expression;
	}

	@Override
	public EvaluationType type() {
		return type;
	}

	@Override
	public String expression() {
		return expression;
	}

}
