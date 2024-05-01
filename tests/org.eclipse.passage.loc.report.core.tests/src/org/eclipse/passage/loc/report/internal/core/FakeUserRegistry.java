/*******************************************************************************
 * Copyright (c) 2021, 2024 ArSysOp
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

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.lic.users.model.api.UserOrigin;
import org.eclipse.passage.loc.internal.users.UserRegistry;

final class FakeUserRegistry implements UserRegistry {

	private final List<User> users;

	FakeUserRegistry(List<User> users) {
		this.users = users;
	}

	@Override
	public Collection<UserOrigin> userOrigins() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Optional<UserOrigin> userOrigin(String userOriginId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<User> users() {
		return users;
	}

	@Override
	public Optional<User> user(String id) {
		return users.stream() //
				.filter(user -> user.getIdentifier().equals(id)) //
				.findFirst();
	}

}
