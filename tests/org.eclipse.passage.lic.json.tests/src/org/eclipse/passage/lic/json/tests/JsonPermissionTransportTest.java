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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.util.Date;

import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.base.access.FeaturePermissions;
import org.eclipse.passage.lic.base.conditions.LicensingConditions;
import org.eclipse.passage.lic.internal.json.FeaturePermissionAggregator;
import org.eclipse.passage.lic.internal.json.JsonPermissionTransport;
import org.eclipse.passage.lic.internal.json.JsonTransport;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.access.FeaturePermission;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("restriction")
public class JsonPermissionTransportTest {

	private static final String PRODUCT_TEST_ID = "product.id"; //$NON-NLS-1$
	private static final String PRODUCT_TEST_VERSION = "product.version.1"; //$NON-NLS-1$

	private static final String FEATURE_TEST_VERSION = "test.version.1"; //$NON-NLS-1$
	private static final String FEATURE_TEST_ID = "test.id"; //$NON-NLS-1$
	private static final String FEATURE_TEST_RULE = "test.rule"; //$NON-NLS-1$
	private static final Date FEATURE_TEST_LEASE_TIME = new Date(System.currentTimeMillis() - 3000l);
	private static final Date FEATURE_TEST_EXPIRE_TIME = new Date(System.currentTimeMillis() + 4000l);
	private static final Date FEATURE_TEST_VALID_FROM = new Date(System.currentTimeMillis() - 5000l);
	private static final Date FEATURE_TEST_VALID_UNTIL = new Date(System.currentTimeMillis() + 6000l);

	private static final String FEATURE_TEST_CONDITION_TYPE = "test.content.type"; //$NON-NLS-1$
	private static final String FEATURE_TEST_EXPRESSION = "test.expression"; //$NON-NLS-1$


	@Test
	public void netFeaturePermissionTransportTest() throws Exception {
		ObjectMapper mapper = JsonTransport.createObjectMapper();

		FeaturePermissionAggregator conditionAggregator = createFeaturePermissionAggregator();
		byte[] byteValues = mapper.writeValueAsBytes(conditionAggregator);
		ByteArrayInputStream bis = new ByteArrayInputStream(byteValues);
		JsonPermissionTransport transport = new JsonPermissionTransport();
		Iterable<FeaturePermission> permissions = transport.readPermissions(bis);
		assertNotNull(permissions);
		for (FeaturePermission permission : permissions) {
			LicensingCondition condition = permission.getLicensingCondition();
			LicensingConfiguration configuration = permission.getLicensingConfiguration();
			assertEquals(FEATURE_TEST_ID, condition.getFeatureIdentifier());
			assertEquals(FEATURE_TEST_VERSION, condition.getMatchVersion());
			assertEquals(FEATURE_TEST_RULE, condition.getMatchRule());
			assertEquals(FEATURE_TEST_VALID_FROM, condition.getValidFrom());
			assertEquals(FEATURE_TEST_VALID_UNTIL, condition.getValidUntil());
			assertEquals(FEATURE_TEST_CONDITION_TYPE, condition.getConditionType());
			assertEquals(FEATURE_TEST_EXPRESSION, condition.getConditionExpression());

			assertEquals(PRODUCT_TEST_ID, configuration.getProductIdentifier());
			assertEquals(PRODUCT_TEST_VERSION, configuration.getProductVersion());

			assertEquals(FEATURE_TEST_LEASE_TIME, permission.getLeaseDate());
			assertEquals(FEATURE_TEST_EXPIRE_TIME, permission.getExpireDate());
		}
	}

	private FeaturePermissionAggregator createFeaturePermissionAggregator() {
		FeaturePermissionAggregator permissionAggregator = new FeaturePermissionAggregator();
		LicensingConfiguration configuration = LicensingConfigurations.create(PRODUCT_TEST_ID, PRODUCT_TEST_VERSION);
		Date from = FEATURE_TEST_VALID_FROM;
		Date until = FEATURE_TEST_VALID_UNTIL;
		String type = FEATURE_TEST_CONDITION_TYPE;
		String expression = FEATURE_TEST_EXPRESSION;
		LicensingCondition condition = LicensingConditions.create(FEATURE_TEST_ID, FEATURE_TEST_VERSION, FEATURE_TEST_RULE, from, until, type, expression);
		FeaturePermission permission = FeaturePermissions.create(configuration, condition, FEATURE_TEST_LEASE_TIME, FEATURE_TEST_EXPIRE_TIME);
		permissionAggregator.addFeaturePermission(permission);
		return permissionAggregator;

	}
}
