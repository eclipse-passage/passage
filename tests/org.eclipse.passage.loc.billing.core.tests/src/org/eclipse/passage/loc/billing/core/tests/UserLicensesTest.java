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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserLicenseDescriptor;
import org.eclipse.passage.loc.internal.billing.core.ProductVersionLicense;
import org.eclipse.passage.loc.internal.billing.core.UserLicenses;
import org.junit.Test;

public class UserLicensesTest {

	private final StringsProvider provider = new StringsProvider();

	@Test
	public void allLicenses() {
		List<UserLicenseDescriptor> licenses1 = licenses(license(provider.randomString()),
				license(provider.randomString()));
		List<UserLicenseDescriptor> licenses2 = licenses(license(provider.randomString()),
				license(provider.randomString()));

		UserLicenses service = service(user(licenses1), user(licenses2));

		assertEquals(Stream.concat(licenses1.stream(), licenses2.stream()).collect(Collectors.toList()),
				service.getAllLicenses());
	}

	@Test
	public void licensesForProduct() {
		String product = provider.randomString();
		List<UserLicenseDescriptor> licenses1 = licenses(license(product), license(provider.randomString()));
		List<UserLicenseDescriptor> licenses2 = licenses(license(product), license(provider.randomString()));

		UserLicenses service = service(user(licenses1), user(licenses2));

		assertEquals(Arrays.asList(license(product), license(product)), service.getLicensesForProduct(product));
	}

	@Test
	public void licensesForProductVersion() {
		String product = provider.randomString();
		String version = provider.randomString();

		UserLicenses service = service(
				user(licenses(license(product, version), license(provider.randomString()))),
				user(licenses(license(product), license(provider.randomString()))));

		assertEquals(Arrays.asList(license(product, version)),
				service.getLicensesForProductVersion(product, version));
	}

	@Test
	public void countSameLicenses() {
		UserLicenseDescriptor license1 = license(provider.randomString(), provider.randomString());
		UserLicenseDescriptor license2 = license(provider.randomString(), provider.randomString());
		UserLicenseDescriptor license3 = license(provider.randomString(), provider.randomString());

		UserLicenses service = service(user(licenses(license1, license3)),
				user(licenses(license2, license3)), user(licenses(license1, license2, license3)));

		assertEquals(Arrays.asList(2, 2, 3), numbers(service, license1, license2, license3));
	}

	private List<Integer> numbers(UserLicenses service, UserLicenseDescriptor... descriptors) {
		return Arrays.asList(descriptors).stream()
				.map(ProductVersionLicense::new)
				.map(license -> service.getLicensesNumbers().get(license))
				.collect(Collectors.toList());
	}

	private UserLicenseDescriptor license(String product) {
		return new FakeLicenseDescriptor(product);
	}

	private UserLicenseDescriptor license(String product, String version) {
		return new FakeLicenseDescriptor(product, version);
	}

	private List<UserLicenseDescriptor> licenses(UserLicenseDescriptor... fakeLicenseDescriptors) {
		return Arrays.asList(fakeLicenseDescriptors);
	}

	private UserDescriptor user(List<UserLicenseDescriptor> licenses) {
		return new FakeUser(licenses);
	}

	private UserLicenses service(UserDescriptor... users) {
		return new UserLicenses(Arrays.asList(users));
	}

}
