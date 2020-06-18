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
package org.eclipse.passage.lic.internal.base.conditions;

import static org.junit.Assert.assertEquals;

import org.eclipse.passage.lic.internal.api.conditions.MatchingRule;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class MatchingRuleForIdentifierTest {

	@Test(expected = NullPointerException.class)
	public void prohibitsNullIdentiier() {
		supplied(null);
	}

	@Test
	public void compatibleCaseInsensitive() {
		assertEquals(new MatchingRuleCompatible(), supplied("compatible")); //$NON-NLS-1$
		assertEquals(new MatchingRuleCompatible(), supplied("compATible")); //$NON-NLS-1$
	}

	@Test
	public void equivalentCaseInsensitive() {
		assertEquals(new MatchingRuleEquivalent(), supplied("equivalent")); //$NON-NLS-1$
		assertEquals(new MatchingRuleEquivalent(), supplied("eQuivAleNt")); //$NON-NLS-1$
	}

	@Test
	public void greaterOrEqualCaseInsensitive() {
		assertEquals(new MatchingRuleGreaterOrEqual(), supplied("greaterOrEqual")); //$NON-NLS-1$
		assertEquals(new MatchingRuleGreaterOrEqual(), supplied("greaterorequal")); //$NON-NLS-1$
		assertEquals(new MatchingRuleGreaterOrEqual(), supplied("GREATerORequal")); //$NON-NLS-1$
	}

	@Test
	public void perfectCaseInsensitive() {
		assertEquals(new MatchingRulePerfect(), supplied("perfect")); //$NON-NLS-1$
		assertEquals(new MatchingRulePerfect(), supplied("PERFECT")); //$NON-NLS-1$
	}

	@Test
	public void defaultRuleForIncorrectInour() {
		assertEquals(new MatchingRuleDefault(), supplied("no data")); //$NON-NLS-1$
		assertEquals(new MatchingRuleDefault(), supplied("")); //$NON-NLS-1$
	}

	private MatchingRule supplied(String input) {
		return new MatchingRuleForIdentifier(input).get();
	}

}
