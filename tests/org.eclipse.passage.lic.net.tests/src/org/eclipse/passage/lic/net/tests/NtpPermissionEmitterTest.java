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
package org.eclipse.passage.lic.net.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.access.FeaturePermission;
import org.eclipse.passage.lic.api.access.PermissionEmitter;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;
import org.eclipse.passage.lic.base.conditions.LicensingConditions;
import org.eclipse.passage.lic.internal.net.NtpPermissionEmitter;
import org.eclipse.passage.lic.net.TimeConditions;
import org.junit.Test;

@SuppressWarnings("restriction")
public class NtpPermissionEmitterTest {

	static final String NET_TIME_FEATURE_ID = "net.time"; //$NON-NLS-1$
	static final String NET_TIME_MATCH_VERSION = "0.3.0"; //$NON-NLS-1$
	static final String NET_TIME_MATCH_RULE = "exact"; //$NON-NLS-1$

	private static final String EXPRESSION_EXPIRED = "localtimestamp=2017-02-02T12:00:00"; //$NON-NLS-1$
	private static final String EXPRESSION_FUTURE = "localtimestamp=2027-02-02T12:00:00"; //$NON-NLS-1$
	private static final String EXPRESSION_UNKNOWN = "ntp=2027-02-02T12:00:00"; //$NON-NLS-1$

	@Test
	public void testEvaluateConditionNegative() {
		NtpPermissionEmitter evaluator = new NtpPermissionEmitter();
		evaluate(evaluator, null, null);

		Set<LicensingCondition> empty = Collections.singleton(createNetCondition(new String()));
		evaluate(evaluator, empty, null);

		Set<LicensingCondition> expired = Collections.singleton(createNetCondition(EXPRESSION_EXPIRED));
		evaluate(evaluator, expired, null);

		Set<LicensingCondition> unknown = Collections.singleton(createNetCondition(EXPRESSION_UNKNOWN));
		evaluate(evaluator, unknown, null);
	}

	private void evaluate(PermissionEmitter emitter, Set<LicensingCondition> conditions, LicensingConfiguration configuration) {
		try {
			emitter.emitPermissions(configuration, conditions);
			fail("Should not accept invalid arguments");
		} catch (LicensingException e) {
			// expected
		}
	}

	@Test
	public void testEvaluateConditionPositive() throws Exception {
		NtpPermissionEmitter evaluator = new NtpPermissionEmitter();
		Set<LicensingCondition> future = Collections.singleton(createNetCondition(EXPRESSION_FUTURE));
		Iterator<FeaturePermission> iterator = evaluator.emitPermissions(null, future).iterator();
		assertTrue(iterator.hasNext());
		FeaturePermission permission = iterator.next();
		LicensingCondition condition = permission.getLicensingCondition();
		assertNotNull(condition);
		assertEquals(NET_TIME_FEATURE_ID, condition.getFeatureIdentifier());
		assertEquals(NET_TIME_MATCH_RULE, condition.getMatchRule());
		assertEquals(NET_TIME_MATCH_VERSION, condition.getMatchVersion());
	}

	@Test
	public void testNetCondition() throws Exception {
		LicensingCondition netCondition = createNetCondition(EXPRESSION_EXPIRED);
		assertEquals(EXPRESSION_EXPIRED, netCondition.getConditionExpression());
		assertEquals(TimeConditions.CONDITION_TYPE_TIME, netCondition.getConditionType());
		assertEquals(NET_TIME_FEATURE_ID, netCondition.getFeatureIdentifier());
		assertEquals(NET_TIME_MATCH_RULE, netCondition.getMatchRule());
		assertEquals(NET_TIME_MATCH_VERSION, netCondition.getMatchVersion());
	}

	public static LicensingCondition createNetCondition(String expression) {
		String id = NET_TIME_FEATURE_ID;
		String version = NET_TIME_MATCH_VERSION;
		String rule = NET_TIME_MATCH_RULE;
		String type = TimeConditions.CONDITION_TYPE_TIME;
		Date from = null;
		Date until = null;
		return LicensingConditions.create(id, version, rule, from, until, type, expression);
	}

}
