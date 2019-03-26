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
package org.eclipse.passage.lic.json.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.util.Date;

import org.eclipse.passage.lic.base.conditions.LicensingConditions;
import org.eclipse.passage.lic.internal.json.JsonConditionTransport;
import org.eclipse.passage.lic.internal.json.JsonTransport;
import org.eclipse.passage.lic.internal.json.LicensingConditionAggregator;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("restriction")
public class JsonConditionTransportTest {

	private static final String FEATURE_TEST_ID = "test.id"; //$NON-NLS-1$
	private static final String FEATURE_TEST_VERSION = "test.version.1"; //$NON-NLS-1$
	private static final String FEATURE_TEST_RULE = "test.rule"; //$NON-NLS-1$

	private static final Date FEATURE_TEST_VALID_FROM = new Date(System.currentTimeMillis() - 5000l);
	private static final Date FEATURE_TEST_VALID_UNTIL = new Date(System.currentTimeMillis() + 6000l);

	private static final String FEATURE_TEST_CONDITION_TYPE = "test.content.type"; //$NON-NLS-1$
	private static final String FEATURE_TEST_EXPRESSION = "test.expression"; //$NON-NLS-1$

	@Test
	public void netConditionDescriptorTransportTest() throws Exception {
		ObjectMapper mapper = JsonTransport.createObjectMapper();

		LicensingConditionAggregator conditionAggregator = createConditionDescriptorAggregator();
		byte[] byteValues = mapper.writeValueAsBytes(conditionAggregator);
		ByteArrayInputStream bis = new ByteArrayInputStream(byteValues);
		JsonConditionTransport transport = new JsonConditionTransport();
		Iterable<LicensingCondition> conditions = transport.readConditions(bis);
		assertNotNull(conditions);
		for (LicensingCondition condition : conditions) {
			assertEquals(FEATURE_TEST_ID, condition.getFeatureIdentifier());
			assertEquals(FEATURE_TEST_VERSION, condition.getMatchVersion());
			assertEquals(FEATURE_TEST_RULE, condition.getMatchRule());
			assertEquals(FEATURE_TEST_VALID_FROM, condition.getValidFrom());
			assertEquals(FEATURE_TEST_VALID_UNTIL, condition.getValidUntil());
			assertEquals(FEATURE_TEST_CONDITION_TYPE, condition.getConditionType());
			assertEquals(FEATURE_TEST_EXPRESSION, condition.getConditionExpression());
		}
	}

	private LicensingConditionAggregator createConditionDescriptorAggregator() {
		LicensingConditionAggregator conditionAggregator = new LicensingConditionAggregator();
		String id = FEATURE_TEST_ID;
		String version = FEATURE_TEST_VERSION;
		String rule = FEATURE_TEST_RULE;
		Date from = FEATURE_TEST_VALID_FROM;
		Date until = FEATURE_TEST_VALID_UNTIL;
		String type = FEATURE_TEST_CONDITION_TYPE;
		String expression = FEATURE_TEST_EXPRESSION;
		LicensingCondition descriptor = LicensingConditions.create(id, version, rule, from, until, type, expression);
		conditionAggregator.addLicensingCondition(descriptor);
		return conditionAggregator;
	}
}
