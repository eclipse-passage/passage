/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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

import org.eclipse.passage.lic.emf.edit.ClassifierInitializer;
import org.eclipse.passage.lic.emf.edit.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.internal.users.core.i18n.UsersCoreMessages;
import org.eclipse.passage.loc.users.core.Users;
import org.osgi.service.component.annotations.Component;

@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + Users.DOMAIN_NAME })
public final class UserOriginClassifierInitializer implements ClassifierInitializer {

	@Override
	public String newObjectIdentifier() {
		return "new.user.origin"; //$NON-NLS-1$
	}

	@Override
	public String newObjectName() {
		return UsersCoreMessages.UserOriginClassifierInitializer_object_name;
	}

	@Override
	public String newFileName() {
		return "new_user_origin"; //$NON-NLS-1$ ;
	}

	@Override
	public String newObjectTitle() {
		return UsersCoreMessages.UserOriginClassifierInitializer_object_title;
	}

	@Override
	public String newObjectMessage() {
		return UsersCoreMessages.UserOriginClassifierInitializer_object_message;
	}

	@Override
	public String newFileMessage() {
		return UsersCoreMessages.UserOriginClassifierInitializer_new_file_message;
	}
}