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
package org.eclipse.passage.loc.internal.users.core;

import java.util.Collections;

import org.eclipse.passage.lic.emf.edit.DomainRegistryAccess;
import org.eclipse.passage.lic.emf.edit.SelectionCommandAdvisor;
import org.eclipse.passage.lic.model.meta.LicPackage;
import org.eclipse.passage.lic.runtime.users.UsersRegistry;
import org.eclipse.passage.lic.runtime.users.Users;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(property = { DomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + Users.DOMAIN_NAME })
public class UsersSelectionCommandAdvisor implements SelectionCommandAdvisor {
	
	private UsersRegistry registry;
	
	@Reference
	public void bindDomainRegistry(UsersRegistry registry) {
		this.registry = registry;
	}

	public void unbindDomainRegistry(UsersRegistry registry) {
		this.registry = null;
	}

	@Override
	public String getSelectionTitle(String classifier) {
		if (LicPackage.eINSTANCE.getUserOrigin().getName().equals(classifier)) {
			return "Select User Origin";
		}
		if (LicPackage.eINSTANCE.getUser().getName().equals(classifier)) {
			return "Select User";
		}
		return null;
	}

	@Override
	public Iterable<?> getSelectionInput(String classifier) {
		if (registry == null) {
			return Collections.emptyList();
		}
		if (LicPackage.eINSTANCE.getUserOrigin().getName().equals(classifier)) {
			return registry.getUserOrigins();
		}
		if (LicPackage.eINSTANCE.getUser().getName().equals(classifier)) {
			return registry.getUsers();
		}
		return Collections.emptyList();
	}

}
