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

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.passage.lic.base.conditions.BaseConditionEvaluator;
import org.eclipse.passage.lic.runtime.LicensingException;
import org.junit.After;
import org.junit.Test;

public class BaseConditionEvaluatorTest {

	private Map<String, Object> segments = new HashMap<>();

	private BaseConditionEvaluator evaluator = new BaseConditionEvaluator() {

		@Override
		protected boolean evaluateSegment(String key, String value) {
			return false;
		}

	};

	@After
	public void tearDown() {
		segments.clear();
	}

	@Test
	public void testEvaluateConditions() {
		try {
			evaluator.evaluateConditions(null, null);
			fail("Should not accept invalid arguments");
		} catch (LicensingException e) {
			// expected
		}
	}

}
