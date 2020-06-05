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

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.passage.loc.yars.internal.api.FetchParams;

/**
 * {@code YARS} {@linkplain FetchParams} implementation specifies set of product
 * of interest.
 * 
 * @since 0.1
 */
@SuppressWarnings("restriction")
final class ProductNames implements FetchParams {

	private final Set<String> products;

	/**
	 * @param products set of product identifiers
	 * @since 0.1
	 */
	public ProductNames(Collection<String> products) {
		this.products = new HashSet<String>(products);
	}

	/**
	 * @param products direct enlistment of product identifiers
	 * @since 0.1
	 */
	public ProductNames(String... products) {
		this(Arrays.asList(products));
	}

	/**
	 * @return products set of product identifiers that the querying side is
	 *         interested in
	 * @since 0.1
	 */
	public Set<String> products() {
		return products;
	}

}
