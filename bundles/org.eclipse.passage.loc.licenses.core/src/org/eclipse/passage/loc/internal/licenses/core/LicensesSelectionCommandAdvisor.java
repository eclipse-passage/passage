/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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

import java.util.Collections;

import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.internal.emf.SelectionCommandAdvisor;
import org.eclipse.passage.loc.internal.licenses.LicenseRegistry;
import org.eclipse.passage.loc.internal.licenses.core.i18n.LicensesCoreMessages;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + LicensesPackage.eNAME })
public class LicensesSelectionCommandAdvisor implements SelectionCommandAdvisor {

	private LicenseRegistry licenseRegistry;

	@Reference
	public void bindDomainRegistry(LicenseRegistry registry) {
		this.licenseRegistry = registry;
	}

	public void unbindDomainRegistry(LicenseRegistry registry) {
		if (this.licenseRegistry == registry) {
			this.licenseRegistry = null;
		}
	}

	@Override
	public String getSelectionTitle(String classifier) {
		if (LicensesPackage.eINSTANCE.getLicensePlan().getName().equals(classifier)) {
			return LicensesCoreMessages.LicensesSelectionCommandAdvisor_select_lic_plan;
		}
		return null;
	}

	@Override
	public Iterable<?> getSelectionInput(String classifier) {
		if (licenseRegistry == null) {
			return Collections.emptyList();
		}
		if (LicensesPackage.eINSTANCE.getLicensePlan().getName().equals(classifier)) {
			return licenseRegistry.getLicensePlans();
		}
		return Collections.emptyList();
	}

}
