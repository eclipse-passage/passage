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
package org.eclipse.passage.lic.api.conditions.tests;

import static org.junit.Assert.assertEquals;

import org.eclipse.passage.lic.internal.api.conditions.EvaluationType;
import org.junit.Test;

@SuppressWarnings("restriction")
public abstract class EvaluationTypeContractTest {

	@Test(expected = NullPointerException.class)
	public final void nullIdentifierIsProhibited() {
		forIdentifier(null);
	}

	@Test
	public final void isDataClass() {
		assertEquals(forIdentifier("any"), forIdentifier("any")); //$NON-NLS-1$//$NON-NLS-2$
	}

	protected abstract EvaluationType forIdentifier(String identifier);

}
