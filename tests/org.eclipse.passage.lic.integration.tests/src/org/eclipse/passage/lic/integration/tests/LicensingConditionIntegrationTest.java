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

import org.eclipse.emf.common.util.EList;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;
import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;
import org.eclipse.passage.lic.oshi.OshiHal;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.inspector.HardwareInspector;
import org.junit.Test;

public class LicensingConditionIntegrationTest extends LicIntegrationBase {

	@Test
	public void testExtractConditionsNegative() {
		Iterable<LicensingCondition> conditionsNull = accessManager.extractConditions(null);
		assertFalse(conditionsNull.iterator().hasNext());

		
		Iterable<LicensingCondition> conditionsProduct = accessManager
				.extractConditions(LicensingConfigurations.create(null, null));
		assertFalse(conditionsProduct.iterator().hasNext());

		LicensingConfiguration configuration = LicensingConfigurations.create(SOME_ENCRYPTED_PRODUCT, null);
		Iterable<LicensingCondition> conditions = accessManager.extractConditions(configuration);
		assertFalse(conditions.iterator().hasNext());
	}

	@Test
	public void testExtractConditionsDecryptedNegative() {
		LicensingConfiguration configuration = LicensingConfigurations.create(SOME_DECRYPTED_PRODUCT, null);
		Iterable<LicensingCondition> conditions = accessManager.extractConditions(configuration);
		assertFalse(conditions.iterator().hasNext());
	}

	@Test
	public void testExtractConditionsEncryptedNegative() {
		LicensingConfiguration configuration = LicensingConfigurations.create(SOME_ENCRYPTED_PRODUCT, null);
		Iterable<LicensingCondition> conditions = accessManager.extractConditions(configuration);
		assertFalse(conditions.iterator().hasNext());
	}

	@Test
	public void testExtractConditionsDecryptedPositive() throws Exception {
		LicensesFactory factory = LicensesFactory.eINSTANCE;
		LicensePack license = factory.createLicensePack();
		EList<LicenseGrant> licenseGrants = license.getLicenseGrants();
		LicenseGrant conditionBundle = factory.createLicenseGrant();
		conditionBundle.setFeatureIdentifier(SOME_BUNDLE_ID);
		conditionBundle.setConditionType(OshiHal.CONDITION_TYPE_HARDWARE);
		conditionBundle.setConditionExpression(HardwareInspector.PROPERTY_OS_FAMILY + '=' + '*');
		licenseGrants.add(conditionBundle);

		String identifier = SOME_DECRYPTED_PRODUCT;
		LicensingConfiguration configuration = LicensingConfigurations.create(identifier, null);
		createProductLicense(configuration, license, false);
		Iterable<LicensingCondition> conditions = accessManager.extractConditions(configuration);
		assertTrue(conditions.iterator().hasNext());
		deleteProductLicense(configuration, false);
	}

	@Test
	public void testExtractConditionsEncryptedPositive() throws Exception {
		LicensesFactory factory = LicensesFactory.eINSTANCE;
		LicensePack license = factory.createLicensePack();
		EList<LicenseGrant> licenseGrants = license.getLicenseGrants();
		LicenseGrant conditionBundle = factory.createLicenseGrant();
		conditionBundle.setFeatureIdentifier(SOME_BUNDLE_ID);
		conditionBundle.setConditionType(OshiHal.CONDITION_TYPE_HARDWARE);
		conditionBundle.setConditionExpression(HardwareInspector.PROPERTY_OS_FAMILY + '=' + '*');
		licenseGrants.add(conditionBundle);

		String identifier = SOME_ENCRYPTED_PRODUCT;
		LicensingConfiguration configuration = LicensingConfigurations.create(identifier, null);
		createProductLicense(configuration, license, true);
		Iterable<LicensingCondition> conditions = accessManager.extractConditions(configuration);
		assertTrue(conditions.iterator().hasNext());
		deleteProductLicense(configuration, true);
	}

	@Test
	public void testExtractConditionsServerPositive() throws Exception {
		LicensesFactory factory = LicensesFactory.eINSTANCE;
		LicensePack license = factory.createLicensePack();
		EList<LicenseGrant> licenseGrants = license.getLicenseGrants();
		LicenseGrant conditionBundle = factory.createLicenseGrant();
		conditionBundle.setFeatureIdentifier(SOME_BUNDLE_ID);
		conditionBundle.setConditionType(OshiHal.CONDITION_TYPE_HARDWARE);
		conditionBundle.setConditionExpression(HardwareInspector.PROPERTY_OS_FAMILY + '=' + '*');
		licenseGrants.add(conditionBundle);

		String identifier = SOME_ENCRYPTED_PRODUCT;
		LicensingConfiguration configuration = LicensingConfigurations.create(identifier, null);
		createProductLicense(configuration, license, true);
		createServerConfiguration(configuration);
		Iterable<LicensingCondition> conditions = accessManager.extractConditions(configuration);
		assertTrue(conditions.iterator().hasNext());
		deleteProductLicense(configuration, true);
		deleteServerConfiguration(configuration);
	}

}
