/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.users.model;

import java.util.function.Supplier;

import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.lic.users.model.meta.UsersFactory;

public final class EmptyUser implements Supplier<User> {

	@Override
	public User get() {
		User user = UsersFactory.eINSTANCE.createUser();
		user.setContact(UsersFactory.eINSTANCE.createContact());
		return user;
	}

}
