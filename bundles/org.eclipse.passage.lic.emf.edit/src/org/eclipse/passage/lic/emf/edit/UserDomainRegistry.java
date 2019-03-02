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
package org.eclipse.passage.lic.emf.edit;

import org.eclipse.passage.lic.runtime.registry.DescriptorRegistry;
import org.eclipse.passage.lic.runtime.users.UserDescriptor;
import org.eclipse.passage.lic.runtime.users.UserOriginDescriptor;
import org.eclipse.passage.lic.runtime.users.UserRegistry;

public interface UserDomainRegistry extends UserRegistry, EditingDomainRegistry, DescriptorRegistry {

	void registerUserOrigin(UserOriginDescriptor userOrigin);

	void unregisterUserOrigin(String userOriginId);
	
	void registerUser(UserDescriptor user);

	void unregisterUser(String userId);
	
}
