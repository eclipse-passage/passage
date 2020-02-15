package org.eclipse.passage.loc.billing.internal.core;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.passage.lic.users.UserLicenseDescriptor;

public class BillingServiceImpl implements BillingService {

	@Override
	public Set<String> getUserLicenses(Iterable<? extends UserLicenseDescriptor> licenses) {
		Set<String> set = new HashSet<String>();
		for (UserLicenseDescriptor descriptor : licenses) {
			set.add(descriptor.getPlanIdentifier());
		}
		return set;
	}

}
