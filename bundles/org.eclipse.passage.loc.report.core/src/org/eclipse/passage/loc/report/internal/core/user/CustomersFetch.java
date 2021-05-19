/*******************************************************************************
 * Copyright (c) 2019, 2021 ArSysOp
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

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.passage.loc.yars.internal.api.FetchedData;

/**
 * <p>
 * An implementation of {@code YARS}'s {@linkplain FetchedData} that lazily
 * polls {@linkplain CustomerStorage} for user information.
 * </p>
 * 
 * @since 0.1
 */
@SuppressWarnings("restriction")
final class CustomersFetch implements FetchedData<CustomerStorage, ProductCustomer> {

	private final CustomerStorage source;
	private final Set<String> products;

	CustomersFetch(CustomerStorage source, Set<String> products) {
		this.source = source;
		this.products = products;
	}

	/**
	 * <p>
	 * The information fetched and formed in a {@linkplain ProductCustomer}
	 * instances and represent all customers that are interested in a product from
	 * the given set.
	 * </p>
	 * 
	 * @since 0.1
	 */
	@Override
	public List<ProductCustomer> get() {
		return Stream.concat(//
				source.personsUsedProducts(products).stream().map(ProductCustomer::new), //
				source.companiesUsedProducts(products).stream().map(ProductCustomer::new)//
		).collect(Collectors.toList());
	}

}
