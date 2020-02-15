package org.eclipse.passage.loc.billing.internal.core;

import java.util.Date;
import java.util.Set;

import org.eclipse.passage.lic.users.UserLicenseDescriptor;

public interface BillingService {

	Set<String> getUserLicenses(Iterable<? extends UserLicenseDescriptor> licenses);
	
	Set<String> getUserLicenses(Iterable<? extends UserLicenseDescriptor> licenses, Date from, Date to);

}
