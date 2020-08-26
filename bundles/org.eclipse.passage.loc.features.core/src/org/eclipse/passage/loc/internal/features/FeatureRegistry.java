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
package org.eclipse.passage.loc.internal.features;

import org.eclipse.passage.lic.features.FeatureDescriptor;
import org.eclipse.passage.lic.features.FeatureSetDescriptor;
import org.eclipse.passage.lic.features.FeatureVersionDescriptor;

public interface FeatureRegistry {

	Iterable<? extends FeatureSetDescriptor> getFeatureSets();

	FeatureSetDescriptor getFeatureSet(String featureSetId);

	void registerFeatureSet(FeatureSetDescriptor featureSet);

	void unregisterFeatureSet(String featureSetId);

	Iterable<? extends FeatureDescriptor> getFeatures();

	Iterable<? extends FeatureDescriptor> getFeatures(String featureSetId);

	FeatureDescriptor getFeature(String featureId);

	void registerFeature(FeatureDescriptor feature);

	void unregisterFeature(String featureId);

	Iterable<? extends FeatureVersionDescriptor> getFeatureVersions();

	Iterable<? extends FeatureVersionDescriptor> getFeatureVersions(String featureId);

	FeatureVersionDescriptor getFeatureVersion(String featureId, String version);

	void registerFeatureVersion(FeatureDescriptor feature, FeatureVersionDescriptor featureVersion);

	void unregisterFeatureVersion(String featureId, String version);

}
