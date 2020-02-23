/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.base.tests.requirements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.passage.lic.api.requirements.LicensingRequirement;
import org.eclipse.passage.lic.base.LicensingNamespaces;
import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.base.requirements.LicensingRequirements;
import org.junit.Test;

public class LicensingRequirementsTest {

	private static final String FEATURE_ID = "f1"; //$NON-NLS-1$

	@Test
	public void testExtractFromPropertiesNulls() {
		LicensingRequirement req = LicensingRequirements.extractFromProperties(null, null, null, null);
		assertEquals(LicensingProperties.LICENSING_RESTRICTION_LEVEL_ERROR, req.getRestrictionLevel());
		assertEquals(LicensingNamespaces.CAPABILITY_LICENSING_MANAGEMENT, req.getFeatureIdentifier());
	}

	@Test
	public void testExtractFromPropertiesInvalid() {
		LicensingRequirement req = LicensingRequirements.extractFromProperties(null, null,
				Collections.singletonMap(LicensingProperties.LICENSING_FEATURE_IDENTIFIER, new Object()), null);
		assertEquals(LicensingProperties.LICENSING_RESTRICTION_LEVEL_ERROR, req.getRestrictionLevel());
		assertEquals(LicensingNamespaces.CAPABILITY_LICENSING_MANAGEMENT, req.getFeatureIdentifier());
	}

	@Test
	public void testExtractFromPropertiesPositive() {
		Map<String, Object> map = new HashMap<>();
		final String featureId = FEATURE_ID;
		map.put(LicensingProperties.LICENSING_FEATURE_IDENTIFIER, featureId);
		LicensingRequirement extracted = LicensingRequirements.extractFromProperties(null, null, map, null);
		assertEquals(featureId, extracted.getFeatureIdentifier());
		assertTrue(extracted.toString().contains(featureId));
	}

	@Test
	public void testExtractFromCapabilityNulls() {
		LicensingRequirement req = LicensingRequirements.extractFromCapability(null, null, null, null, null);
		assertEquals(LicensingProperties.LICENSING_RESTRICTION_LEVEL_ERROR, req.getRestrictionLevel());
		assertEquals(LicensingNamespaces.CAPABILITY_LICENSING_MANAGEMENT, req.getFeatureIdentifier());
	}

	@Test
	public void testExtractFromCapabilityInvalid() {
		LicensingRequirement req = LicensingRequirements.extractFromCapability(null, null,
				Collections.singletonMap(LicensingNamespaces.CAPABILITY_LICENSING_FEATURE, new Object()), null, null);
		assertEquals(LicensingProperties.LICENSING_RESTRICTION_LEVEL_ERROR, req.getRestrictionLevel());
		assertEquals(LicensingNamespaces.CAPABILITY_LICENSING_MANAGEMENT, req.getFeatureIdentifier());
	}

	@Test
	public void testExtractFromCapabilityPositive() {
		Map<String, Object> map = new HashMap<>();
		final String featureId = FEATURE_ID;
		map.put(LicensingNamespaces.CAPABILITY_LICENSING_FEATURE, featureId);
		LicensingRequirement extracted = LicensingRequirements.extractFromCapability(null, null, map, null, null);
		assertEquals(featureId, extracted.getFeatureIdentifier());
		assertTrue(extracted.toString().contains(featureId));
	}

}
