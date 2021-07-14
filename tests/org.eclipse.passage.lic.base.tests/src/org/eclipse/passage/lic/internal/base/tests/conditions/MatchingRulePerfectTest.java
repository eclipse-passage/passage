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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.eclipse.passage.lic.api.conditions.MatchingRule;
import org.eclipse.passage.lic.base.conditions.MatchingRulePerfect;
import org.junit.Test;

public final class MatchingRulePerfectTest {

	@Test
	public void idIsPerfect() {
		assertEquals("perfect", rule().identifier()); //$NON-NLS-1$
	}

	@Test
	public void qualifierSegmentIsCaseSensitive() {
		assertFalse(rule().match("1.2.3.hotfix", "1.2.3.HotFix")); //$NON-NLS-1$//$NON-NLS-2$
	}

	/**
	 * Inherited tests from LicensingVersionTest::testIsMatchPerfect
	 */
	@Test
	public void bulk() {
		assertEquals(false, rule().match("1.2.1", "1.2.0")); //$NON-NLS-1$//$NON-NLS-2$
		assertEquals(false, rule().match("1.3.0", "1.2.0")); //$NON-NLS-1$//$NON-NLS-2$
		assertEquals(false, rule().match("1.3.0.a", "1.2.0.b")); //$NON-NLS-1$ //$NON-NLS-2$
		assertEquals(false, rule().match("1.2.0.a", "1.2.0.b")); //$NON-NLS-1$ //$NON-NLS-2$
		assertEquals(false, rule().match("2.0.0.a", "1.2.0.a")); //$NON-NLS-1$ //$NON-NLS-2$
		assertEquals(true, rule().match("1.2.0.a", "1.2.0.a")); //$NON-NLS-1$ //$NON-NLS-2$
		assertEquals(false, rule().match("1.2.0.a", "0.0.0")); //$NON-NLS-1$ //$NON-NLS-2$
		assertEquals(false, rule().match("0.0.0", "1.2.0.a")); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private MatchingRule rule() {
		return new MatchingRulePerfect();
	}

}
