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

		Feature f0 = features.get(0);
		assertEquals("org.eclipse.passage.lic.launch", f0.getIdentifier());
		assertEquals("Eclipse Passage Launch", f0.getName());
		assertEquals("Eclipse Passage startup feature", f0.getDescription());
		
		EList<FeatureVersion> f0vs = f0.getFeatureVersions();
		assertEquals(1, f0vs.size());
		FeatureVersion f0v0 = f0vs.get(0);
		assertEquals("0.3.3", f0v0.getVersion());

		Feature f1 = features.get(1);
		assertEquals("org.eclipse.passage.lic.product", f1.getIdentifier());
		assertEquals("Eclipse Passage Product", f1.getName());
		assertEquals("Eclipse Passage product feature", f1.getDescription());

		EList<FeatureVersion> f1vs = f1.getFeatureVersions();
		assertEquals(1, f1vs.size());
		FeatureVersion f1v1 = f1vs.get(0);
		assertEquals("0.4.0", f1v1.getVersion());
	}
}
