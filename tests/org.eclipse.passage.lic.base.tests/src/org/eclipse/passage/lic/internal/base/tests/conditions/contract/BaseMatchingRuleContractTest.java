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
package org.eclipse.passage.lic.internal.base.tests.conditions.contract;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.eclipse.passage.lic.api.tests.conditions.MatchingRuleContractTest;
import org.junit.jupiter.api.Test;

@SuppressWarnings("restriction")
public abstract class BaseMatchingRuleContractTest extends MatchingRuleContractTest {

	@Test
	public final void nullRequiredIsProhibited() {
		assertThrows(NullPointerException.class, () -> rule().match(null, "")); //$NON-NLS-1$
	}

	@Test
	public final void nullAllowedIsProhibited() {
		assertThrows(NullPointerException.class, () -> rule().match("", null)); //$NON-NLS-1$
	}
}
