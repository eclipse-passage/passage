/*******************************************************************************
 * Copyright (c) 2018, 2022 ArSysOp
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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.features.FeatureDescriptor;
import org.eclipse.passage.lic.features.FeatureSetDescriptor;
import org.eclipse.passage.lic.features.FeatureVersionDescriptor;
import org.eclipse.passage.lic.features.model.meta.FeaturesPackage;
import org.eclipse.passage.lic.features.model.util.FeaturesResourceImpl;
import org.eclipse.passage.lic.internal.equinox.events.EquinoxEvent;
import org.eclipse.passage.loc.internal.api.OperatorGearSupplier;
import org.eclipse.passage.loc.internal.api.workspace.Features;
import org.eclipse.passage.loc.internal.api.workspace.KnownResources;
import org.eclipse.passage.loc.internal.api.workspace.OperatorWorkspace;
import org.eclipse.passage.loc.internal.api.workspace.ResourceHandle;
import org.eclipse.passage.loc.internal.emf.BaseDomainRegistry;
import org.eclipse.passage.loc.internal.emf.DomainContentAdapter;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistry;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.internal.features.FeatureRegistry;
import org.eclipse.passage.loc.internal.features.FeatureRegistryEvents;
import org.eclipse.passage.loc.internal.features.core.i18n.FeaturesCoreMessages;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.EventAdmin;

@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + FeaturesPackage.eNAME,
		EditingDomainRegistryAccess.PROPERTY_FILE_EXTENSION + '=' + "features_xmi" })
public class FeatureDomainRegistry extends BaseDomainRegistry<FeatureSetDescriptor>
		implements FeatureRegistry, EditingDomainRegistry<FeatureSetDescriptor> {

	private final Map<String, FeatureSetDescriptor> featureSetIndex = new HashMap<>();
	private final Map<String, FeatureDescriptor> featureIndex = new HashMap<>();
	private final Map<String, Map<String, FeatureVersionDescriptor>> featureVersionIndex = new HashMap<>();

	private EventAdmin events;

	@Reference
	public void bindEventAdmin(EventAdmin admin) {
		this.events = admin;
	}

	public void unbindEventAdmin(@SuppressWarnings("unused") EventAdmin admin) {
		this.events = null;
	}

	@Override
	@Reference
	public void bindGear(OperatorGearSupplier supplier) {
		super.bindGear(supplier);
	}

	@Override
	public void unbindGear(OperatorGearSupplier supplier) {
		super.unbindGear(supplier);
	}

	@Override
	@Activate
	public void activate(Map<String, Object> properties) {
		super.activate(properties);
	}

	@Deactivate
	@Override
	public void deactivate(Map<String, Object> properties) {
		Collection<Map<String, FeatureVersionDescriptor>> values = featureVersionIndex.values();
		for (Map<String, FeatureVersionDescriptor> map : values) {
			map.clear();
		}
		featureVersionIndex.clear();
		featureIndex.clear();
		featureSetIndex.clear();
		super.deactivate(properties);
	}

	@Override
	public String getFileExtension() {
		return "features_xmi"; //$NON-NLS-1$
	}

	@Override
	public Class<FeatureSetDescriptor> getContentClass() {
		return FeatureSetDescriptor.class;
	}

	@Override
	public String resolveIdentifier(FeatureSetDescriptor content) {
		return content.getIdentifier();
	}

	@Override
	public Iterable<? extends FeatureSetDescriptor> getFeatureSets() {
		return new ArrayList<>(featureSetIndex.values());
	}

	@Override
	public FeatureSetDescriptor getFeatureSet(String identifier) {
		return featureSetIndex.get(identifier);
	}

	@Override
	public Iterable<? extends FeatureDescriptor> getFeatures() {
		return new ArrayList<>(featureIndex.values());
	}

	@Override
	public Iterable<? extends FeatureDescriptor> getFeatures(String featureSetId) {
		FeatureSetDescriptor featureSet = featureSetIndex.get(featureSetId);
		if (featureSet == null) {
			return Collections.emptyList();
		}
		List<FeatureDescriptor> features = new ArrayList<>();
		featureSet.getFeatures().forEach(features::add);
		return features;
	}

	@Override
	public FeatureDescriptor getFeature(String identifier) {
		return featureIndex.get(identifier);
	}

	@Override
	public Iterable<? extends FeatureVersionDescriptor> getFeatureVersions() {
		List<FeatureVersionDescriptor> list = new ArrayList<>();
		Collection<Map<String, FeatureVersionDescriptor>> values = featureVersionIndex.values();
		for (Map<String, FeatureVersionDescriptor> map : values) {
			list.addAll(map.values());
		}
		return list;
	}

	@Override
	public Iterable<? extends FeatureVersionDescriptor> getFeatureVersions(String featureId) {
		Map<String, FeatureVersionDescriptor> map = featureVersionIndex.get(featureId);
		if (map == null) {
			return Collections.emptyList();
		}
		return new ArrayList<>(map.values());
	}

	@Override
	public FeatureVersionDescriptor getFeatureVersion(String featureId, String version) {
		Map<String, FeatureVersionDescriptor> map = featureVersionIndex.get(featureId);
		if (map == null) {
			return null;
		}
		return map.get(version);
	}

	@Override
	protected DomainContentAdapter<FeatureSetDescriptor, FeatureDomainRegistry> createContentAdapter() {
		return new FeaturesDomainRegistryTracker(this);
	}

	@Override
	public void registerFeatureSet(FeatureSetDescriptor featureSet) {
		String identifier = featureSet.getIdentifier();
		FeatureSetDescriptor existing = featureSetIndex.put(identifier, featureSet);
		if (existing != null) {
			String msg = NLS.bind(FeaturesCoreMessages.FeatureDomain_instance_duplication_message, existing,
					featureSet);
			Platform.getLog(getClass()).warn(msg);
		}
		events.postEvent(new EquinoxEvent(FeatureRegistryEvents.FEATURE_SET_CREATE, featureSet).get());
		Iterable<? extends FeatureDescriptor> features = featureSet.getFeatures();
		for (FeatureDescriptor feature : features) {
			registerFeature(feature);
		}
	}

	@Override
	public void registerFeature(FeatureDescriptor feature) {
		String identifier = feature.getIdentifier();
		FeatureDescriptor existing = featureIndex.put(identifier, feature);
		if (existing != null) {
			String msg = NLS.bind(FeaturesCoreMessages.FeatureDomain_instance_duplication_message, existing, feature);
			Platform.getLog(getClass()).warn(msg);
		}
		events.postEvent(new EquinoxEvent(FeatureRegistryEvents.FEATURE_CREATE, feature).get());
		Iterable<? extends FeatureVersionDescriptor> featureVersions = feature.getFeatureVersions();
		for (FeatureVersionDescriptor featureVersion : featureVersions) {
			registerFeatureVersion(feature, featureVersion);
		}
	}

	@Override
	public void registerFeatureVersion(FeatureDescriptor feature, FeatureVersionDescriptor featureVersion) {
		String identifier = feature.getIdentifier();
		Map<String, FeatureVersionDescriptor> map = featureVersionIndex.computeIfAbsent(identifier,
				key -> new HashMap<>());
		String version = featureVersion.getVersion();
		FeatureVersionDescriptor existing = map.put(version, featureVersion);
		if (existing != null) {
			String msg = NLS.bind(FeaturesCoreMessages.FeatureDomain_instance_duplication_message, existing,
					featureVersion);
			Platform.getLog(getClass()).warn(msg);
		}
		events.postEvent(new EquinoxEvent(FeatureRegistryEvents.FEATURE_VERSION_CREATE, featureVersion).get());
	}

	@Override
	public void unregisterFeatureSet(String featureSetId) {
		FeatureSetDescriptor removed = featureSetIndex.remove(featureSetId);
		if (removed != null) {
			events.postEvent(new EquinoxEvent(FeatureRegistryEvents.FEATURE_SET_DELETE, removed).get());
			Iterable<? extends FeatureDescriptor> features = removed.getFeatures();
			for (FeatureDescriptor feature : features) {
				unregisterFeature(feature.getIdentifier());
			}
		}
	}

	@Override
	public void unregisterFeature(String featureId) {
		FeatureDescriptor removed = featureIndex.remove(featureId);
		if (removed != null) {
			events.postEvent(new EquinoxEvent(FeatureRegistryEvents.FEATURE_DELETE, removed).get());
			Iterable<? extends FeatureVersionDescriptor> featureVersions = removed.getFeatureVersions();
			for (FeatureVersionDescriptor featureVersion : featureVersions) {
				unregisterFeatureVersion(featureId, featureVersion.getVersion());
			}
		}
	}

	@Override
	public void unregisterFeatureVersion(String featureId, String version) {
		Map<String, FeatureVersionDescriptor> map = featureVersionIndex.get(featureId);
		if (map != null) {
			FeatureVersionDescriptor removed = map.remove(version);
			if (removed != null) {
				events.postEvent(new EquinoxEvent(FeatureRegistryEvents.FEATURE_VERSION_DELETE, removed).get());
			}
			if (map.isEmpty()) {
				featureVersionIndex.remove(version);
			}
		}
	}

	@Override
	public EClass getContentClassifier() {
		return FeaturesPackage.eINSTANCE.getFeatureSet();
	}

	@Override
	public EStructuralFeature getContentIdentifierAttribute() {
		return FeaturesPackage.eINSTANCE.getFeatureSet_Identifier();
	}

	@Override
	public EStructuralFeature getContentNameAttribute() {
		return FeaturesPackage.eINSTANCE.getFeatureSet_Name();
	}

	@Override
	public void registerContent(FeatureSetDescriptor content) {
		registerFeatureSet(content);
	}

	@Override
	public void unregisterContent(String identifier) {
		unregisterFeatureSet(identifier);
	}

	@Override
	protected final Resource createResource(URI uri) {
		return new FeaturesResourceImpl(uri);
	}

	@Override
	protected boolean emfResource(ResourceHandle handle) {
		return Features.xmi.equals(handle.type()) || Features.xmi033.equals(handle.type());
	}

	@Override
	protected KnownResources knownResources(OperatorWorkspace workspace) {
		return workspace.features();
	}

}
