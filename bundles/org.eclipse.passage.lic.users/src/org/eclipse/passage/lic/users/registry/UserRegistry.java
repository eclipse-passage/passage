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
package org.eclipse.passage.lic.users.registry;

import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserLicenseDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;

public interface UserRegistry {

	Iterable<? extends UserOriginDescriptor> getUserOrigins();

	UserOriginDescriptor getUserOrigin(String userOriginId);

	Iterable<? extends UserDescriptor> getUsers();

	UserDescriptor getUser(String userId);

	Iterable<? extends UserLicenseDescriptor> getUserLicenses();

}
