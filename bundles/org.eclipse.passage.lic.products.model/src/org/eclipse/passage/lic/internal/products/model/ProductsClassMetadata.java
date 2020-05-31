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
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.internal.products.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.passage.lic.emf.meta.ClassMetadata;
import org.eclipse.passage.lic.emf.meta.EntityMetadata;
import org.eclipse.passage.lic.emf.meta.PlainEntityMetadata;
import org.eclipse.passage.lic.products.ProductDescriptor;
import org.eclipse.passage.lic.products.ProductLineDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.products.model.api.Product;
import org.eclipse.passage.lic.products.model.api.ProductLine;
import org.eclipse.passage.lic.products.model.api.ProductVersion;
import org.eclipse.passage.lic.products.model.meta.ProductsPackage;

public final class ProductsClassMetadata implements ClassMetadata {

	private final ProductsPackage meta;
	private final Map<Class<?>, EntityMetadata> map;

	public ProductsClassMetadata() {
		meta = ProductsPackage.eINSTANCE;
		map = new HashMap<Class<?>, EntityMetadata>();
		map.put(ProductLineDescriptor.class, //
				new PlainEntityMetadata(//
						meta.getProductLine(), //
						meta.getProductLine_Identifier(), //
						meta.getProductLine_Name()));
		map.put(ProductLine.class, map.get(ProductLineDescriptor.class));
		map.put(ProductDescriptor.class, //
				new PlainEntityMetadata(//
						meta.getProduct(), //
						meta.getProduct_Identifier(), //
						meta.getProduct_Name()));
		map.put(Product.class, map.get(ProductDescriptor.class));
		map.put(ProductVersionDescriptor.class, //
				new PlainEntityMetadata(//
						meta.getProductVersion(), //
						meta.getProductVersion_Version(), //
						meta.getProductVersion_Name()));
		map.put(ProductVersion.class, map.get(ProductVersionDescriptor.class));
	}

	@Override
	public Optional<EntityMetadata> find(Class<?> clazz) {
		return Optional.ofNullable(map.get(clazz));
	}

}
