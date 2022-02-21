/*******************************************************************************
 * Copyright (c) 2021, 2022 ArSysOp
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
package org.eclipse.passage.loc.internal.agreements.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.agreements.AgreementDescriptor;
import org.eclipse.passage.lic.agreements.AgreementGroupDescriptor;
import org.eclipse.passage.lic.agreements.model.api.AgreementGroup;
import org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage;
import org.eclipse.passage.lic.agreements.model.util.AgreementsResourceImpl;
import org.eclipse.passage.lic.internal.equinox.events.EquinoxEvent;
import org.eclipse.passage.loc.internal.agreements.AgreementRegistry;
import org.eclipse.passage.loc.internal.agreements.AgreementRegistryEvents;
import org.eclipse.passage.loc.internal.agreements.core.i18n.AgreementsCoreMessages;
import org.eclipse.passage.loc.internal.api.OperatorGearSupplier;
import org.eclipse.passage.loc.internal.api.workspace.Agreements;
import org.eclipse.passage.loc.internal.api.workspace.KnownResources;
import org.eclipse.passage.loc.internal.api.workspace.OperatorWorkspace;
import org.eclipse.passage.loc.internal.api.workspace.ResourceHandle;
import org.eclipse.passage.loc.internal.emf.BaseDomainRegistry;
import org.eclipse.passage.loc.internal.emf.DomainContentAdapter;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistry;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistryAccess;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.EventAdmin;

@SuppressWarnings("restriction")
@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + AgreementsPackage.eNAME,
		EditingDomainRegistryAccess.PROPERTY_FILE_EXTENSION + '=' + "agreements_xmi" })
public final class AgreementDomainRegistry extends BaseDomainRegistry<AgreementGroupDescriptor>
		implements AgreementRegistry, EditingDomainRegistry<AgreementGroupDescriptor> {

	private final Map<String, AgreementGroupDescriptor> groups = new HashMap<>();
	private final Map<String, AgreementDescriptor> agreements = new HashMap<>();
	private EventAdmin events;

	@Override
	@Activate
	public void activate(Map<String, Object> properties) {
		super.activate(properties);
	}

	@Deactivate
	@Override
	public void deactivate(Map<String, Object> properties) {
		agreements.clear();
		groups.clear();
		super.deactivate(properties);
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

	@Reference
	public void bindEventAdmin(EventAdmin admin) {
		this.events = admin;
	}

	public void unbindEventAdmin(@SuppressWarnings("unused") EventAdmin admin) {
		this.events = null;
	}

	@Override
	public String getFileExtension() {
		return "agreements_xmi"; //$NON-NLS-1$
	}

	@Override
	public Class<AgreementGroupDescriptor> getContentClass() {
		return AgreementGroupDescriptor.class;
	}

	@Override
	public String resolveIdentifier(AgreementGroupDescriptor content) {
		return content.getIdentifier();
	}

	@Override
	public Collection<AgreementGroupDescriptor> groups() {
		return new ArrayList<>(groups.values());
	}

	@Override
	public AgreementGroupDescriptor group(String identifier) {
		return groups.get(identifier);
	}

	@Override
	public Collection<AgreementDescriptor> agreements() {
		return new ArrayList<>(agreements.values());
	}

	@SuppressWarnings("unchecked")
	public Collection<AgreementDescriptor> agreements(String id) {
		AgreementGroupDescriptor group = groups.get(id);
		if (group == null) {
			return Collections.emptyList();
		}
		return (Collection<AgreementDescriptor>) group.getAgreements();
	}

	@Override
	public AgreementDescriptor agreement(String identifier) {
		return agreements.get(identifier);
	}

	@Override
	protected DomainContentAdapter<AgreementGroupDescriptor, AgreementDomainRegistry> createContentAdapter() {
		return new AgreementsDomainRegistryTracker(this);
	}

	public void registerAgreementGroup(AgreementGroupDescriptor group) {
		AgreementGroupDescriptor existing = groups.put(group.getIdentifier(), group);
		if (existing != null) {
			String msg = NLS.bind(AgreementsCoreMessages.AgreementDomain_instance_duplication_message, existing, group);
			Platform.getLog(getClass()).warn(msg);
		}
		brush(group);
		events.postEvent(new EquinoxEvent(AgreementRegistryEvents.AGREEMENT_GROUP_CREATE, group).get());
		group.getAgreements().forEach(u -> registerAgreement(u));
	}

	private void brush(AgreementGroupDescriptor group) {
		if (group.getDescription() == null) {
			((AgreementGroup) group).setDescription(""); //$NON-NLS-1$
		}
	}

	public void registerAgreement(AgreementDescriptor agreement) {
		String identifier = agreement.getIdentifier();
		AgreementDescriptor existing = agreements.put(identifier, agreement);
		if (existing != null) {
			String msg = NLS.bind(AgreementsCoreMessages.AgreementDomain_instance_duplication_message, existing,
					agreement);
			Platform.getLog(getClass()).warn(msg);
		}
		events.postEvent(new EquinoxEvent(AgreementRegistryEvents.AGREEMENT_CREATE, agreement).get());
	}

	public void unregisterAgreementGroup(String id) {
		AgreementGroupDescriptor removed = groups.remove(id);
		if (removed != null) {
			events.postEvent(new EquinoxEvent(AgreementRegistryEvents.AGREEMENT_GROUP_DELETE, removed).get());
			removed.getAgreements().forEach(u -> unregisterAgreement(u.getIdentifier()));
		}
	}

	public void unregisterAgreement(String id) {
		AgreementDescriptor removed = agreements.remove(id);
		if (removed != null) {
			events.postEvent(new EquinoxEvent(AgreementRegistryEvents.AGREEMENT_DELETE, removed).get());
		}
	}

	@Override
	public EClass getContentClassifier() {
		return AgreementsPackage.eINSTANCE.getAgreementGroup();
	}

	@Override
	public EStructuralFeature getContentIdentifierAttribute() {
		return AgreementsPackage.eINSTANCE.getAgreementGroup_Identifier();
	}

	@Override
	public EStructuralFeature getContentNameAttribute() {
		return AgreementsPackage.eINSTANCE.getAgreementGroup_Name();
	}

	@Override
	public void registerContent(AgreementGroupDescriptor content) {
		registerAgreementGroup(content);
	}

	@Override
	public void unregisterContent(String identifier) {
		unregisterAgreementGroup(identifier);
	}

	@Override
	protected final Resource createResource(URI uri) {
		return new AgreementsResourceImpl(uri);
	}

	@Override
	protected boolean emfResource(ResourceHandle handle) {
		return Agreements.xmi.equals(handle.type());
	}

	@Override
	protected KnownResources knownResources(OperatorWorkspace workspace) {
		return workspace.agreements();
	}

}
