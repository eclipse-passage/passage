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
package org.eclipse.passage.lic.integration.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Date;

import org.eclipse.emf.common.util.EList;
import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.api.access.FeaturePermission;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;
import org.eclipse.passage.lic.api.inspector.HardwareInspector;
import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;
import org.eclipse.passage.lic.oshi.OshiHal;
import org.junit.Test;

public class FeaturePermissionIntegrationTest extends LicIntegrationBase {

	@Test
	public void testEvaluateConditionsNegative() {
		Iterable<FeaturePermission> permissionsNull = accessManager.evaluateConditions(null, null);
		assertFalse(permissionsNull.iterator().hasNext());

		Iterable<FeaturePermission> permissionsEmpty = accessManager.evaluateConditions(null, Collections.emptyList());
		assertFalse(permissionsEmpty.iterator().hasNext());

	}

	@Test
	public void testEvaluateConditionsDecryptedPositive() throws Exception {
		LicensesFactory factory = LicensesFactory.eINSTANCE;
		LicensePack license = factory.createLicensePack();
		EList<LicenseGrant> licenseGrants = license.getLicenseGrants();
		LicenseGrant grant = factory.createLicenseGrant();
		grant.setFeatureIdentifier(SOME_BUNDLE_ID);
		grant.setConditionType(OshiHal.CONDITION_TYPE_HARDWARE);
		grant.setConditionExpression(HardwareInspector.PROPERTY_OS_FAMILY + '=' + '*');
		grant.setValidFrom(new Date(System.currentTimeMillis() - 100500));
		grant.setValidUntil(new Date(System.currentTimeMillis() + 100500));
		licenseGrants.add(grant);

		String identifier = SOME_DECRYPTED_PRODUCT;
		LicensingConfiguration configuration = LicensingConfigurations.create(identifier, null);
		createProductLicense(configuration, license, false);
		Iterable<LicensingCondition> conditions = accessManager.extractConditions(configuration);
		assertTrue(conditions.iterator().hasNext());
		Iterable<FeaturePermission> permissions = accessManager.evaluateConditions(configuration, conditions);
		assertTrue(permissions.iterator().hasNext());
		deleteProductLicense(configuration, false);
	}

	@Test
	public void testEvaluateConditionsEncryptedPositive() throws Exception {
		LicensesFactory factory = LicensesFactory.eINSTANCE;
		LicensePack license = factory.createLicensePack();
		EList<LicenseGrant> licenseGrants = license.getLicenseGrants();
		LicenseGrant grant = factory.createLicenseGrant();
		grant.setFeatureIdentifier(SOME_BUNDLE_ID);
		grant.setConditionType(OshiHal.CONDITION_TYPE_HARDWARE);
		grant.setConditionExpression(HardwareInspector.PROPERTY_OS_FAMILY + '=' + '*');
		grant.setValidFrom(new Date(System.currentTimeMillis() - 100500));
		grant.setValidUntil(new Date(System.currentTimeMillis() + 100500));
		licenseGrants.add(grant);

		String identifier = SOME_ENCRYPTED_PRODUCT;
		LicensingConfiguration configuration = LicensingConfigurations.create(identifier, SOME_PRODUCT_VERSION);
		createProductLicense(configuration, license, true);
		Iterable<LicensingCondition> conditions = accessManager.extractConditions(configuration);
		assertTrue(conditions.iterator().hasNext());
		Iterable<FeaturePermission> permissions = accessManager.evaluateConditions(configuration, conditions);
		assertTrue(permissions.iterator().hasNext());
		deleteProductLicense(configuration, true);
	}

}
