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
 *      ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.report.internal.ui.jface.user;

import java.util.function.Function;

import org.eclipse.passage.lic.products.ProductDescriptor;
import org.eclipse.passage.lic.products.registry.ProductRegistry;

final class DescribedProduct implements Function<String, ProductDescriptor> {

	private final ProductRegistry registry;

	public DescribedProduct(ProductRegistry registry) {
		this.registry = registry;
	}

	@Override
	public ProductDescriptor apply(String identifier) {
		return registry.getProduct(identifier);
	}

}
