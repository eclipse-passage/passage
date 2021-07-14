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
package org.eclipse.passage.lic.api.tests.fakes.conditions.evaluation;

import org.eclipse.passage.lic.api.EvaluationType;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionTokenAssessmentService;

public final class FakeExpressionTokenAssessmentService implements ExpressionTokenAssessmentService {

	@Override
	public EvaluationType id() {
		return new EvaluationType.Of("air-content-assessment"); //$NON-NLS-1$
	}

	@Override
	public boolean equal(String key, String value) {
		throw new UnsupportedOperationException();
	}

}
