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
package org.eclipse.passage.loc.report.internal.core;

import java.util.List;

import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;
import org.eclipse.passage.loc.internal.users.UserRegistry;

final class FakeUserRegistry implements UserRegistry {

	private final List<UserDescriptor> users;

	FakeUserRegistry(List<UserDescriptor> users) {
		this.users = users;
	}

	@Override
	public Iterable<? extends UserOriginDescriptor> getUserOrigins() {
		throw new UnsupportedOperationException();
	}

	@Override
	public UserOriginDescriptor getUserOrigin(String userOriginId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<? extends UserDescriptor> getUsers() {
		return users;
	}

	@Override
	public UserDescriptor getUser(String id) {
		return users.stream() //
				.filter(user -> user.getIdentifier().equals(id)) //
				.findFirst() //
				.get();
	}

}
