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
package org.eclipse.passage.lic.emf.edit;

import org.eclipse.passage.lic.runtime.features.FeatureDescriptor;
import org.eclipse.passage.lic.runtime.features.FeatureRegistry;
import org.eclipse.passage.lic.runtime.features.FeatureSetDescriptor;
import org.eclipse.passage.lic.runtime.features.FeatureVersionDescriptor;

public interface FeatureDomainRegistry extends FeatureRegistry, EditingDomainRegistry {

	void registerFeatureSet(FeatureSetDescriptor featureSet);

	void unregisterFeatureSet(String featureSetId);
	
	void registerFeature(FeatureDescriptor feature);

	void unregisterFeature(String featureId);
	
	void registerFeatureVersion(FeatureDescriptor feature, FeatureVersionDescriptor featureVersion);

	void unregisterFeatureVersion(String featureId, String version);

}
