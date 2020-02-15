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
import java.util.Set;

import org.eclipse.passage.lic.users.UserLicenseDescriptor;

/**
 * <p>
 * Central interface of the package: all license plans data processing are described here.
 * </p>
 * 
 * @since 0.1
 */
public interface BillingService {

	/**
	 * Returns set of all user {@code licenses} plans.
	 * 
	 * @param licenses iterable of user license descriptors
	 * 
	 * @return set of all user licenses plans
	 */
	Set<String> getUserLicenses(Iterable<? extends UserLicenseDescriptor> licenses);
	
	/**
	 * Returns set of all user {@code licenses} plans for chosen period.
	 * 
	 * @param licenses iterable of user license descriptors
	 * @param from licenses issued after this date will be chosen
	 * @param to licenses issued before this date will be chosen
	 * 
	 * @return set of user licenses plans
	 */
	Set<String> getUserLicenses(Iterable<? extends UserLicenseDescriptor> licenses, Date from, Date to);

}
