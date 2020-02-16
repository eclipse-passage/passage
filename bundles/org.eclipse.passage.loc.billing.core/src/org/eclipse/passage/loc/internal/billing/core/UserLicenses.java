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

import java.util.LinkedList;
import java.util.List;

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
		return getLicenses(new Condition() {
			@Override
			public boolean check(UserLicenseDescriptor descriptor) {
				// Add in any case
				return true;
			}
		});
	}

	/**
	 * Returns licenses filtered by product
	 * 
	 * @param productIdentifier identifier of product operator looks for
	 * 
	 * @return Linked list of licenses
	 */
	public final List<UserLicenseDescriptor> getLicensesForProduct(String productIdentifier) {
		return getLicenses(new Condition() {
			@Override
			public boolean check(UserLicenseDescriptor descriptor) {
				// Add if product identifier is the same as needed
				return descriptor.getProductIdentifier().equals(productIdentifier);
			}
		});
	}

	/**
	 * Returns licenses filtered by product and version
	 * 
	 * @param productIdentifier identifier of product operator looks for
	 * @param productVersio identifier of version operator looks for
	 * 
	 * @return Linked list of licenses
	 */
	public final List<UserLicenseDescriptor> getLicensesForProductVersion(String productIdentifier,
			String versionIdentifier) {
		return getLicenses(new Condition() {
			@Override
			public boolean check(UserLicenseDescriptor descriptor) {
				// Add if product identifier AND version is the same as needed
				return descriptor.getProductIdentifier().equals(productIdentifier)
						&& descriptor.getProductVersion().equals(versionIdentifier);
			}
		});
	}

	/**
	 * Returns licenses following condition provided through @param condition
	 * 
	 * @param condition adding logic, see {@code Condition}
	 * 
	 * @return Linked list of licenses
	 */
	private final List<UserLicenseDescriptor> getLicenses(Condition condition) {
		List<UserLicenseDescriptor> licenses = new LinkedList<UserLicenseDescriptor>();
		for (UserDescriptor user : users) {
			Iterable<? extends UserLicenseDescriptor> userLicenses = user.getUserLicenses();
			for (UserLicenseDescriptor userLicenseDescriptor : userLicenses) {
				if (condition.check(userLicenseDescriptor)) {
					licenses.add(userLicenseDescriptor);
				}
			}
		}
		return licenses;
	}

	/**
	 * <p>
	 * Interface for condition to add license pack to output
	 * </p>
	 */
	private interface Condition {

		/**
		 * Method can be implemented in order to provide some descriptor adding logic
		 * 
		 * @param descriptor license descriptor instance provides params to implement
		 *                   adding logic
		 * @return result of check
		 */
		boolean check(UserLicenseDescriptor descriptor);

	}

}
