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
package org.eclipse.passage.loc.edit;

import org.eclipse.passage.lic.emf.edit.EditingDomainRegistry;
import org.eclipse.passage.lic.model.api.Product;
import org.eclipse.passage.lic.model.api.ProductLine;
import org.eclipse.passage.lic.model.api.ProductVersion;
import org.eclipse.passage.lic.model.api.ProductVersionFeature;
import org.eclipse.passage.lic.registry.DescriptorRegistry;
import org.eclipse.passage.lic.registry.ProductRegistry;
import org.eclipse.passage.lic.registry.ProductVersionDescriptor;

public interface ProductDomainRegistry extends ProductRegistry, EditingDomainRegistry, DescriptorRegistry {

	String createPassword(ProductVersionDescriptor productVersion);

	void registerProductLine(ProductLine productLine);

	void registerProduct(Product product);

	void registerProductVersion(Product product, ProductVersion productVersion);

	void registerProductVersionFeature(Product product, ProductVersion productVersion,
			ProductVersionFeature productVersionFeature);

	void unregisterProductLine(String productLineId);

	void unregisterProduct(String productId);

	void unregisterProductVersion(String productId, String version);

	void unregisterProductVersionFeature(String productId, String version, String featureId);

}
