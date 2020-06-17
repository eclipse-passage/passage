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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lic.internal.api.conditions.MatchingRule;
import org.junit.Test;

@SuppressWarnings("restriction")
public abstract class MatchingRuleContractTest {

	@Test
	public void absolutelyEqualVersionsMatch() {
		String version = "1.23.456";
		assertTrue(create().match(version, version));
	}

	@Test
	public void notVersionFailMatchiing() {
		String incorrect = "this .is . not . a . version";
		assertFalse(create().match(incorrect, incorrect));
		assertFalse(create().match(incorrect, "1.2.3"));
		assertFalse(create().match("3.2.1", incorrect));
	}

	/**
	 * For the same arguments matching must always return the same result 
	 */
	@Test
	public void matchingIsDeterministic() {
		String required = "1.23.456";
		String allowed = "1.23.0";
		boolean first = create().match(required, allowed);
		boolean second = create().match(required, allowed);
		assertTrue(first == second);
	}
	

	/**
	 * A matching call does not affect further calls.
	 */
	@Test
	public void matchingIsIdempotant() {
		String required = "1.23.456";
		String allowed = "1.23.0";
		MatchingRule rule = create();
		boolean first = rule.match(required, allowed);
		boolean second = rule.match(required, allowed);
		assertTrue(first == second);
	}

	protected abstract MatchingRule create();

}
