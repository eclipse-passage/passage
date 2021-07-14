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
package org.eclipse.passage.lic.api.tests.conditions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lic.api.conditions.MatchingRule;
import org.junit.Test;

public abstract class MatchingRuleContractTest {

	@Test
	public final void absolutelyEqualVersionsMatch() {
		String version = "1.23.456"; //$NON-NLS-1$
		assertTrue(rule().match(new String(version), new String(version)));
	}

	@Test
	public final void absolutelyEqualNotVersionsMatch() {
		String cadabra = "some strange 59t#.iQo/ing"; //$NON-NLS-1$
		assertTrue(rule().match(new String(cadabra), new String(cadabra)));
	}

	@Test
	public final void defaultVersionsMatch() {
		String version = "0.0.0"; //$NON-NLS-1$
		assertTrue(rule().match(version, version));
	}

	/**
	 * For the same arguments matching must always return the same result
	 */
	@Test
	public final void matchingIsDeterministic() {
		String required = "1.23.456"; //$NON-NLS-1$
		String allowed = "1.23.0"; //$NON-NLS-1$
		boolean first = rule().match(required, allowed);
		boolean second = rule().match(required, allowed);
		assertTrue(first == second);
	}

	/**
	 * A matching call does not affect further calls.
	 */
	@Test
	public final void matchingIsIdempotant() {
		String required = "1.23.456"; //$NON-NLS-1$
		String allowed = "1.23.0"; //$NON-NLS-1$
		MatchingRule rule = rule();
		boolean first = rule.match(required, allowed);
		boolean second = rule.match(required, allowed);
		assertTrue(first == second);
	}

	@Test
	public final void allInstancesAreEqual() {
		assertEquals(rule(), rule());
	}

	/**
	 * An instance must hold no mutable data and be focused on the matching
	 * algorithm only.
	 */
	@Test
	public final void stateless() {
		MatchingRule rule = rule();
		rule.match("1.23.456", "stearing wheel"); //$NON-NLS-1$//$NON-NLS-2$
		assertEquals(rule(), rule);
	}

	protected abstract MatchingRule rule();

}
