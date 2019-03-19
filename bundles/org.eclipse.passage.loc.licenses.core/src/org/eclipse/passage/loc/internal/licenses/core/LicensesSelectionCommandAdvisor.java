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

import java.util.Collections;

import org.eclipse.passage.lic.emf.edit.EditingDomainRegistryAccess;
import org.eclipse.passage.lic.emf.edit.SelectionCommandAdvisor;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.lic.licenses.registry.LicenseRegistry;
import org.eclipse.passage.loc.licenses.core.Licenses;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + Licenses.DOMAIN_NAME })
public class LicensesSelectionCommandAdvisor implements SelectionCommandAdvisor {

	private LicenseRegistry registry;

	@Reference
	public void bindDomainRegistry(LicenseRegistry registry) {
		this.registry = registry;
	}

	public void unbindDomainRegistry(LicenseRegistry registry) {
		this.registry = null;
	}

	@Override
	public String getSelectionTitle(String classifier) {
		if (LicensesPackage.eINSTANCE.getLicensePack().getName().equals(classifier)) {
			return "Select License Pack";
		}
		return null;
	}

	@Override
	public Iterable<?> getSelectionInput(String classifier) {
		if (registry == null) {
			return Collections.emptyList();
		}
		if (LicensesPackage.eINSTANCE.getLicensePack().getName().equals(classifier)) {
			return registry.getLicensePacks();
		}
		return Collections.emptyList();
	}

}
