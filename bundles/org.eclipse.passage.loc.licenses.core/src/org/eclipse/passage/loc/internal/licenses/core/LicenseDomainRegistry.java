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
import org.eclipse.passage.lic.emf.ecore.DomainContentAdapter;
import org.eclipse.passage.lic.emf.ecore.EditingDomainRegistry;
import org.eclipse.passage.lic.emf.edit.BaseDomainRegistry;
import org.eclipse.passage.lic.emf.edit.ComposedAdapterFactoryProvider;
import org.eclipse.passage.lic.emf.edit.EditingDomainRegistryAccess;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.lic.licenses.registry.LicenseRegistry;
import org.eclipse.passage.lic.licenses.registry.LicenseRegistryEvents;
import org.eclipse.passage.lic.licenses.registry.Licenses;
import org.eclipse.passage.loc.runtime.OperatorEvents;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.EventAdmin;

@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + Licenses.DOMAIN_NAME,
		EditingDomainRegistryAccess.PROPERTY_FILE_EXTENSION + '=' + Licenses.FILE_EXTENSION_XMI })
public class LicenseDomainRegistry extends BaseDomainRegistry<LicensePackDescriptor>
		implements LicenseRegistry, EditingDomainRegistry<LicensePackDescriptor> {

	private final Map<String, LicensePackDescriptor> licensePackIndex = new HashMap<>();
	private final Map<String, List<LicensePackDescriptor>> userPackIndex = new HashMap<>();
	private final Map<String, Map<String, List<LicensePackDescriptor>>> productVersionPackIndex = new HashMap<>();

	@Reference
	@Override
	public void bindEventAdmin(EventAdmin admin) {
		super.bindEventAdmin(admin);
	}

	@Override
	public void unbindEventAdmin(EventAdmin admin) {
		super.unbindEventAdmin(admin);
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

	@Override
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
	public Class<LicensePackDescriptor> getContentClass() {
		return LicensePackDescriptor.class;
	}

	@Override
	public String resolveIdentifier(LicensePackDescriptor content) {
		return content.getIdentifier();
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
	protected DomainContentAdapter<LicensePackDescriptor, LicenseDomainRegistry> createContentAdapter() {
		return new LicensesDomainRegistryTracker(this);
	}

	@Override
	public void registerLicensePack(LicensePackDescriptor licensePack) {
		String identifier = licensePack.getIdentifier();
		LicensePackDescriptor existing = licensePackIndex.put(identifier, licensePack);
		if (existing != null) {
			// FIXME: warning
		}
		eventAdmin.postEvent(OperatorEvents.create(LicenseRegistryEvents.LICENSE_PACK_CREATE, licensePack));
		String userIdentifier = licensePack.getUserIdentifier();
		List<LicensePackDescriptor> userPackList = userPackIndex.computeIfAbsent(userIdentifier,
				key -> new ArrayList<>());
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
			eventAdmin.postEvent(OperatorEvents.create(LicenseRegistryEvents.LICENSE_PACK_DELETE, removed));
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
		return LicensesPackage.eINSTANCE.getLicensePack();
	}

	@Override
	public EStructuralFeature getContentIdentifierAttribute() {
		return LicensesPackage.eINSTANCE.getLicensePack_Identifier();
	}

	@Override
	public EStructuralFeature getContentNameAttribute() {
		return null;
	}

	@Override
	public void registerContent(LicensePackDescriptor content) {
		registerLicensePack(content);
	}

	@Override
	public void unregisterContent(String identifier) {
		unregisterLicensePack(identifier);
	}

}
