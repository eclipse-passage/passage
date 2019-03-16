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

import org.eclipse.passage.lic.emf.edit.ClassifierInitializer;
import org.eclipse.passage.lic.emf.edit.EditingDomainRegistryAccess;
import org.eclipse.passage.lic.users.registry.Users;
import org.osgi.service.component.annotations.Component;

@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + Users.DOMAIN_NAME })
public final class UserOriginClassifierInitializer implements ClassifierInitializer {

	@Override
	public String newObjectIdentifier() {
		return "new.user.origin";
	}

	@Override
	public String newObjectName() {
		return "New User Origin";
	}

	@Override
	public String newFileName() {
		return "new_user_origin"; //$NON-NLS-1$ ;
	}

	@Override
	public String newObjectTitle() {
		return "User Origin";
	}

	@Override
	public String newObjectMessage() {
		return "New User Origin";
	}

	@Override
	public String newFileMessage() {
		return "Please specify a file name to store user data";
	}
}