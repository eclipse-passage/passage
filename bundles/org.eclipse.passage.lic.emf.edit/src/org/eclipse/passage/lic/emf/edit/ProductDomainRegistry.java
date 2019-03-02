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
package org.eclipse.passage.lic.emf.edit;

import org.eclipse.passage.lic.runtime.products.ProductDescriptor;
import org.eclipse.passage.lic.runtime.products.ProductLineDescriptor;
import org.eclipse.passage.lic.runtime.products.ProductRegistry;
import org.eclipse.passage.lic.runtime.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.runtime.products.ProductVersionFeatureDescriptor;
import org.eclipse.passage.lic.runtime.registry.DescriptorRegistry;

public interface ProductDomainRegistry extends ProductRegistry, EditingDomainRegistry, DescriptorRegistry {

	String createPassword(ProductVersionDescriptor productVersion);

	void registerProductLine(ProductLineDescriptor productLine);

	void registerProduct(ProductDescriptor product);

	void registerProductVersion(ProductDescriptor product, ProductVersionDescriptor productVersion);

	void registerProductVersionFeature(ProductDescriptor product, ProductVersionDescriptor productVersion,
			ProductVersionFeatureDescriptor productVersionFeature);

	void unregisterProductLine(String productLineId);

	void unregisterProduct(String productId);

	void unregisterProductVersion(String productId, String version);

	void unregisterProductVersionFeature(String productId, String version, String featureId);

}
