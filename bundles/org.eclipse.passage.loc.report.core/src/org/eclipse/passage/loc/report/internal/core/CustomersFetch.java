/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.report.internal.core;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.passage.loc.yars.internal.api.FetchedData;

/**
 * FIXME doc
 * 
 * @since 0.1
 */
@SuppressWarnings("restriction")
final class CustomersFetch implements FetchedData<CustomerStorage, ProductCustomer> {

	private final CustomerStorage source;
	private final Set<String> products;

	public CustomersFetch(CustomerStorage source, Set<String> products) {
		this.source = source;
		this.products = products;
	}

	@Override
	public List<ProductCustomer> get() {
		return source.forProducts(products).stream()//
				.map(user -> new ProductCustomer(user.getFullName(), user.getEmail()))//
				.collect(Collectors.toList());
	}

}
