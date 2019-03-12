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
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNoException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;

import org.eclipse.passage.lic.base.access.BaseFeaturePermission;
import org.eclipse.passage.lic.base.access.FeaturePermissions;
import org.eclipse.passage.lic.base.conditions.BaseLicensingCondition;
import org.eclipse.passage.lic.base.conditions.LicensingConditions;
import org.eclipse.passage.lic.internal.json.FeaturePermissionAggregator;
import org.eclipse.passage.lic.internal.json.FeaturePermissionMixln;
import org.eclipse.passage.lic.internal.json.JsonFeaturePermissionTransport;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.access.FeaturePermission;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("restriction")
public class JsonFeaturePermissionTransportTest {

	private static final String FEATURE_TEST_VERSION = "test.version.1"; //$NON-NLS-1$
	private static final String FEATURE_TEST_ID = "test.id"; //$NON-NLS-1$
	private static final String FEATURE_TEST_RULE = "test.rule"; //$NON-NLS-1$
	private static final Date FEATURE_TEST_LEASE_TIME = new Date(System.currentTimeMillis() - 3000l);
	private static final Date FEATURE_TEST_EXPIRE_TIME = new Date(System.currentTimeMillis() + 4000l);

	@Test
	public void netFeaturePermissionTransportTest() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
		mapper.addMixIn(BaseFeaturePermission.class, FeaturePermissionMixln.class);

		FeaturePermissionAggregator conditionAggregator = createFeaturePermissionAggregator();
		try {
			byte[] byteValues = mapper.writeValueAsBytes(conditionAggregator);
			ByteArrayInputStream bis = new ByteArrayInputStream(byteValues);
			JsonFeaturePermissionTransport transport = new JsonFeaturePermissionTransport();
			Iterable<FeaturePermission> permissions = transport.readFeaturePermissions(bis);
			assertNotNull(permissions);
			for (FeaturePermission permission : permissions) {
				LicensingCondition condition = permission.getLicensingCondition();
				assertNotNull(condition);
				assertTrue(condition.getFeatureIdentifier().equals(FEATURE_TEST_ID));
				assertTrue(condition.getMatchVersion().equals(FEATURE_TEST_VERSION));
				assertTrue(condition.getMatchRule().equals(FEATURE_TEST_RULE));
				assertTrue(permission.getLeaseDate() == FEATURE_TEST_LEASE_TIME);
				assertTrue(permission.getExpireDate() == FEATURE_TEST_EXPIRE_TIME);
			}

		} catch (JsonProcessingException e) {
			assumeNoException(e);
		} catch (IOException e) {
			assumeNoException(e);
		}
	}

	private FeaturePermissionAggregator createFeaturePermissionAggregator() {
		FeaturePermissionAggregator permissionAggregator = new FeaturePermissionAggregator();
		//FIXME: rework nulls and check the result 
		LicensingConfiguration configuration = null;
		Date from = null;
		Date until = null;
		BaseLicensingCondition condition = LicensingConditions.create(FEATURE_TEST_ID, FEATURE_TEST_VERSION, FEATURE_TEST_RULE, from, until, null, null);
		BaseFeaturePermission permission = FeaturePermissions.create(condition, configuration, FEATURE_TEST_LEASE_TIME, FEATURE_TEST_EXPIRE_TIME);
		permissionAggregator.addFeaturePermission(permission);
		return permissionAggregator;

	}
}
