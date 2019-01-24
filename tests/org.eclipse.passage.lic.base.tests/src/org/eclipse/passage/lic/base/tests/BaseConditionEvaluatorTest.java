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

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.passage.lic.base.BaseConditionEvaluator;
import org.junit.After;
import org.junit.Test;

public class BaseConditionEvaluatorTest {
	
	private Map<String, Object> segments = new HashMap<>();
	private Map<String, Object> log = new HashMap<>();
	
	private BaseConditionEvaluator evaluator = new BaseConditionEvaluator() {
		
		@Override
		protected boolean evaluateSegment(String key, String value) {
			return false;
		}

		@Override
		protected void logError(String message, Throwable e) {
			log.put(message, e);
		}

	};
	
	@After
	public void tearDown() {
		log.clear();
		segments.clear();
	}

	@Test
	public void testEvaluateConditions() {
		evaluator.evaluateConditions(null, null);
		assertEquals(1, log.size());
	}


}
