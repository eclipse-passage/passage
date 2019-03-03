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

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;

import org.eclipse.emf.common.util.EList;
import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.base.LicensingNamespaces;
import org.eclipse.passage.lic.model.api.LicenseGrant;
import org.eclipse.passage.lic.model.api.LicensePack;
import org.eclipse.passage.lic.model.meta.LicFactory;
import org.eclipse.passage.lic.oshi.OshiHal;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.inspector.HardwareInspector;
import org.junit.Before;
import org.junit.Test;

public class AccessManagerIntegrationTest extends LicIntegrationBase {
	
	private static final String UNDEFINED = "undefined"; //$NON-NLS-1$
	
	@Before
	public void beforeTest() {
		System.setProperty(SOME_BUNDLE_ID, UNDEFINED);
		System.setProperty(SOME_COMPONENT_ID, UNDEFINED);
	}

	@Test
	public void testAccessManagerNoLicenseDecrypted() {
		assertEquals(UNDEFINED, System.getProperty(SOME_COMPONENT_ID, UNDEFINED));
		assertEquals(UNDEFINED, System.getProperty(SOME_BUNDLE_ID, UNDEFINED));
		accessManager.executeAccessRestrictions(LicensingConfigurations.create(SOME_DECRYPTED_PRODUCT, null));
		assertEquals(LicensingNamespaces.ATTRIBUTE_LEVEL_ERROR, System.getProperty(SOME_COMPONENT_ID, UNDEFINED));
		assertEquals(LicensingNamespaces.ATTRIBUTE_LEVEL_WARN, System.getProperty(SOME_BUNDLE_ID, UNDEFINED));
	}
	
	@Test
	public void testAccessManagerNoLicenseEncrypted() {
		assertEquals(UNDEFINED, System.getProperty(SOME_COMPONENT_ID, UNDEFINED));
		assertEquals(UNDEFINED, System.getProperty(SOME_BUNDLE_ID, UNDEFINED));
		accessManager.executeAccessRestrictions(LicensingConfigurations.create(SOME_ENCRYPTED_PRODUCT, null));
		assertEquals(LicensingNamespaces.ATTRIBUTE_LEVEL_ERROR, System.getProperty(SOME_COMPONENT_ID, UNDEFINED));
		assertEquals(LicensingNamespaces.ATTRIBUTE_LEVEL_WARN, System.getProperty(SOME_BUNDLE_ID, UNDEFINED));
	}

	@Test
	public void testAccessManagerLicensedDecrypted() throws IOException {
		LicFactory factory = LicFactory.eINSTANCE;
		LicensePack license = factory.createLicensePack();
		EList<LicenseGrant> licenseGrants = license.getLicenseGrants();
		LicenseGrant grant = factory.createLicenseGrant();
		grant.setFeatureIdentifier(SOME_BUNDLE_ID);
		grant.setConditionType(OshiHal.CONDITION_TYPE_HARDWARE);
		grant.setConditionExpression(HardwareInspector.PROPERTY_OS_FAMILY + '=' + '*');
		grant.setValidFrom(new Date(System.currentTimeMillis() - 100500));
		grant.setValidUntil(new Date(System.currentTimeMillis() + 100500));
		licenseGrants.add(grant);
		LicensingConfiguration configuration = LicensingConfigurations.create(SOME_DECRYPTED_PRODUCT, null);
	
		try {
			createProductLicense(configuration, license, false);
			
			assertEquals(UNDEFINED, System.getProperty(SOME_COMPONENT_ID, UNDEFINED));
			assertEquals(UNDEFINED, System.getProperty(SOME_BUNDLE_ID, UNDEFINED));
			accessManager.executeAccessRestrictions(configuration);
			assertEquals(LicensingNamespaces.ATTRIBUTE_LEVEL_ERROR, System.getProperty(SOME_COMPONENT_ID, UNDEFINED));
			assertEquals(UNDEFINED, System.getProperty(SOME_BUNDLE_ID, UNDEFINED));
			
		} finally {
			deleteProductLicense(configuration, false);
		}
	}

	@Test
	public void testAccessManagerLicensedEncrypted() throws IOException {
		LicFactory factory = LicFactory.eINSTANCE;
		LicensePack license = factory.createLicensePack();
		EList<LicenseGrant> licenseGrants = license.getLicenseGrants();
		LicenseGrant grant = factory.createLicenseGrant();
		grant.setFeatureIdentifier(SOME_BUNDLE_ID);
		grant.setConditionType(OshiHal.CONDITION_TYPE_HARDWARE);
		grant.setConditionExpression(HardwareInspector.PROPERTY_OS_FAMILY + '=' + '*');
		grant.setValidFrom(new Date(System.currentTimeMillis() - 100500));
		grant.setValidUntil(new Date(System.currentTimeMillis() + 100500));
		licenseGrants.add(grant);
		LicensingConfiguration configuration = LicensingConfigurations.create(SOME_ENCRYPTED_PRODUCT, null);

		try {
			createProductLicense(configuration, license, true);
			
			assertEquals(UNDEFINED, System.getProperty(SOME_COMPONENT_ID, UNDEFINED));
			assertEquals(UNDEFINED, System.getProperty(SOME_BUNDLE_ID, UNDEFINED));
			accessManager.executeAccessRestrictions(configuration);
			assertEquals(LicensingNamespaces.ATTRIBUTE_LEVEL_ERROR, System.getProperty(SOME_COMPONENT_ID, UNDEFINED));
			assertEquals(UNDEFINED, System.getProperty(SOME_BUNDLE_ID, UNDEFINED));
			
		} finally {
			deleteProductLicense(configuration, true);
		}
	}
}
