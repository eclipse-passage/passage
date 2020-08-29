/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.loc.internal.products;

import org.eclipse.passage.lic.products.ProductDescriptor;
import org.eclipse.passage.lic.products.ProductLineDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.products.ProductVersionFeatureDescriptor;

public interface ProductRegistry {

	Iterable<? extends ProductLineDescriptor> getProductLines();

	ProductLineDescriptor getProductLine(String productLineId);

	void registerProductLine(ProductLineDescriptor productLine);

	void unregisterProductLine(String productLineId);

	Iterable<? extends ProductDescriptor> getProducts();

	Iterable<? extends ProductDescriptor> getProducts(String productLineId);

	ProductDescriptor getProduct(String productId);

	void registerProduct(ProductDescriptor product);

	void unregisterProduct(String productId);

	Iterable<? extends ProductVersionDescriptor> getProductVersions();

	Iterable<? extends ProductVersionDescriptor> getProductVersions(String productId);

	ProductVersionDescriptor getProductVersion(String productId, String version);

	void registerProductVersion(ProductDescriptor product, ProductVersionDescriptor productVersion);

	void unregisterProductVersion(String productId, String version);

	Iterable<? extends ProductVersionFeatureDescriptor> getProductVersionFeatures();

	Iterable<? extends ProductVersionFeatureDescriptor> getProductVersionFeatures(String productId, String version);

	void registerProductVersionFeature(ProductDescriptor product, ProductVersionDescriptor productVersion,
			ProductVersionFeatureDescriptor productVersionFeature);

	void unregisterProductVersionFeature(String productId, String version, String featureId);

}
