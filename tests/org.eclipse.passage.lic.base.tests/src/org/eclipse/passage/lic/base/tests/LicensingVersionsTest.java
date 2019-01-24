/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.base.tests;

import static org.eclipse.passage.lic.base.LicensingVersions.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

@SuppressWarnings("nls")
public class LicensingVersionsTest {
	
	@Test
	public void testToVersionValue() {
		assertEquals(VERSION_DEFAULT, toVersionValue(this));
		assertEquals(VERSION_DEFAULT, toVersionValue(this.toString()));
		assertEquals(VERSION_DEFAULT, toVersionValue(String.valueOf(' ')));
		assertEquals("1.0.0", toVersionValue("1"));
		assertEquals("1.0.0", toVersionValue("1.a"));
		assertEquals("1.2.0", toVersionValue("1.2"));
		assertEquals("1.2.0", toVersionValue("1.2.x"));
		assertEquals("1.2.3", toVersionValue("1.2.3"));
		assertEquals("1.2.3.x3", toVersionValue("1.2.3.x3"));
	}

	@Test
	public void testToRuleValue() {
		assertEquals(RULE_DEFAULT, toRuleValue(this));
		assertEquals(RULE_DEFAULT, toRuleValue(this.toString()));

		assertEquals(RULE_COMPATIBLE, toRuleValue(RULE_COMPATIBLE));
		assertEquals(RULE_EQUIVALENT, toRuleValue(RULE_EQUIVALENT));
		assertEquals(RULE_GREATER_OR_EQUAL, toRuleValue(RULE_GREATER_OR_EQUAL));
		assertEquals(RULE_PERFECT, toRuleValue(RULE_PERFECT));

		assertEquals(RULE_COMPATIBLE, toRuleValue("coMpaTible")); 
		assertEquals(RULE_EQUIVALENT, toRuleValue("eQuIVALenT"));
		assertEquals(RULE_GREATER_OR_EQUAL, toRuleValue("gREaterORequAL"));
		assertEquals(RULE_PERFECT, toRuleValue("perFecT"));
	}

	@Test
	public void testIsMatchNegative() {
		assertEquals(false, isMatch(null, null, null));
		assertEquals(false, isMatch(null, null, new String()));
		assertEquals(false, isMatch(null, new String(), null));
		assertEquals(false, isMatch(null, new String(), new String()));
		assertEquals(false, isMatch(new String(), null, null));
		assertEquals(false, isMatch(new String(), null, new String()));
		assertEquals(true, isMatch(new String(), new String(), null));
		assertEquals(true, isMatch(new String(), new String(), new String()));

		assertEquals(true, isMatch("1.2.3", "1.2.3", null));
		assertEquals(true, isMatch("1.2.3", "1.2.3.x3", null));
		assertEquals(true, isMatch("1.2.3", "1.2.3", new String()));
		assertEquals(true, isMatch("1.2.3", "1.2.3.x3", new String()));
	}

	@Test
	public void testIsMatchGreaterOrEqual() {
		assertEquals(false, isMatch(null, null, RULE_GREATER_OR_EQUAL));
		assertEquals(true, isMatch(new String(), new String(), RULE_GREATER_OR_EQUAL));
		assertEquals(true, isMatch("1.2.1", "1.2.0", RULE_GREATER_OR_EQUAL));
		assertEquals(true, isMatch("1.2.0", "1.2.1", RULE_GREATER_OR_EQUAL));
		assertEquals(true, isMatch("1.3.0", "1.2.0", RULE_GREATER_OR_EQUAL));
		assertEquals(true, isMatch("1.3.0.a", "1.2.0.b", RULE_GREATER_OR_EQUAL));
		assertEquals(true, isMatch("1.2.0.a", "1.2.0.b", RULE_GREATER_OR_EQUAL));
		assertEquals(true, isMatch("2.0.0.a", "1.2.0.a", RULE_GREATER_OR_EQUAL));
		assertEquals(false, isMatch("1.2.0.a", "2.0.0.a", RULE_GREATER_OR_EQUAL));
		assertEquals(true, isMatch("1.2.0.a", "0.0.0", RULE_GREATER_OR_EQUAL));
		assertEquals(true, isMatch("0.0.0", "1.2.0.a", RULE_GREATER_OR_EQUAL));
	}

	@Test
	public void testIsMatchCompatible() {
		assertEquals(false, isMatch(null, null, RULE_COMPATIBLE));
		assertEquals(true, isMatch(new String(), new String(), RULE_COMPATIBLE));
		assertEquals(true, isMatch("1.2.1", "1.2.0", RULE_COMPATIBLE));
		assertEquals(true, isMatch("1.2.0", "1.2.1", RULE_COMPATIBLE));
		assertEquals(true, isMatch("1.3.0", "1.2.0", RULE_COMPATIBLE));
		assertEquals(true, isMatch("1.3.0.a", "1.2.0.b", RULE_COMPATIBLE));
		assertEquals(true, isMatch("1.2.0.a", "1.2.0.b", RULE_COMPATIBLE));
		assertEquals(false, isMatch("2.0.0.a", "1.2.0.a", RULE_COMPATIBLE));
		assertEquals(false, isMatch("1.2.0.a", "0.0.0", RULE_COMPATIBLE));
		assertEquals(true, isMatch("0.0.0", "1.2.0.a", RULE_COMPATIBLE));
	}

	@Test
	public void testIsMatchEquivalent() {
		assertEquals(false, isMatch(null, null, RULE_EQUIVALENT));
		assertEquals(true, isMatch(new String(), new String(), RULE_EQUIVALENT));
		assertEquals(true, isMatch("1.2.1", "1.2.0", RULE_EQUIVALENT));
		assertEquals(false, isMatch("1.2.0", "1.2.1", RULE_EQUIVALENT));
		assertEquals(false, isMatch("1.3.0", "1.2.0", RULE_EQUIVALENT));
		assertEquals(false, isMatch("1.3.0.a", "1.2.0.b", RULE_EQUIVALENT));
		assertEquals(true, isMatch("1.2.0.a", "1.2.0.b", RULE_EQUIVALENT));
		assertEquals(false, isMatch("2.0.0.a", "1.2.0.a", RULE_EQUIVALENT));
		assertEquals(false, isMatch("1.2.0.a", "0.0.0", RULE_EQUIVALENT));
		assertEquals(true, isMatch("0.0.0", "1.2.0.a", RULE_EQUIVALENT));
	}

	@Test
	public void testIsMatchPerfect() {
		assertEquals(false, isMatch(null, null, RULE_PERFECT));
		assertEquals(true, isMatch(new String(), new String(), RULE_PERFECT));
		assertEquals(false, isMatch("1.2.1", "1.2.0", RULE_PERFECT));
		assertEquals(false, isMatch("1.3.0", "1.2.0", RULE_PERFECT));
		assertEquals(false, isMatch("1.3.0.a", "1.2.0.b", RULE_PERFECT));
		assertEquals(false, isMatch("1.2.0.a", "1.2.0.b", RULE_PERFECT));
		assertEquals(false, isMatch("2.0.0.a", "1.2.0.a", RULE_PERFECT));
		assertEquals(true, isMatch("1.2.0.a", "1.2.0.a", RULE_PERFECT));
		assertEquals(false, isMatch("1.2.0.a", "0.0.0", RULE_PERFECT));
		assertEquals(false, isMatch("0.0.0", "1.2.0.a", RULE_PERFECT));
	}

}
