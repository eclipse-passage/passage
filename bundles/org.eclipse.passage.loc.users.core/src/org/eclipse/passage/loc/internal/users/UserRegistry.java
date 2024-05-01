/*******************************************************************************
 * Copyright (c) 2018, 2024 ArSysOp
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
package org.eclipse.passage.loc.internal.users;

import java.util.Collection;
import java.util.Optional;

import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.lic.users.model.api.UserOrigin;

public interface UserRegistry {

	Collection<UserOrigin> userOrigins();

	Optional<UserOrigin> userOrigin(String id);

	Collection<User> users();

	Optional<User> user(String userId);

}
