package org.eclipse.passage.loc.billing.internal.tests;

import java.util.Set;

import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserLicenseDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;

public class FakeUser implements UserDescriptor {
	
	private final Set<UserLicenseDescriptor> licenses;
	
	public FakeUser(Set<UserLicenseDescriptor> licenses) {
		this.licenses = licenses;
	}

	@Override
	public String getIdentifier() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getEmail() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getFullName() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getDescription() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getPreferredConditionType() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getPreferredConditionExpression() {
		throw new UnsupportedOperationException();
	}

	@Override
	public UserOriginDescriptor getUserOrigin() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<? extends UserLicenseDescriptor> getUserLicenses() {
		return licenses;
	}

}
