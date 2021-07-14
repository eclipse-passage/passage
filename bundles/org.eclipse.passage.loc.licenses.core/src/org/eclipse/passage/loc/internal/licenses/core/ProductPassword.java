/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.loc.internal.licenses.core;

import java.util.function.Function;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.loc.internal.api.OperatorProductService;
import org.eclipse.passage.loc.internal.products.ProductRegistry;

final class ProductPassword implements Function<LicensedProduct, String> {

	private final ProductRegistry products;
	private final OperatorProductService operator;

	public ProductPassword(ProductRegistry products, OperatorProductService operator) {
		this.products = products;
		this.operator = operator;
	}

	@Override
	public String apply(LicensedProduct product) {
		return operator.createPassword(//
				products.getProductVersion(//
						product.identifier(), //
						product.version()));
	}

}
