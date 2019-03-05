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
package org.eclipse.passage.lic.oshi.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.passage.lic.base.conditions.LicensingConditions;
import org.eclipse.passage.lic.internal.oshi.OshiConditionEvaluator;
import org.eclipse.passage.lic.oshi.OshiHal;
import org.eclipse.passage.lic.runtime.FeaturePermission;
import org.eclipse.passage.lic.runtime.LicensingCondition;
import org.junit.Test;
import org.mockito.Mockito;
import org.osgi.service.log.LogService;

@SuppressWarnings("restriction")
public class OshiConditionEvaluatorTest {

	static final String OSHI_HARDWARE_FEATURE_ID = "oshi.hardware"; //$NON-NLS-1$
	static final String OSHI_HARDWARE_MATCH_VERSION = "0.3.0"; //$NON-NLS-1$
	static final String OSHI_HARDWARE_MATCH_RULE = "exact"; //$NON-NLS-1$

	private static final String EXPRESSION_OS_ANY = "os.family=*"; //$NON-NLS-1$
	private static final String EXPRESSION_OS_X3 = "os.family=X3"; //$NON-NLS-1$

	@Test
	public void testEvaluateConditionNegative() throws Exception {
		OshiConditionEvaluator evaluator = new OshiConditionEvaluator();
		evaluator.bindLogService(Mockito.mock(LogService.class));
		assertEmpty(evaluator.evaluateConditions(null, null));

		Set<LicensingCondition> empty = Collections.singleton(createOshiCondition(new String()));
		assertEmpty(evaluator.evaluateConditions(empty, null));

		Set<LicensingCondition> unknown = Collections.singleton(createOshiCondition(EXPRESSION_OS_X3));
		assertEmpty(evaluator.evaluateConditions(unknown, null));
	}

	@Test
	public void testEvaluateConditionPositive() throws Exception {
		OshiConditionEvaluator evaluator = new OshiConditionEvaluator();
		evaluator.bindLogService(Mockito.mock(LogService.class));
		Set<LicensingCondition> future = Collections.singleton(createOshiCondition(EXPRESSION_OS_ANY));
		Iterator<FeaturePermission> iterator = evaluator.evaluateConditions(future, null).iterator();
		assertTrue(iterator.hasNext());
		FeaturePermission permission = iterator.next();
		LicensingCondition condition = permission.getLicensingCondition();
		assertNotNull(condition);
		assertEquals(OSHI_HARDWARE_FEATURE_ID, condition.getFeatureIdentifier());
		assertEquals(OSHI_HARDWARE_MATCH_RULE, condition.getMatchRule());
		assertEquals(OSHI_HARDWARE_MATCH_VERSION, condition.getMatchVersion());
	}

	@Test
	public void testOshiCondition() throws Exception {
		LicensingCondition condition = createOshiCondition(EXPRESSION_OS_X3);
		assertEquals(EXPRESSION_OS_X3, condition.getConditionExpression());
		assertEquals(OshiHal.CONDITION_TYPE_HARDWARE, condition.getConditionType());
		assertEquals(OSHI_HARDWARE_FEATURE_ID, condition.getFeatureIdentifier());
		assertEquals(OSHI_HARDWARE_MATCH_RULE, condition.getMatchRule());
		assertEquals(OSHI_HARDWARE_MATCH_VERSION, condition.getMatchVersion());
	}

	private void assertEmpty(Iterable<FeaturePermission> iterable) {
		assertFalse(iterable.iterator().hasNext());
	}

	public static LicensingCondition createOshiCondition(String expression) {
		String id = OSHI_HARDWARE_FEATURE_ID;
		String version = OSHI_HARDWARE_MATCH_VERSION;
		String rule = OSHI_HARDWARE_MATCH_RULE;
		String type = OshiHal.CONDITION_TYPE_HARDWARE;
		Date from = null;
		Date until = null;
		return LicensingConditions.create(id, version, rule, from, until, type, expression);
	}

}
