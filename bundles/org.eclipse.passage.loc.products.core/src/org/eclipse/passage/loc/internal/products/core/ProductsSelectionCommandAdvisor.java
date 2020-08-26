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
package org.eclipse.passage.loc.internal.products.core;

import java.util.Collections;

import org.eclipse.passage.lic.products.model.meta.ProductsPackage;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.internal.emf.SelectionCommandAdvisor;
import org.eclipse.passage.loc.internal.products.ProductRegistry;
import org.eclipse.passage.loc.internal.products.core.i18n.ProductsCoreMessages;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + ProductsPackage.eNAME })
public class ProductsSelectionCommandAdvisor implements SelectionCommandAdvisor {

	private ProductRegistry productRegistry;

	@Reference
	public void bindDomainRegistry(ProductRegistry registry) {
		this.productRegistry = registry;
	}

	public void unbindDomainRegistry(ProductRegistry registry) {
		if (this.productRegistry == registry) {
			this.productRegistry = null;
		}
	}

	@Override
	public String getSelectionTitle(String classifier) {
		if (ProductsPackage.eINSTANCE.getProductLine().getName().equals(classifier)) {
			return ProductsCoreMessages.ProductsSelectionCommandAdvisor_select_product_line;
		}
		if (ProductsPackage.eINSTANCE.getProduct().getName().equals(classifier)) {
			return ProductsCoreMessages.ProductsSelectionCommandAdvisor_select_product;
		}
		if (ProductsPackage.eINSTANCE.getProductVersion().getName().equals(classifier)) {
			return ProductsCoreMessages.ProductsSelectionCommandAdvisor_select_product_version;
		}
		if (ProductsPackage.eINSTANCE.getProductVersionFeature().getName().equals(classifier)) {
			return ProductsCoreMessages.ProductsSelectionCommandAdvisor_select_product_version_feature;
		}
		return null;
	}

	@Override
	public Iterable<?> getSelectionInput(String classifier) {
		if (productRegistry == null) {
			return Collections.emptyList();
		}
		if (ProductsPackage.eINSTANCE.getProductLine().getName().equals(classifier)) {
			return productRegistry.getProductLines();
		}
		if (ProductsPackage.eINSTANCE.getProduct().getName().equals(classifier)) {
			return productRegistry.getProducts();
		}
		if (ProductsPackage.eINSTANCE.getProductVersion().getName().equals(classifier)) {
			return productRegistry.getProductVersions();
		}
		if (ProductsPackage.eINSTANCE.getProductVersionFeature().getName().equals(classifier)) {
			return productRegistry.getProductVersionFeatures();
		}
		return Collections.emptyList();
	}

}
