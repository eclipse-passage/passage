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
package org.eclipse.passage.loc.internal.features.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.passage.lic.features.model.meta.FeaturesPackage;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.internal.emf.SelectionCommandAdvisor;
import org.eclipse.passage.loc.internal.features.FeatureRegistry;
import org.eclipse.passage.loc.internal.features.core.i18n.FeaturesCoreMessages;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + FeaturesPackage.eNAME })
public class FeaturesSelectionCommandAdvisor implements SelectionCommandAdvisor {

	private final List<FeatureRegistry> features = new ArrayList<>();

	@Reference(cardinality = ReferenceCardinality.MANDATORY)
	public void bindFeatures(FeatureRegistry registry) {
		this.features.add(registry);
	}

	public void unbindFeatures(FeatureRegistry registry) {
		this.features.remove(registry);
	}

	@Override
	public String getSelectionTitle(String classifier) {
		if (FeaturesPackage.eINSTANCE.getFeatureSet().getName().equals(classifier)) {
			return FeaturesCoreMessages.FeaturesSelectionCommandAdvisor_select_feature_set;
		}
		if (FeaturesPackage.eINSTANCE.getFeature().getName().equals(classifier)) {
			return FeaturesCoreMessages.FeaturesSelectionCommandAdvisor_select_feature;
		}
		if (FeaturesPackage.eINSTANCE.getFeatureVersion().getName().equals(classifier)) {
			return FeaturesCoreMessages.FeaturesSelectionCommandAdvisor_select_feature_version;
		}
		return null;
	}

	@Override
	public Iterable<?> getSelectionInput(String classifier) {
		if (FeaturesPackage.eINSTANCE.getFeatureSet().getName().equals(classifier)) {
			return features().featureSets();
		}
		if (FeaturesPackage.eINSTANCE.getFeature().getName().equals(classifier)) {
			return features().features();
		}
		if (FeaturesPackage.eINSTANCE.getFeatureVersion().getName().equals(classifier)) {
			return features().featureVersions();
		}
		return Collections.emptyList();
	}

	private FeatureRegistry features() {
		return features.stream().findAny().get();
	}

}
