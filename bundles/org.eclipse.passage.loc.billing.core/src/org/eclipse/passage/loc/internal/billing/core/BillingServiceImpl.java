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
 *     Nikifor Fedorov <zelenyhleb@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.internal.billing.core;

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
