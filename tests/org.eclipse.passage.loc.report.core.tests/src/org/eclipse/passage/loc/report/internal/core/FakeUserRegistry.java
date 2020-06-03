package org.eclipse.passage.loc.report.internal.core;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserLicenseDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;
import org.eclipse.passage.lic.users.registry.UserRegistry;

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
