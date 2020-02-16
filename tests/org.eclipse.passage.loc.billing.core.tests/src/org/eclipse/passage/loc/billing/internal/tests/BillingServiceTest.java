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
package org.eclipse.passage.loc.billing.internal.tests;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserLicenseDescriptor;
import org.eclipse.passage.loc.internal.billing.core.BillingService;
import org.eclipse.passage.loc.internal.billing.core.BillingServiceImpl;
import org.junit.Test;

public class BillingServiceTest {

	@Test
	public void getUserLicensesTest() {
		BillingService service = new BillingServiceImpl();

		String identifier1 = "foo"; //$NON-NLS-1$
		String identifier2 = "bar"; //$NON-NLS-1$
		String identifier3 = "baz"; //$NON-NLS-1$

		Set<UserLicenseDescriptor> licenses = new HashSet<UserLicenseDescriptor>();
		licenses.add(new FakeLicenseDescriptor(identifier1));
		licenses.add(new FakeLicenseDescriptor(identifier2));
		licenses.add(new FakeLicenseDescriptor(identifier3));

		Set<String> identifiers = new HashSet<String>();
		identifiers.add(identifier1);
		identifiers.add(identifier2);
		identifiers.add(identifier3);

		UserDescriptor user = new FakeUser(licenses);
		assertEquals(identifiers, service.getUserLicenses(user.getUserLicenses()));
	}

	@Test
	public void getUserLicensesFilteredByDateTest() {
		BillingService service = new BillingServiceImpl();

		String identifier1 = "foo"; //$NON-NLS-1$
		Date issueDate1 = new Date(2020, 1, 12);
		String identifier2 = "bar"; //$NON-NLS-1$
		Date issueDate2 = new Date(2018, 4, 1);
		String identifier3 = "baz"; //$NON-NLS-1$
		Date issueDate3 = new Date(2019, 7, 22);
		String identifier4 = "qux"; //$NON-NLS-1$
		Date issueDate4 = new Date(2016, 8, 5);

		Set<UserLicenseDescriptor> licenses = new HashSet<UserLicenseDescriptor>();
		licenses.add(new FakeLicenseDescriptor(identifier1, issueDate1));
		licenses.add(new FakeLicenseDescriptor(identifier2, issueDate2));
		licenses.add(new FakeLicenseDescriptor(identifier3, issueDate3));
		licenses.add(new FakeLicenseDescriptor(identifier4, issueDate4));

		// All identifiers except for two not matching
		Set<String> identifiers = new HashSet<String>();
		identifiers.add(identifier2);
		identifiers.add(identifier3);

		UserDescriptor user = new FakeUser(licenses);
		assertEquals(identifiers, service.getUserLicenses(user.getUserLicenses(), new Date(2017, 1, 20), new Date(2019, 8, 1)));
	}

}
