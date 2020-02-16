package org.eclipse.passage.loc.internal.billing.core;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserLicenseDescriptor;

public class UserLicenses {

	private final List<UserDescriptor> users;

	public UserLicenses(List<UserDescriptor> users) {
		this.users = users;
	}

	public List<UserLicenseDescriptor> getAllLicenses() {
		return getLicenses(new Condition() {
			@Override
			public boolean check(UserLicenseDescriptor descriptor) {
				//Add in any case
				return true;
			}
		});
	}


	public List<UserLicenseDescriptor> getLicensesForProduct(String productIdentifier) {
		return getLicenses(new Condition() {			
			@Override
			public boolean check(UserLicenseDescriptor descriptor) {
				//Add if product identifier is the same as needed
				return descriptor.getProductIdentifier().equals(productIdentifier);
			}
		});
	}

	public List<UserLicenseDescriptor> getLicensesForProductVersion(String productIdentifier,
			String versionIdentifier) {
		return getLicenses(new Condition() {
			@Override
			public boolean check(UserLicenseDescriptor descriptor) {
				//Add if product identifier AND version is the same as needed
				return descriptor.getProductIdentifier().equals(productIdentifier)
						&& descriptor.getProductVersion().equals(versionIdentifier);
			}
		});
	}

	public List<UserLicenseDescriptor> getLicenses(Condition condition) {
		List<UserLicenseDescriptor> licenses = new LinkedList<UserLicenseDescriptor>();
		for (UserDescriptor user : users) {
			Iterable<? extends UserLicenseDescriptor> userLicenses = user.getUserLicenses();
			for (UserLicenseDescriptor userLicenseDescriptor : userLicenses) {
				if (condition.check(userLicenseDescriptor)) {
					licenses.add(userLicenseDescriptor);
				}
			}
		}
		return licenses;
	}


	private interface Condition {

		boolean check(UserLicenseDescriptor descriptor);

	}

}
