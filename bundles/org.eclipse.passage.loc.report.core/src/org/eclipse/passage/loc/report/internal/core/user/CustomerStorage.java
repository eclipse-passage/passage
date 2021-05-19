/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.report.internal.core.user;

import java.util.Set;

import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;
import org.eclipse.passage.loc.internal.users.UserRegistry;
import org.eclipse.passage.loc.yars.internal.api.Storage;

/**
 * A {@code YARS}'s {@linkplain Storage} dedicated to spread user information
 * built on top of {@code LIC} {@linkplain UserRegistry}.
 * 
 * @since 0.2
 */
@SuppressWarnings("restriction")
public interface CustomerStorage extends Storage<UserDescriptor> {

	/**
	 * Retrieve information of all users who ever got licenses (personal type of
	 * license) for any product from the given set of {@code products}.
	 * 
	 * @param products set of product identifiers
	 * @return set of {@linkplain UserDescriptor}s for all users that are interested
	 *         in a product from the given set
	 * @since 0.1
	 */
	Set<UserDescriptor> personsUsedProducts(Set<String> products);

	/**
	 * Retrieve information of all companies who ever got licenses (floating) for
	 * any product from the given set of {@code products}.
	 * 
	 * @param products set of product identifiers
	 * @return set of {@linkplain UserDescriptor}s for all companies that are
	 *         interested in a product from the given set
	 * @since 0.1
	 */
	Set<UserOriginDescriptor> companiesUsedProducts(Set<String> products);

	/**
	 * Find all products for which any known user ever has got a license.
	 * 
	 * @return all products with licensed issued
	 */
	Set<String> products();

}
