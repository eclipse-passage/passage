/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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
package org.eclipse.passage.loc.internal.licenses.core;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.api.LicensingReporter;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.emf.ecore.DomainContentAdapter;
import org.eclipse.passage.lic.emf.ecore.EditingDomainRegistry;
import org.eclipse.passage.lic.emf.edit.BaseDomainRegistry;
import org.eclipse.passage.lic.emf.edit.ComposedAdapterFactoryProvider;
import org.eclipse.passage.lic.emf.edit.EditingDomainRegistryAccess;
import org.eclipse.passage.lic.equinox.io.EquinoxPaths;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.lic.licenses.registry.LicenseRegistry;
import org.eclipse.passage.lic.licenses.registry.LicenseRegistryEvents;
import org.eclipse.passage.loc.internal.licenses.core.i18n.LicensesCoreMessages;
import org.eclipse.passage.loc.licenses.core.Licenses;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + Licenses.DOMAIN_NAME,
		EditingDomainRegistryAccess.PROPERTY_FILE_EXTENSION + '=' + Licenses.FILE_EXTENSION_XMI })
public class LicenseDomainRegistry extends BaseDomainRegistry<LicensePlanDescriptor>
		implements LicenseRegistry, EditingDomainRegistry<LicensePlanDescriptor> {

	private final Map<String, LicensePlanDescriptor> licensePlanIndex = new HashMap<>();

	@Reference
	@Override
	public void bindLicensingReporter(LicensingReporter admin) {
		super.bindLicensingReporter(admin);
	}

	@Override
	public void unbindLicensingReporter(LicensingReporter admin) {
		super.unbindLicensingReporter(admin);
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
		licensePlanIndex.clear();
		super.deactivate(properties);
	}

	@Override
	public String getFileExtension() {
		return Licenses.FILE_EXTENSION_XMI;
	}

	@Override
	public Class<LicensePlanDescriptor> getContentClass() {
		return LicensePlanDescriptor.class;
	}

	@Override
	public String resolveIdentifier(LicensePlanDescriptor content) {
		return content.getIdentifier();
	}

	@Override
	public Iterable<LicensePlanDescriptor> getLicensePlans() {
		return new ArrayList<>(licensePlanIndex.values());
	}

	@Override
	public LicensePlanDescriptor getLicensePlan(String identifier) {
		return licensePlanIndex.get(identifier);
	}

	public void registerLicensePlan(LicensePlanDescriptor licensePlan) {
		String identifier = licensePlan.getIdentifier();
		LicensePlanDescriptor existing = licensePlanIndex.put(identifier, licensePlan);
		if (existing != null) {
			String msg = NLS.bind(LicensesCoreMessages.LicenseDomain_instance_duplication_message, existing,
					licensePlan);
			licensingReporter.logResult(LicensingResults.createWarning(msg, this.getClass(), null));
		}
		licensingReporter
				.postResult(LicensingResults.createEvent(LicenseRegistryEvents.LICENSE_PLAN_CREATE, licensePlan));
	}

	public void unregisterLicensePlan(String identifier) {
		LicensePlanDescriptor removed = licensePlanIndex.remove(identifier);
		if (removed != null) {
			licensingReporter
					.postResult(LicensingResults.createEvent(LicenseRegistryEvents.LICENSE_PLAN_DELETE, removed));

		}
	}

	@Override
	protected DomainContentAdapter<LicensePlanDescriptor, LicenseDomainRegistry> createContentAdapter() {
		return new LicensesDomainRegistryTracker(this);
	}

	@Override
	public EClass getContentClassifier() {
		return LicensesPackage.eINSTANCE.getLicensePlan();
	}

	@Override
	public EStructuralFeature getContentIdentifierAttribute() {
		return LicensesPackage.eINSTANCE.getLicensePlan_Identifier();
	}

	@Override
	public EStructuralFeature getContentNameAttribute() {
		return null;
	}

	@Override
	public void registerContent(LicensePlanDescriptor content) {
		registerLicensePlan(content);
	}

	@Override
	public void unregisterContent(String identifier) {
		unregisterLicensePlan(identifier);
	}

	@Override
	protected Path getResourceSetPath() throws Exception {
		Path passagePath = EquinoxPaths.resolveInstallBasePath();
		Files.createDirectories(passagePath);
		return passagePath.resolve(domainName);
	}

}
