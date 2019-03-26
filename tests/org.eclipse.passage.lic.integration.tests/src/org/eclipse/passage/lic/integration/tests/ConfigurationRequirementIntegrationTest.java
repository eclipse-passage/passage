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

import static org.eclipse.passage.lic.base.LicensingProperties.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.base.LicensingVersions;
import org.eclipse.passage.lic.runtime.requirements.LicensingRequirement;
import org.junit.Test;

public class ConfigurationRequirementIntegrationTest extends LicIntegrationBase {

	@Test
	public void testResolveRequirementsPositive() {
		Iterable<LicensingRequirement> resolved = accessManager.resolveRequirements(LicensingConfigurations.INVALID);
		Map<String, LicensingRequirement> requirements = new HashMap<>();
		for (LicensingRequirement cr : resolved) {
			requirements.put(cr.getFeatureIdentifier(), cr);
		}
		assertEquals(2, requirements.size());
		LicensingRequirement bundleRequirement = requirements.get(SOME_BUNDLE_ID);
		assertNotNull(bundleRequirement);
		assertEquals(SOME_BUNDLE_ID, bundleRequirement.getFeatureIdentifier());
		assertEquals(LicensingVersions.VERSION_DEFAULT, bundleRequirement.getFeatureVersion());
		assertEquals(LICENSING_RESTRICTION_LEVEL_DEFAULT, bundleRequirement.getRestrictionLevel());

		LicensingRequirement componentRequirement = requirements.get(SOME_COMPONENT_ID);
		assertNotNull(componentRequirement);
		assertEquals(SOME_COMPONENT_ID, componentRequirement.getFeatureIdentifier());
		assertEquals(SOME_COMPONENT_VERSION, componentRequirement.getFeatureVersion());
		assertEquals(LICENSING_RESTRICTION_LEVEL_ERROR, componentRequirement.getRestrictionLevel());
	}

}
