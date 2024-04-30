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

import java.util.Collection;
import java.util.Optional;

import org.eclipse.passage.lic.features.model.api.Feature;
import org.eclipse.passage.lic.features.model.api.FeatureSet;
import org.eclipse.passage.lic.features.model.api.FeatureVersion;

public interface FeatureRegistry {

	Collection<FeatureSet> featureSets();

	Optional<FeatureSet> featureSet(String id);

	Collection<Feature> features();

	Optional<Feature> feature(String id);

	Collection<FeatureVersion> featureVersions();

}
