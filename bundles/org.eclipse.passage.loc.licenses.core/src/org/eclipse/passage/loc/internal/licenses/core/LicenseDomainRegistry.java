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
package org.eclipse.passage.loc.internal.licenses.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.internal.equinox.events.EquinoxEvent;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.lic.licenses.model.util.LicensesResourceImpl;
import org.eclipse.passage.loc.internal.api.OperatorGearSupplier;
import org.eclipse.passage.loc.internal.api.workspace.KnownResources;
import org.eclipse.passage.loc.internal.api.workspace.Licenses;
import org.eclipse.passage.loc.internal.api.workspace.OperatorWorkspace;
import org.eclipse.passage.loc.internal.api.workspace.ResourceHandle;
import org.eclipse.passage.loc.internal.emf.BaseDomainRegistry;
import org.eclipse.passage.loc.internal.emf.DomainContentAdapter;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistry;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.internal.licenses.LicenseRegistry;
import org.eclipse.passage.loc.internal.licenses.LicenseRegistryEvents;
import org.eclipse.passage.loc.internal.licenses.core.i18n.LicensesCoreMessages;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.EventAdmin;

@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + LicensesPackage.eNAME,
		EditingDomainRegistryAccess.PROPERTY_FILE_EXTENSION + '=' + "licenses_xmi" })
public class LicenseDomainRegistry extends BaseDomainRegistry<LicensePlanDescriptor>
		implements LicenseRegistry, EditingDomainRegistry<LicensePlanDescriptor> {

	private final Map<String, LicensePlanDescriptor> licensePlanIndex = new HashMap<>();

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
		licensePlanIndex.clear();
		super.deactivate(properties);
	}

	@Override
	public String getFileExtension() {
		return "licenses_xmi"; //$NON-NLS-1$
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
	public Collection<LicensePlanDescriptor> getLicensePlans() {
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
			Platform.getLog(getClass()).warn(msg);
		}
		events.postEvent(new EquinoxEvent(LicenseRegistryEvents.LICENSE_PLAN_CREATE, licensePlan).get());
	}

	public void unregisterLicensePlan(String identifier) {
		LicensePlanDescriptor removed = licensePlanIndex.remove(identifier);
		if (removed != null) {
			events.postEvent(new EquinoxEvent(LicenseRegistryEvents.LICENSE_PLAN_DELETE, removed).get());

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
		return LicensesPackage.eINSTANCE.getLicensePlan_Name();
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
	protected final Resource createResource(URI uri) {
		return new LicensesResourceImpl(uri);
	}

	@Override
	protected boolean emfResource(ResourceHandle handle) {
		return Licenses.xmi.equals(handle.type()) || Licenses.xmi033.equals(handle.type());
	}

	@Override
	protected KnownResources knownResources(OperatorWorkspace workspace) {
		return workspace.licenses();
	}

}
