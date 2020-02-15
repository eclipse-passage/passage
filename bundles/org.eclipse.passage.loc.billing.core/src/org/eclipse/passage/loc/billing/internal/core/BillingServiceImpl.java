package org.eclipse.passage.loc.billing.internal.core;

import java.util.Date;
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

	@Override
	public Set<String> getUserLicenses(Iterable<? extends UserLicenseDescriptor> licenses, Date from, Date to) {
		Set<String> set = new HashSet<String>();
		for (UserLicenseDescriptor descriptor : licenses) {
			Date issueDate = descriptor.getIssueDate();
			if (issueDate.after(from) && issueDate.before(to)) {
				set.add(descriptor.getPlanIdentifier());
			}
		}
		return set;
	}

}
