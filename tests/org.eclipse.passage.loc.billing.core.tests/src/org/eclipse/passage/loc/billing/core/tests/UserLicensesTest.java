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

import org.eclipse.passage.lic.users.UserLicenseDescriptor;
import org.eclipse.passage.loc.internal.billing.core.UserLicenses;
import org.junit.Test;

public class UserLicensesTest {

	@Test
	public void testGetAllLicenses() {
		List<UserLicenseDescriptor> licenses1 = new ArrayList<UserLicenseDescriptor>();
		licenses1.add(new FakeLicenseDescriptor("foo")); //$NON-NLS-1$
		licenses1.add(new FakeLicenseDescriptor("bar")); //$NON-NLS-1$

		List<UserLicenseDescriptor> licenses2 = new ArrayList<UserLicenseDescriptor>();
		licenses2.add(new FakeLicenseDescriptor("baz")); //$NON-NLS-1$
		licenses2.add(new FakeLicenseDescriptor("foo")); //$NON-NLS-1$

		UserLicenses userLicenses = new UserLicenses(
				Arrays.asList(new FakeUser[] { new FakeUser(licenses1), new FakeUser(licenses2) }));

		List<UserLicenseDescriptor> expectedLicenses = new ArrayList<UserLicenseDescriptor>(licenses1);
		expectedLicenses.addAll(licenses2);

		assertEquals(expectedLicenses, userLicenses.getAllLicenses());
	}

	@Test
	public void testGetLicensesForProduct() {
		String selectedProduct = "product"; //$NON-NLS-1$
		FakeLicenseDescriptor license1 = new FakeLicenseDescriptor(selectedProduct);
		FakeLicenseDescriptor license2 = new FakeLicenseDescriptor(selectedProduct);

		List<UserLicenseDescriptor> licenses1 = new ArrayList<UserLicenseDescriptor>();
		licenses1.add(license1);
		licenses1.add(new FakeLicenseDescriptor("bar")); //$NON-NLS-1$

		List<UserLicenseDescriptor> licenses2 = new ArrayList<UserLicenseDescriptor>();
		licenses2.add(new FakeLicenseDescriptor("baz")); //$NON-NLS-1$
		licenses2.add(license2);

		UserLicenses userLicenses = new UserLicenses(
				Arrays.asList(new FakeUser[] { new FakeUser(licenses1), new FakeUser(licenses2) }));

		List<UserLicenseDescriptor> expectedLicenses = new ArrayList<UserLicenseDescriptor>();
		expectedLicenses.add(license1);
		expectedLicenses.add(license2);

		assertEquals(expectedLicenses, userLicenses.getLicensesForProduct(selectedProduct));
	}

	@Test
	public void testGetLicensesForProductVersion() {
		String selectedProduct = "product"; //$NON-NLS-1$
		String selectedVersion = "version"; //$NON-NLS-1$

		FakeLicenseDescriptor license1 = new FakeLicenseDescriptor(selectedProduct, selectedVersion);
		FakeLicenseDescriptor license2 = new FakeLicenseDescriptor(selectedProduct); // default version is empty string

		List<UserLicenseDescriptor> licenses1 = new ArrayList<UserLicenseDescriptor>();
		licenses1.add(license1);
		licenses1.add(new FakeLicenseDescriptor("bar")); //$NON-NLS-1$

		List<UserLicenseDescriptor> licenses2 = new ArrayList<UserLicenseDescriptor>();
		licenses2.add(new FakeLicenseDescriptor("baz")); //$NON-NLS-1$
		licenses2.add(license2);

		UserLicenses userLicenses = new UserLicenses(
				Arrays.asList(new FakeUser[] { new FakeUser(licenses1), new FakeUser(licenses2) }));

		List<UserLicenseDescriptor> expectedLicenses = new ArrayList<UserLicenseDescriptor>();
		expectedLicenses.add(license1);

		assertEquals(expectedLicenses, userLicenses.getLicensesForProductVersion(selectedProduct, selectedVersion));
	}

}
