/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Nikifor Fedorov <zelenyhleb@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.features.model.tests;

import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lic.features.FeatureDescriptor;
import org.eclipse.passage.lic.features.FeatureSetDescriptor;
import org.eclipse.passage.lic.features.model.api.Feature;
import org.eclipse.passage.lic.features.model.api.FeatureSet;
import org.eclipse.passage.lic.internal.features.model.FeaturesClassMetadata;
import org.junit.Test;

public class FeaturesClassMetadataTest {

	private final FeaturesClassMetadata metadata;

	public FeaturesClassMetadataTest() {
		metadata = new FeaturesClassMetadata();
	}

	@Test
	public void featureSetMetadata() {
		assertTrue(metadata.find(FeatureSetDescriptor.class).isPresent());
		assertTrue(metadata.find(FeatureSet.class).isPresent());
	}

	@Test
	public void featureMetadata() {
		assertTrue(metadata.find(FeatureDescriptor.class).isPresent());
		assertTrue(metadata.find(Feature.class).isPresent());
	}

	@Test
	public void featureVersionMetadata() {
		// FIXME: implement
//		assertTrue(metadata.find(FeatureVersionDescriptor.class).isPresent());
//		assertTrue(metadata.find(FeatureVersion.class).isPresent());
	}
}
