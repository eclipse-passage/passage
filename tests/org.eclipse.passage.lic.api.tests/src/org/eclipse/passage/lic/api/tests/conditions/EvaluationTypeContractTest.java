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
package org.eclipse.passage.lic.api.tests.conditions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.eclipse.passage.lic.api.EvaluationType;
import org.junit.jupiter.api.Test;

public abstract class EvaluationTypeContractTest {

	@Test
	public final void nullIdentifierIsProhibited() {
		assertThrows(NullPointerException.class, () -> forIdentifier(null));
	}

	@Test
	public final void isDataClass() {
		assertEquals(forIdentifier("any"), forIdentifier("any")); //$NON-NLS-1$//$NON-NLS-2$
	}

	protected abstract EvaluationType forIdentifier(String identifier);

}
