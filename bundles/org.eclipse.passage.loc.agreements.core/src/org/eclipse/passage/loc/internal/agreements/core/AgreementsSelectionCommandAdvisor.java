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

import java.util.Collections;

import org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage;
import org.eclipse.passage.loc.internal.agreements.AgreementRegistry;
import org.eclipse.passage.loc.internal.agreements.core.i18n.AgreementsCoreMessages;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.internal.emf.SelectionCommandAdvisor;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@SuppressWarnings("restriction")
@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + AgreementsPackage.eNAME })
public class AgreementsSelectionCommandAdvisor implements SelectionCommandAdvisor {

	private AgreementRegistry registry;

	@Reference
	public void bindDomainRegistry(AgreementRegistry dr) {
		this.registry = dr;
	}

	public void unbindDomainRegistry(AgreementRegistry dr) {
		this.registry = null;
	}

	@Override
	public String getSelectionTitle(String classifier) {
		if (AgreementsPackage.eINSTANCE.getAgreementGroup().getName().equals(classifier)) {
			return AgreementsCoreMessages.AgreementsSelectionCommandAdvisor_select_agreement_group;
		}
		if (AgreementsPackage.eINSTANCE.getAgreement().getName().equals(classifier)) {
			return AgreementsCoreMessages.AgreementsSelectionCommandAdvisor_select_agreement;
		}
		return null;
	}

	@Override
	public Iterable<?> getSelectionInput(String classifier) {
		if (registry == null) {
			return Collections.emptyList();
		}
		if (AgreementsPackage.eINSTANCE.getAgreementGroup().getName().equals(classifier)) {
			return registry.groups();
		}
		if (AgreementsPackage.eINSTANCE.getAgreement().getName().equals(classifier)) {
			return registry.agreements();
		}
		return Collections.emptyList();
	}

}
