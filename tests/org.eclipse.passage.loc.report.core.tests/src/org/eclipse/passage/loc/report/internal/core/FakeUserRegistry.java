/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserLicenseDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;
import org.eclipse.passage.loc.internal.users.UserRegistry;

public final class FakeUserRegistry implements UserRegistry {

	private final List<UserDescriptor> users;

	public FakeUserRegistry(List<UserDescriptor> users) {
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
	public UserDescriptor getUser(String userId) {
		return users.stream() //
				.filter(user -> user.getIdentifier().equals(userId)) //
				.findFirst() //
				.get();
	}

	@Override
	public Iterable<? extends UserLicenseDescriptor> getUserLicenses() {
		return users.stream() //
				.flatMap(user -> StreamSupport.stream(user.getUserLicenses().spliterator(), false)) //
				.collect(Collectors.toSet());
	}

}
