/*******************************************************************************
 * Copyright (c) 2020, 2024 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *      ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.report.internal.ui.jface.user;

import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.products.model.api.Product;
import org.eclipse.passage.loc.internal.products.ProductRegistry;
import org.eclipse.passage.loc.report.internal.core.user.CustomerStorage;

final class Products implements Supplier<Product[]> {

	private final CustomerStorage customers;
	private final ProductRegistry products;

	public Products(ProductRegistry products, CustomerStorage customers) {
		this.products = products;
		this.customers = customers;
	}

	@Override
	public Product[] get() {
		return customers.products().stream() //
				.map(products::product) //
				.collect(Collectors.toSet())//
				.toArray(new Product[0]);
	}

}
