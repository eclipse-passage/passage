package org.eclipse.passage.loc.billing.internal.core;

import java.util.Set;

import org.eclipse.passage.lic.users.UserLicenseDescriptor;

public interface BillingService {

	/**
	 * Returns a set of user licenses (licenses issued to this user).
	 *
	 * @param user licenses
	 *
	 * @return the user licenses identifiers set
	 */
	Set<String> getUserLicenses(Iterable<? extends UserLicenseDescriptor> licenses);

}
