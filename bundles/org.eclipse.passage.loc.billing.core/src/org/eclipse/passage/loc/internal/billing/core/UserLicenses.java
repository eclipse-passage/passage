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

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserLicenseDescriptor;

public class UserLicenses {

	/**
	 * All users list
	 * 
	 */
	private final List<UserDescriptor> users;

	public UserLicenses(List<UserDescriptor> users) {
		this.users = users;
	}

	/**
	 * Returns all licenses of all users
	 * 
	 * @return Linked list of licenses
	 */
	public final List<UserLicenseDescriptor> getAllLicenses() {
		Predicate<UserLicenseDescriptor> any = new Predicate<UserLicenseDescriptor>() {
			@Override
			public boolean test(UserLicenseDescriptor t) {
				// Add in any case
				return true;
			}
		};
		return getLicenses(any);
	}

	/**
	 * Returns licenses filtered by product
	 * 
	 * @param productIdentifier identifier of product operator looks for
	 * 
	 * @return Linked list of licenses
	 */
	public final List<UserLicenseDescriptor> getLicensesForProduct(String productIdentifier) {
		Predicate<UserLicenseDescriptor> product = new Predicate<UserLicenseDescriptor>() {
			@Override
			public boolean test(UserLicenseDescriptor descriptor) {
				// Add if product identifier is the same as needed
				return descriptor.getProductIdentifier().equals(productIdentifier);
			}
		};
		return getLicenses(product);
	}

	/**
	 * Returns licenses filtered by product and version
	 * 
	 * @param productIdentifier identifier of product operator looks for
	 * @param productVersio     identifier of version operator looks for
	 * 
	 * @return Linked list of licenses
	 */
	public final List<UserLicenseDescriptor> getLicensesForProductVersion(String productIdentifier,
			String versionIdentifier) {
		Predicate<UserLicenseDescriptor> productAndVersion = new Predicate<UserLicenseDescriptor>() {
			@Override
			public boolean test(UserLicenseDescriptor descriptor) {
				// Add if product identifier AND version is the same as needed
				return descriptor.getProductIdentifier().equals(productIdentifier)
						&& descriptor.getProductVersion().equals(versionIdentifier);
			}
		};
		return getLicenses(productAndVersion);
	}

	/**
	 * Returns licenses following condition provided through @param condition
	 * 
	 * @param condition adding logic
	 * 
	 * @return Linked list of licenses
	 */
	private final List<UserLicenseDescriptor> getLicenses(Predicate<UserLicenseDescriptor> condition) {
		return users.stream()
				.map(UserDescriptor::getUserLicenses)
				.flatMap(iterable -> StreamSupport.stream(iterable.spliterator(), false))
				.filter(condition)
				.collect(Collectors.toList());
	}

}
