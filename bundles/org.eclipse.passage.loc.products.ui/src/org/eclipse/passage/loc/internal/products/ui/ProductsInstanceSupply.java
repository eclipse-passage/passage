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
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.internal.products.ui;

import java.util.Optional;

import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.lic.products.model.api.Product;
import org.eclipse.passage.lic.products.model.api.ProductLine;
import org.eclipse.passage.loc.internal.api.ClassSupply;
import org.eclipse.passage.loc.internal.api.InstanceSupply;
import org.osgi.service.component.annotations.Component;

@Component
public final class ProductsInstanceSupply implements ClassSupply {

	@Override
	public Optional<InstanceSupply<?>> find(Class<?> clazz, MandatoryService context) {
		if (ProductLine.class.isAssignableFrom(clazz)) {
			return Optional.of(new SupplyProductLine(context));
		}
		if (Product.class.isAssignableFrom(clazz)) {
			return Optional.of(new SupplyProduct(context));
		}
		return Optional.empty();
	}

}
