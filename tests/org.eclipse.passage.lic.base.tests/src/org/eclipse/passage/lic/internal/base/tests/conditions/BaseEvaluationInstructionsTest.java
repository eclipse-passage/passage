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
package org.eclipse.passage.lic.internal.base.tests.conditions;

import org.eclipse.passage.lic.api.EvaluationType;
import org.eclipse.passage.lic.base.conditions.BaseEvaluationInstructions;
import org.junit.Test;

public final class BaseEvaluationInstructionsTest {

	@Test(expected = NullPointerException.class)
	public void nullTypeIsProhibited() {
		new BaseEvaluationInstructions(null, ""); //$NON-NLS-1$
	}

	@Test(expected = NullPointerException.class)
	public void nullExpressionIsProhibited() {
		new BaseEvaluationInstructions(new EvaluationType.Of("anyway"), null); //$NON-NLS-1$
	}

}
