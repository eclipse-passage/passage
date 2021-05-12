/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
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
package org.eclipse.passage.loc.internal.users.core;

import java.util.Collections;

import org.eclipse.passage.lic.users.model.meta.UsersPackage;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.internal.emf.SelectionCommandAdvisor;
import org.eclipse.passage.loc.internal.users.UserRegistry;
import org.eclipse.passage.loc.internal.users.core.i18n.UsersCoreMessages;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + UsersPackage.eNAME })
public class UsersSelectionCommandAdvisor implements SelectionCommandAdvisor {

	private UserRegistry userRegistry;

	@Reference
	public void bindDomainRegistry(UserRegistry registry) {
		this.userRegistry = registry;
	}

	public void unbindDomainRegistry(UserRegistry registry) {
		if (this.userRegistry == registry) {
			this.userRegistry = null;
		}
	}

	@Override
	public String getSelectionTitle(String classifier) {
		if (UsersPackage.eINSTANCE.getUserOrigin().getName().equals(classifier)) {
			return UsersCoreMessages.UsersSelectionCommandAdvisor_select_user_origin;
		}
		if (UsersPackage.eINSTANCE.getUser().getName().equals(classifier)) {
			return UsersCoreMessages.UsersSelectionCommandAdvisor_select_user;
		}
		return null;
	}

	@Override
	public Iterable<?> getSelectionInput(String classifier) {
		if (userRegistry == null) {
			return Collections.emptyList();
		}
		if (UsersPackage.eINSTANCE.getUserOrigin().getName().equals(classifier)) {
			return userRegistry.getUserOrigins();
		}
		if (UsersPackage.eINSTANCE.getUser().getName().equals(classifier)) {
			return userRegistry.getUsers();
		}
		return Collections.emptyList();
	}

}
