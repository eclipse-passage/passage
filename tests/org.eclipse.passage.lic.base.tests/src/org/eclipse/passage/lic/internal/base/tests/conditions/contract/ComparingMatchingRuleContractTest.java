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

import static org.junit.Assert.assertTrue;

import org.junit.Test;

@SuppressWarnings("restriction")
public abstract class ComparingMatchingRuleContractTest extends BaseMatchingRuleContractTest {

	@Test
	public final void requiredDefaultAlwaysMatches() {
		assertTrue(rule().match("0.0.0", "1.23.456")); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public final void notVersionMatchesDefault() {
		String incorrect = "this .is . not . a . version"; //$NON-NLS-1$
		assertTrue(rule().match(incorrect, "0.0.0")); //$NON-NLS-1$
		assertTrue(rule().match("0.0.0", incorrect)); //$NON-NLS-1$
	}

	@Test
	public final void blankStringMatchesDefault() {
		assertTrue(rule().match(" ", "0.0.0")); //$NON-NLS-1$ //$NON-NLS-2$
		assertTrue(rule().match("0.0.0", "")); //$NON-NLS-1$ //$NON-NLS-2$
	}

}
