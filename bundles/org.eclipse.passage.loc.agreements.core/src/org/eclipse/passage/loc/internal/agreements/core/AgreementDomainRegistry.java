/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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

import java.nio.file.Files;
import java.nio.file.Path;
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
import org.eclipse.passage.lic.agreements.AgreementsGroupDescriptor;
import org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage;
import org.eclipse.passage.lic.agreements.model.util.AgreementsResourceImpl;
import org.eclipse.passage.lic.equinox.io.InstallationPath;
import org.eclipse.passage.lic.internal.equinox.events.EquinoxEvent;
import org.eclipse.passage.loc.internal.agreements.AgreementRegistry;
import org.eclipse.passage.loc.internal.agreements.AgreementRegistryEvents;
import org.eclipse.passage.loc.internal.agreements.core.i18n.AgreementsCoreMessages;
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
public final class AgreementDomainRegistry extends BaseDomainRegistry<AgreementsGroupDescriptor>
		implements AgreementRegistry, EditingDomainRegistry<AgreementsGroupDescriptor> {

	private final Map<String, AgreementsGroupDescriptor> groups = new HashMap<>();
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
	public Class<AgreementsGroupDescriptor> getContentClass() {
		return AgreementsGroupDescriptor.class;
	}

	@Override
	public String resolveIdentifier(AgreementsGroupDescriptor content) {
		return content.getIdentifier();
	}

	@Override
	public Collection<AgreementsGroupDescriptor> groups() {
		return new ArrayList<>(groups.values());
	}

	@Override
	public AgreementsGroupDescriptor group(String identifier) {
		return groups.get(identifier);
	}

	@Override
	public Collection<AgreementDescriptor> agreements() {
		return new ArrayList<>(agreements.values());
	}

	@SuppressWarnings("unchecked")
	public Collection<AgreementDescriptor> agreements(String id) {
		AgreementsGroupDescriptor group = groups.get(id);
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
	protected DomainContentAdapter<AgreementsGroupDescriptor, AgreementDomainRegistry> createContentAdapter() {
		return new AgreementsDomainRegistryTracker(this);
	}

	public void registerAgreementsGroup(AgreementsGroupDescriptor group) {
		String identifier = group.getIdentifier();
		AgreementsGroupDescriptor existing = groups.put(identifier, group);
		if (existing != null) {
			String msg = NLS.bind(AgreementsCoreMessages.AgreementDomain_instance_duplication_message, existing, group);
			Platform.getLog(getClass()).warn(msg);
		}
		events.postEvent(new EquinoxEvent(AgreementRegistryEvents.AGREEMENTS_GROUP_CREATE, group).get());
		group.getAgreements().forEach(u -> registerAgreement(u));
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

	public void unregisterAgreementsGroup(String id) {
		AgreementsGroupDescriptor removed = groups.remove(id);
		if (removed != null) {
			events.postEvent(new EquinoxEvent(AgreementRegistryEvents.AGREEMENTS_GROUP_DELETE, removed).get());
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
		return AgreementsPackage.eINSTANCE.getAgreementsGroup();
	}

	@Override
	public EStructuralFeature getContentIdentifierAttribute() {
		return AgreementsPackage.eINSTANCE.getAgreement_Identifier();
	}

	@Override
	public EStructuralFeature getContentNameAttribute() {
		return AgreementsPackage.eINSTANCE.getAgreementsGroup_Name();
	}

	@Override
	public void registerContent(AgreementsGroupDescriptor content) {
		registerAgreementsGroup(content);
	}

	@Override
	public void unregisterContent(String identifier) {
		unregisterAgreementsGroup(identifier);
	}

	@Override
	protected Path getResourceSetPath() throws Exception {
		Path passagePath = new InstallationPath().get();
		Files.createDirectories(passagePath);
		return passagePath.resolve(domainName);
	}

	@Override
	protected final Resource createResource(URI uri) {
		return new AgreementsResourceImpl(uri);
	}

}
