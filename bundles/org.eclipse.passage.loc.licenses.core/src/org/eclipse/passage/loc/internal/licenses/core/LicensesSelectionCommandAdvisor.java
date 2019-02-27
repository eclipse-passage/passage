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

import org.eclipse.passage.lic.emf.edit.DomainRegistryAccess;
import org.eclipse.passage.lic.emf.edit.SelectionCommandAdvisor;
import org.eclipse.passage.lic.model.meta.LicPackage;
import org.eclipse.passage.lic.registry.LicensesRegistry;
import org.eclipse.passage.loc.edit.LicenseDomainRegistry;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(property = { DomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + LicensesRegistry.DOMAIN_NAME })
public class LicensesSelectionCommandAdvisor implements SelectionCommandAdvisor {
	
	private LicenseDomainRegistry registry;
	
	@Reference
	public void bindDomainRegistry(LicenseDomainRegistry registry) {
		this.registry = registry;
	}

	public void unbindDomainRegistry(LicenseDomainRegistry registry) {
		this.registry = null;
	}

	@Override
	public String getSelectionTitle(String classifier) {
		if (LicPackage.eINSTANCE.getLicensePack().getName().equals(classifier)) {
			return "Select License Pack";
		}
		return null;
	}

	@Override
	public Iterable<?> getSelectionInput(String classifier) {
		if (registry == null) {
			return Collections.emptyList();
		}
		if (LicPackage.eINSTANCE.getLicensePack().getName().equals(classifier)) {
			return registry.getLicensePacks();
		}
		return Collections.emptyList();
	}

}
