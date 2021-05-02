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
package org.eclipse.passage.lic.features.model.migration.tests;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.passage.lic.features.model.api.Feature;
import org.eclipse.passage.lic.features.model.api.FeatureSet;
import org.eclipse.passage.lic.features.model.api.FeatureVersion;
import org.eclipse.passage.lic.features.model.util.FeaturesResourceImpl;
import org.junit.Test;

public final class FeaturesMigratorTest {

	@Test
	public void testMigratorPositive() throws Exception {
		File legacy = new File(
				System.getProperty("user.dir") + File.separator + "model/org.eclipse.passage.lic.lic_features"); //$NON-NLS-1$//$NON-NLS-2$
		URI uri = URI.createFileURI(legacy.getPath());
		// FIXME:AF: should be done via factory
		Resource resource = new FeaturesResourceImpl(uri);
		resource.load(null);
		EList<EObject> contents = resource.getContents();
		EObject eObject = contents.get(0);

		FeatureSet featureSet = FeatureSet.class.cast(eObject);
		assertEquals("org.eclipse.passage.lic", featureSet.getIdentifier()); //$NON-NLS-1$
		assertEquals("Eclipse Passage LIC", featureSet.getName()); //$NON-NLS-1$
		assertEquals("Eclipse Passage Licensing Integration Components", featureSet.getDescription()); //$NON-NLS-1$

		EList<Feature> features = featureSet.getFeatures();
		assertEquals(2, features.size());

		Feature f0 = features.get(0);
		assertEquals("org.eclipse.passage.lic.launch", f0.getIdentifier()); //$NON-NLS-1$
		assertEquals("Eclipse Passage Launch", f0.getName()); //$NON-NLS-1$
		assertEquals("Eclipse Passage startup feature", f0.getDescription()); //$NON-NLS-1$

		EList<FeatureVersion> f0vs = f0.getFeatureVersions();
		assertEquals(1, f0vs.size());
		FeatureVersion f0v0 = f0vs.get(0);
		assertEquals("0.3.3", f0v0.getVersion()); //$NON-NLS-1$

		Feature f1 = features.get(1);
		assertEquals("org.eclipse.passage.lic.product", f1.getIdentifier()); //$NON-NLS-1$
		assertEquals("Eclipse Passage Product", f1.getName()); //$NON-NLS-1$
		assertEquals("Eclipse Passage product feature", f1.getDescription()); //$NON-NLS-1$

		EList<FeatureVersion> f1vs = f1.getFeatureVersions();
		assertEquals(1, f1vs.size());
		FeatureVersion f1v1 = f1vs.get(0);
		assertEquals("0.4.0", f1v1.getVersion()); //$NON-NLS-1$
	}
}
