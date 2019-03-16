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
package org.eclipse.passage.lic.features.migration.tests;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.passage.lic.features.model.api.Feature;
import org.eclipse.passage.lic.features.model.api.FeatureSet;
import org.eclipse.passage.lic.features.model.api.FeatureVersion;
import org.junit.Test;

public class FeaturesMigratorTest {

	@Test
	public void testMigratorPositive() throws Exception {
		File legacy = new File(System.getProperty("user.dir") + File.separator + "model/org.eclipse.passage.lic.lic_features");
		URI uri = URI.createFileURI(legacy.getPath());
		Resource resource = new XMIResourceImpl(uri);
		resource.load(null);
		EList<EObject> contents = resource.getContents();
		EObject eObject = contents.get(0);

		FeatureSet featureSet = FeatureSet.class.cast(eObject);
		assertEquals("org.eclipse.passage.lic", featureSet.getIdentifier());
		assertEquals("Eclipse Passage LIC", featureSet.getName());
		assertEquals("Eclipse Passage Licensing Integration Components", featureSet.getDescription());

		EList<Feature> features = featureSet.getFeatures();
		assertEquals(2, features.size());

		Feature feature0 = features.get(0);
		assertEquals("org.eclipse.passage.lic.launch", feature0.getIdentifier());
		assertEquals("Eclipse Passage Launch", feature0.getName());
		assertEquals("Eclipse Passage startup feature", feature0.getDescription());
		
		EList<FeatureVersion> feature0Versions = feature0.getFeatureVersions();
		assertEquals(1, feature0Versions.size());
		FeatureVersion feature0Version0 = feature0Versions.get(0);
		assertEquals("0.3.3", feature0Version0.getVersion());

		Feature feature1 = features.get(1);
		assertEquals("org.eclipse.passage.lic.product", feature1.getIdentifier());
		assertEquals("Eclipse Passage Product", feature1.getName());
		assertEquals("Eclipse Passage product feature", feature1.getDescription());

		EList<FeatureVersion> feature1Versions = feature1.getFeatureVersions();
		assertEquals(1, feature1Versions.size());
		FeatureVersion feature1Version0 = feature1Versions.get(0);
		assertEquals("0.4.0", feature1Version0.getVersion());
	}
}
