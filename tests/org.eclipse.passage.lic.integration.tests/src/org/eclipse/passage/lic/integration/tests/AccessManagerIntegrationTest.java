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

import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.base.LicensingNamespaces;
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

}
