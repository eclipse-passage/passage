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
package org.eclipse.passage.loc.internal.licenses.core;

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
import org.eclipse.passage.lic.internal.equinox.events.EquinoxEvent;
import org.eclipse.passage.lic.internal.licenses.model.util.LicensesResourceImpl;
import org.eclipse.passage.lic.licenses.model.api.LicensePlan;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.loc.internal.api.OperatorGearSupplier;
import org.eclipse.passage.loc.internal.api.workspace.KnownResources;
import org.eclipse.passage.loc.internal.api.workspace.Licenses;
import org.eclipse.passage.loc.internal.api.workspace.OperatorWorkspace;
import org.eclipse.passage.loc.internal.api.workspace.ResourceHandle;
import org.eclipse.passage.loc.internal.emf.DomainContentAdapter;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistry;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.internal.equinox.BaseDomainRegistry;
import org.eclipse.passage.loc.internal.licenses.LicenseRegistry;
import org.eclipse.passage.loc.internal.licenses.LicenseRegistryEvents;
import org.eclipse.passage.loc.internal.licenses.core.i18n.LicensesCoreMessages;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.event.EventAdmin;

@SuppressWarnings("restriction")
@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + LicensesPackage.eNAME,
		EditingDomainRegistryAccess.PROPERTY_FILE_EXTENSION + '=' + "licenses_xmi" })
public final class LicenseDomainRegistry extends BaseDomainRegistry<LicensePlan>
		implements LicenseRegistry, EditingDomainRegistry<LicensePlan> {

	private final Map<String, LicensePlan> plans = new HashMap<>();

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
		plans.clear();
		super.deactivate(properties);
	}

	@Override
	public String getFileExtension() {
		return "licenses_xmi"; //$NON-NLS-1$
	}

	@Override
	public Class<LicensePlan> getContentClass() {
		return LicensePlan.class;
	}

	@Override
	public String resolveIdentifier(LicensePlan content) {
		return content.getIdentifier();
	}

	@Override
	public Collection<LicensePlan> plans() {
		return new ArrayList<>(plans.values());
	}

	@Override
	public Optional<LicensePlan> plan(String identifier) {
		return Optional.ofNullable(plans.get(identifier));
	}

	void registerLicensePlan(LicensePlan licensePlan) {
		String identifier = licensePlan.getIdentifier();
		LicensePlan existing = plans.put(identifier, licensePlan);
		if ((existing != null) && (existing != licensePlan)) {
			String msg = NLS.bind(LicensesCoreMessages.LicenseDomain_instance_duplication_message, existing,
					licensePlan);
			Platform.getLog(getClass()).warn(msg);
		}
		events().postEvent(new EquinoxEvent(LicenseRegistryEvents.LICENSE_PLAN_CREATE, licensePlan).get());
	}

	void unregisterLicensePlan(String identifier) {
		LicensePlan removed = plans.remove(identifier);
		if (removed != null) {
			events().postEvent(new EquinoxEvent(LicenseRegistryEvents.LICENSE_PLAN_DELETE, removed).get());
		}
	}

	private EventAdmin events() {
		return events.stream().findAny().get();
	}

	@Override
	protected DomainContentAdapter<LicensePlan, LicenseDomainRegistry> createContentAdapter() {
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
	public void registerContent(LicensePlan content) {
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
