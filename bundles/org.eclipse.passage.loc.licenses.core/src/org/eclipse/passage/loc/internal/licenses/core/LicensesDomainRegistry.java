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
package org.eclipse.passage.loc.internal.licenses.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.service.environment.EnvironmentInfo;
import org.eclipse.passage.lic.emf.edit.ComposedAdapterFactoryProvider;
import org.eclipse.passage.lic.emf.edit.DomainContentAdapter;
import org.eclipse.passage.lic.emf.edit.DomainRegistryAccess;
import org.eclipse.passage.lic.emf.edit.EditingDomainRegistry;
import org.eclipse.passage.lic.model.api.LicensePack;
import org.eclipse.passage.lic.model.meta.LicPackage;
import org.eclipse.passage.lic.registry.Identified;
import org.eclipse.passage.lic.registry.licenses.LicensePackDescriptor;
import org.eclipse.passage.lic.registry.licenses.Licenses;
import org.eclipse.passage.lic.registry.licenses.LicensesEvents;
import org.eclipse.passage.lic.registry.licenses.LicensesRegistry;
import org.eclipse.passage.loc.edit.EditingDomainBasedRegistry;
import org.eclipse.passage.loc.runtime.OperatorEvents;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.EventAdmin;

@Component(property = { DomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + Licenses.DOMAIN_NAME,
		DomainRegistryAccess.PROPERTY_FILE_EXTENSION + '=' + Licenses.FILE_EXTENSION_XMI })
public class LicensesDomainRegistry extends EditingDomainBasedRegistry
		implements LicensesRegistry, EditingDomainRegistry {

	private final Map<String, LicensePackDescriptor> licensePackIndex = new HashMap<>();
	private final Map<String, List<LicensePackDescriptor>> userPackIndex = new HashMap<>();
	private final Map<String, Map<String, List<LicensePackDescriptor>>> productVersionPackIndex = new HashMap<>();

	@Reference
	@Override
	public void bindEnvironmentInfo(EnvironmentInfo environmentInfo) {
		super.bindEnvironmentInfo(environmentInfo);
	}

	@Override
	public void unbindEnvironmentInfo(EnvironmentInfo environmentInfo) {
		super.unbindEnvironmentInfo(environmentInfo);
	}

	@Reference
	@Override
	public void bindEventAdmin(EventAdmin eventAdmin) {
		super.bindEventAdmin(eventAdmin);
	}

	@Override
	public void unbindEventAdmin(EventAdmin eventAdmin) {
		super.unbindEventAdmin(eventAdmin);
	}

	@Reference
	@Override
	public void bindFactoryProvider(ComposedAdapterFactoryProvider factoryProvider) {
		super.bindFactoryProvider(factoryProvider);
	}

	@Override
	public void unbindFactoryProvider(ComposedAdapterFactoryProvider factoryProvider) {
		super.unbindFactoryProvider(factoryProvider);
	}

	@Activate
	public void activate(Map<String, Object> properties) {
		super.activate(properties);
	}

	@Deactivate
	@Override
	public void deactivate(Map<String, Object> properties) {
		Collection<Map<String, List<LicensePackDescriptor>>> productPacks = productVersionPackIndex.values();
		for (Map<String, List<LicensePackDescriptor>> versionPacks : productPacks) {
			Collection<List<LicensePackDescriptor>> packs = versionPacks.values();
			for (List<LicensePackDescriptor> list : packs) {
				list.clear();
			}
			versionPacks.clear();
		}
		productPacks.clear();
		Collection<List<LicensePackDescriptor>> packs = userPackIndex.values();
		for (List<LicensePackDescriptor> list : packs) {
			list.clear();
		}
		userPackIndex.clear();
		licensePackIndex.clear();
		super.deactivate(properties);
	}

	@Override
	public String getFileExtension() {
		return Licenses.FILE_EXTENSION_XMI;
	}

	@Override
	public LicensePackDescriptor getLicensePack(String identifier) {
		return licensePackIndex.get(identifier);
	}

	@Override
	public Iterable<LicensePackDescriptor> getLicensePacks() {
		return new ArrayList<>(licensePackIndex.values());
	}

	@Override
	public Iterable<LicensePackDescriptor> getUserLicensePacks(String userId) {
		List<LicensePackDescriptor> list = userPackIndex.get(userId);
		if (list == null) {
			return Collections.emptyList();
		}
		return Collections.unmodifiableList(list);
	}

	@Override
	public Iterable<LicensePackDescriptor> getProductVersionLicensePacks(String productId, String version) {
		Map<String, List<LicensePackDescriptor>> map = productVersionPackIndex.get(productId);
		if (map == null) {
			return Collections.emptyList();
		}
		List<LicensePackDescriptor> list = map.get(version);
		if (list == null) {
			return Collections.emptyList();
		}
		return Collections.unmodifiableList(list);
	}

	@Override
	protected DomainContentAdapter<LicensesDomainRegistry> createContentAdapter() {
		return new LicensesDomainRegistryTracker(this);
	}

	@Override
	public void registerLicensePack(LicensePackDescriptor licensePack) {
		String identifier = licensePack.getIdentifier();
		LicensePackDescriptor existing = licensePackIndex.put(identifier, licensePack);
		if (existing != null) {
			// FIXME: warning
		}
		eventAdmin.postEvent(OperatorEvents.create(LicensesEvents.LICENSE_PACK_CREATE, licensePack));
		String userIdentifier = licensePack.getUserIdentifier();
		List<LicensePackDescriptor> userPackList = userPackIndex.computeIfAbsent(userIdentifier, key -> new ArrayList<>());
		userPackList.add(licensePack);
		String productIdentifier = licensePack.getProductIdentifier();
		Map<String, List<LicensePackDescriptor>> map = productVersionPackIndex.computeIfAbsent(productIdentifier,
				key -> new HashMap<>());
		String productVersion = licensePack.getProductVersion();
		List<LicensePackDescriptor> list = map.computeIfAbsent(productVersion, key -> new ArrayList<>());
		list.add(licensePack);
	}

	@Override
	public void unregisterLicensePack(String identifier) {
		LicensePackDescriptor removed = licensePackIndex.remove(identifier);
		if (removed != null) {
			eventAdmin.postEvent(OperatorEvents.create(LicensesEvents.LICENSE_PACK_DELETE, removed));
			String userIdentifier = removed.getUserIdentifier();

			List<LicensePackDescriptor> userPackList = userPackIndex.get(userIdentifier);
			if (userPackList != null) {
				userPackList.remove(removed);
				if (userPackList.isEmpty()) {
					userPackIndex.remove(userIdentifier);
				}
			}

			String productIdentifier = removed.getProductIdentifier();
			Map<String, List<LicensePackDescriptor>> map = productVersionPackIndex.get(productIdentifier);
			if (map != null) {
				String productVersion = removed.getProductVersion();
				List<LicensePackDescriptor> list = map.get(productVersion);
				if (list != null) {
					list.remove(removed);
					if (list.isEmpty()) {
						map.remove(productVersion);
					}
				}
				if (map.isEmpty()) {
					productVersionPackIndex.remove(productIdentifier);
				}
			}
		}

	}

	@Override
	public EClass getContentClassifier() {
		return LicPackage.eINSTANCE.getLicensePack();
	}

	@Override
	public EStructuralFeature getContentIdentifierAttribute() {
		return LicPackage.eINSTANCE.getLicensePack_Identifier();
	}

	@Override
	public EStructuralFeature getContentNameAttribute() {
		return null;
	}

	@Override
	public void registerContent(Identified content) {
		if (content instanceof LicensePack) {
			LicensePack licensePack = (LicensePack) content;
			registerLicensePack(licensePack);
		} else {
			//TODO: warning
		}
	}

	@Override
	public void unregisterContent(String identifier) {
		unregisterLicensePack(identifier);
	}

}
