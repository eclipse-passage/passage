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
package org.eclipse.passage.lic.internal.base.tests.conditions.contract;

import org.eclipse.passage.lic.api.tests.conditions.MatchingRuleContractTest;
import org.junit.Test;

@SuppressWarnings("restriction")
public abstract class BaseMatchingRuleContractTest extends MatchingRuleContractTest {

	@Test(expected = NullPointerException.class)
	public final void nullRequiredIsProhibited() {
		rule().match(null, ""); //$NON-NLS-1$
	}

	@Test(expected = NullPointerException.class)
	public final void nullAllowedIsProhibited() {
		rule().match("", null); //$NON-NLS-1$
	}
}
