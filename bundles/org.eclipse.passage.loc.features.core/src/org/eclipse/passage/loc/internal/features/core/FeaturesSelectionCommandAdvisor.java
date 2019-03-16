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
package org.eclipse.passage.loc.internal.features.core;

import java.util.Collections;

import org.eclipse.passage.lic.emf.edit.EditingDomainRegistryAccess;
import org.eclipse.passage.lic.emf.edit.SelectionCommandAdvisor;
import org.eclipse.passage.lic.features.registry.Features;
import org.eclipse.passage.lic.features.registry.FeatureRegistry;
import org.eclipse.passage.lic.model.meta.LicPackage;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + Features.DOMAIN_NAME })
public class FeaturesSelectionCommandAdvisor implements SelectionCommandAdvisor {
	
	private FeatureRegistry registry;
	
	@Reference
	public void bindDomainRegistry(FeatureRegistry registry) {
		this.registry = registry;
	}

	public void unbindDomainRegistry(FeatureRegistry registry) {
		this.registry = null;
	}

	@Override
	public String getSelectionTitle(String classifier) {
		if (LicPackage.eINSTANCE.getFeatureSet().getName().equals(classifier)) {
			return "Select Feature Set";
		}
		if (LicPackage.eINSTANCE.getFeature().getName().equals(classifier)) {
			return "Select Feature";
		}
		if (LicPackage.eINSTANCE.getFeatureVersion().getName().equals(classifier)) {
			return "Select Feature Version";
		}
		return null;
	}

	@Override
	public Iterable<?> getSelectionInput(String classifier) {
		if (registry == null) {
			return Collections.emptyList();
		}
		if (LicPackage.eINSTANCE.getFeatureSet().getName().equals(classifier)) {
			return registry.getFeatureSets();
		}
		if (LicPackage.eINSTANCE.getFeature().getName().equals(classifier)) {
			return registry.getFeatures();
		}
		if (LicPackage.eINSTANCE.getFeatureVersion().getName().equals(classifier)) {
			return registry.getFeatureVersions();
		}
		return Collections.emptyList();
	}

}
