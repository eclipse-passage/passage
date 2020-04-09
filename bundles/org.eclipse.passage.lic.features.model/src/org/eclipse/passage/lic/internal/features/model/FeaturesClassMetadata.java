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
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.internal.features.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.passage.lic.emf.meta.ClassMetadata;
import org.eclipse.passage.lic.emf.meta.EntityMetadata;
import org.eclipse.passage.lic.emf.meta.PlainEntityMetadata;
import org.eclipse.passage.lic.features.FeatureDescriptor;
import org.eclipse.passage.lic.features.FeatureSetDescriptor;
import org.eclipse.passage.lic.features.model.api.Feature;
import org.eclipse.passage.lic.features.model.api.FeatureSet;
import org.eclipse.passage.lic.features.model.meta.FeaturesPackage;

public final class FeaturesClassMetadata implements ClassMetadata {

	private final FeaturesPackage meta;
	// FIXME: we need to employ Cached here
	private final Map<Class<?>, EntityMetadata> map;

	public FeaturesClassMetadata() {
		meta = FeaturesPackage.eINSTANCE;
		map = new HashMap<Class<?>, EntityMetadata>();
		map.put(FeatureSetDescriptor.class, //
				new PlainEntityMetadata(//
						meta.getFeatureSet(), //
						meta.getFeatureSet_Identifier(), //
						meta.getFeatureSet_Name()));
		map.put(FeatureSet.class, map.get(FeatureSetDescriptor.class));
		map.put(FeatureDescriptor.class, //
				new PlainEntityMetadata(//
						meta.getFeature(), //
						meta.getFeature_Identifier(), //
						meta.getFeature_Name()));
		map.put(Feature.class, map.get(FeatureDescriptor.class));
	}

	@Override
	public Optional<EntityMetadata> find(Class<?> clazz) {
		return Optional.ofNullable(map.get(clazz));
	}

}
