/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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
package org.eclipse.passage.lic.runtime.products;

import org.eclipse.passage.lic.runtime.registry.DescriptorRegistry;

public interface ProductRegistry extends DescriptorRegistry {

	Iterable<ProductLineDescriptor> getProductLines();

	ProductLineDescriptor getProductLine(String productLineId);

	Iterable<? extends ProductDescriptor> getProducts();

	Iterable<? extends ProductDescriptor> getProducts(String productLineId);
	
	ProductDescriptor getProduct(String productId);

	Iterable<? extends ProductVersionDescriptor> getProductVersions();

	Iterable<? extends ProductVersionDescriptor> getProductVersions(String productId);

	ProductVersionDescriptor getProductVersion(String productId, String version);

	Iterable<? extends ProductVersionFeatureDescriptor> getProductVersionFeatures();

	Iterable<? extends ProductVersionFeatureDescriptor> getProductVersionFeatures(String productId, String version);

}
