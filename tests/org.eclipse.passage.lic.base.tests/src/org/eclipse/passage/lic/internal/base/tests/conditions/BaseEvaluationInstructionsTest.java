/*******************************************************************************
 * Copyright (c) 2020, 2026 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support and improvements
 *******************************************************************************/
package org.eclipse.passage.lic.internal.base.tests.conditions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.eclipse.passage.lic.api.EvaluationType;
import org.eclipse.passage.lic.base.conditions.BaseEvaluationInstructions;
import org.junit.jupiter.api.Test;

public final class BaseEvaluationInstructionsTest {

	@Test
	public void nullTypeIsProhibited() {
		assertThrows(NullPointerException.class, () -> new BaseEvaluationInstructions(null, "")); //$NON-NLS-1$
	}

	@Test
	public void nullExpressionIsProhibited() {
		assertThrows(NullPointerException.class,
				() -> new BaseEvaluationInstructions(new EvaluationType.Of("anyway"), null)); //$NON-NLS-1$
	}

}
