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
package org.eclipse.passage.loc.billing.core.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.passage.lic.users.UserLicenseDescriptor;
import org.eclipse.passage.loc.internal.billing.core.UserLicenses;
import org.junit.Test;

public class UserLicensesTest {

	@Test
	public void allLicenses() {
		List<UserLicenseDescriptor> licenses1 = new ArrayList<UserLicenseDescriptor>();
		licenses1.add(new FakeLicenseDescriptor("foo")); //$NON-NLS-1$
		licenses1.add(new FakeLicenseDescriptor("bar")); //$NON-NLS-1$

		List<UserLicenseDescriptor> licenses2 = new ArrayList<UserLicenseDescriptor>();
		licenses2.add(new FakeLicenseDescriptor("baz")); //$NON-NLS-1$
		licenses2.add(new FakeLicenseDescriptor("foo")); //$NON-NLS-1$

		UserLicenses service = new UserLicenses(
				Arrays.asList(new FakeUser[] { new FakeUser(licenses1), new FakeUser(licenses2) }));

		assertEquals(Stream.concat(licenses1.stream(), licenses2.stream()).collect(Collectors.toList()),
				service.getAllLicenses());
	}

	@Test
	public void licensesForProduct() {
		String product = "product"; //$NON-NLS-1$
		FakeLicenseDescriptor license1 = new FakeLicenseDescriptor(product);
		FakeLicenseDescriptor license2 = new FakeLicenseDescriptor(product);

		List<UserLicenseDescriptor> licenses1 = new ArrayList<UserLicenseDescriptor>();
		licenses1.add(license1);
		licenses1.add(new FakeLicenseDescriptor("bar")); //$NON-NLS-1$

		List<UserLicenseDescriptor> licenses2 = new ArrayList<UserLicenseDescriptor>();
		licenses2.add(new FakeLicenseDescriptor("baz")); //$NON-NLS-1$
		licenses2.add(license2);

		UserLicenses service = new UserLicenses(
				Arrays.asList(new FakeUser[] { new FakeUser(licenses1), new FakeUser(licenses2) }));

		assertEquals(Arrays.asList(license1, license2), service.getLicensesForProduct(product));
	}

	@Test
	public void licensesForProductVersion() {
		String product = "product"; //$NON-NLS-1$
		String version = "version"; //$NON-NLS-1$

		FakeLicenseDescriptor license1 = new FakeLicenseDescriptor(product, version);
		FakeLicenseDescriptor license2 = new FakeLicenseDescriptor(product); // default version is empty string

		List<UserLicenseDescriptor> licenses1 = new ArrayList<UserLicenseDescriptor>();
		licenses1.add(license1);
		licenses1.add(new FakeLicenseDescriptor("bar")); //$NON-NLS-1$

		List<UserLicenseDescriptor> licenses2 = new ArrayList<UserLicenseDescriptor>();
		licenses2.add(new FakeLicenseDescriptor("baz")); //$NON-NLS-1$
		licenses2.add(license2);

		UserLicenses service = new UserLicenses(
				Arrays.asList(new FakeUser[] { new FakeUser(licenses1), new FakeUser(licenses2) }));

		assertEquals(Arrays.asList(license1), service.getLicensesForProductVersion(product, version));
	}

}
