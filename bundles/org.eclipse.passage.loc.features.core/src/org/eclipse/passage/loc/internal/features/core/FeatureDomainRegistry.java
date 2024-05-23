/*******************************************************************************
 * Copyright (c) 2018, 2024 ArSysOp
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.features.model.api.Feature;
import org.eclipse.passage.lic.features.model.api.FeatureSet;
import org.eclipse.passage.lic.features.model.api.FeatureVersion;
import org.eclipse.passage.lic.features.model.meta.FeaturesPackage;
import org.eclipse.passage.lic.features.model.util.FeaturesResourceImpl;
import org.eclipse.passage.lic.internal.equinox.events.EquinoxEvent;
import org.eclipse.passage.loc.internal.api.OperatorGearSupplier;
import org.eclipse.passage.loc.internal.api.workspace.Features;
import org.eclipse.passage.loc.internal.api.workspace.KnownResources;
import org.eclipse.passage.loc.internal.api.workspace.OperatorWorkspace;
import org.eclipse.passage.loc.internal.api.workspace.ResourceHandle;
import org.eclipse.passage.loc.internal.emf.DomainContentAdapter;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistry;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.internal.equinox.BaseDomainRegistry;
import org.eclipse.passage.loc.internal.features.FeatureRegistry;
import org.eclipse.passage.loc.internal.features.FeatureRegistryEvents;
import org.eclipse.passage.loc.internal.features.core.i18n.FeaturesCoreMessages;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.event.EventAdmin;

@SuppressWarnings("restriction")
@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + FeaturesPackage.eNAME,
		EditingDomainRegistryAccess.PROPERTY_FILE_EXTENSION + '=' + "features_xmi" })
public final class FeatureDomainRegistry extends BaseDomainRegistry<FeatureSet>
		implements FeatureRegistry, EditingDomainRegistry<FeatureSet> {

	private final Map<String, FeatureSet> sets = new HashMap<>();
	private final Map<String, Feature> features = new HashMap<>();
	private final Map<String, Map<String, FeatureVersion>> versions = new HashMap<>();

	private final List<EventAdmin> events = new ArrayList<>();

	@Reference(cardinality = ReferenceCardinality.MANDATORY)
	public void bindEventAdmin(EventAdmin admin) {
		this.events.add(admin);
	}

	public void unbindEventAdmin(EventAdmin admin) {
		this.events.remove(admin);
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

	@Activate
	public void load(Map<String, Object> properties) {
		super.activate(properties);
	}

	@Deactivate
	public void unload(Map<String, Object> properties) {
		versions.values().forEach(Map::clear);
		versions.clear();
		features.clear();
		sets.clear();
		super.deactivate(properties);
	}

	@Override
	public String getFileExtension() {
		return "features_xmi"; //$NON-NLS-1$
	}

	@Override
	public Class<FeatureSet> getContentClass() {
		return FeatureSet.class;
	}

	@Override
	public String resolveIdentifier(FeatureSet content) {
		return content.getIdentifier();
	}

	@Override
	public Collection<FeatureSet> featureSets() {
		return new ArrayList<>(sets.values());
	}

	@Override
	public Optional<FeatureSet> featureSet(String identifier) {
		return Optional.ofNullable(sets.get(identifier));
	}

	@Override
	public Collection<Feature> features() {
		return new ArrayList<>(features.values());
	}

	@Override
	public Optional<Feature> feature(String id) {
		return Optional.ofNullable(features.get(id));
	}

	@Override
	public Collection<FeatureVersion> featureVersions() {
		List<FeatureVersion> list = new ArrayList<>();
		Collection<Map<String, FeatureVersion>> values = versions.values();
		for (Map<String, FeatureVersion> map : values) {
			list.addAll(map.values());
		}
		return list;
	}

	@Override
	protected DomainContentAdapter<FeatureSet, FeatureDomainRegistry> createContentAdapter() {
		return new FeaturesDomainRegistryTracker(this);
	}

	void registerFeatureSet(FeatureSet fs) {
		FeatureSet existing = sets.put(fs.getIdentifier(), fs);
		if ((existing != null) && (existing != fs)) {
			Platform.getLog(getClass())
					.warn(NLS.bind(FeaturesCoreMessages.FeatureDomain_instance_duplication_message, existing, fs));
		}
		events().postEvent(new EquinoxEvent(FeatureRegistryEvents.FEATURE_SET_CREATE, fs).get());
		fs.getFeatures().forEach(this::registerFeature);
	}

	void registerFeature(Feature feature) {
		Feature existing = features.put(feature.getIdentifier(), feature);
		if ((existing != null) && (existing != feature)) {
			Platform.getLog(getClass())
					.warn(NLS.bind(FeaturesCoreMessages.FeatureDomain_instance_duplication_message, existing, feature));
		}
		events().postEvent(new EquinoxEvent(FeatureRegistryEvents.FEATURE_CREATE, feature).get());
		feature.getFeatureVersions().forEach(fv -> registerFeatureVersion(feature, fv));
	}

	void registerFeatureVersion(Feature feature, FeatureVersion version) {
		String identifier = feature.getIdentifier();
		Map<String, FeatureVersion> map = versions.computeIfAbsent(identifier, key -> new HashMap<>());
		FeatureVersion existing = map.put(version.getVersion(), version);
		if ((existing != null) && (existing != version)) {
			Platform.getLog(getClass())
					.warn(NLS.bind(FeaturesCoreMessages.FeatureDomain_instance_duplication_message, existing, version));
		}
		events().postEvent(new EquinoxEvent(FeatureRegistryEvents.FEATURE_VERSION_CREATE, version).get());
	}

	void unregisterFeatureSet(String id) {
		FeatureSet removed = sets.remove(id);
		if (removed != null) {
			events().postEvent(new EquinoxEvent(FeatureRegistryEvents.FEATURE_SET_DELETE, removed).get());
			removed.getFeatures().stream().map(Feature::getIdentifier).forEach(this::unregisterFeature);
		}
	}

	void unregisterFeature(String id) {
		Feature removed = features.remove(id);
		if (removed != null) {
			events().postEvent(new EquinoxEvent(FeatureRegistryEvents.FEATURE_DELETE, removed).get());
			removed.getFeatureVersions().forEach(fv -> unregisterFeatureVersion(id, fv.getVersion()));
		}
	}

	void unregisterFeatureVersion(String featureId, String version) {
		Map<String, FeatureVersion> map = versions.get(featureId);
		if (map != null) {
			FeatureVersion removed = map.remove(version);
			if (removed != null) {
				events().postEvent(new EquinoxEvent(FeatureRegistryEvents.FEATURE_VERSION_DELETE, removed).get());
			}
			if (map.isEmpty()) {
				versions.remove(version);
			}
		}
	}

	private EventAdmin events() {
		return events.stream().findAny().get();
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
	public void registerContent(FeatureSet content) {
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
