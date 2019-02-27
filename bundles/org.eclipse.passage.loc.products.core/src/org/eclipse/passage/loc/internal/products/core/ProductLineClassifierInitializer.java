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
package org.eclipse.passage.loc.internal.products.core;

import org.eclipse.passage.lic.emf.edit.ClassifierInitializer;
import org.eclipse.passage.lic.emf.edit.DomainRegistryAccess;
import org.eclipse.passage.lic.registry.ProductsRegistry;
import org.osgi.service.component.annotations.Component;

@Component(property = { DomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + ProductsRegistry.DOMAIN_NAME })
public final class ProductLineClassifierInitializer implements ClassifierInitializer {
	@Override
	public String newObjectIdentifier() {
		return "new.product.line"; //$NON-NLS-1$ ;
	}

	@Override
	public String newObjectName() {
		return "New Product Line";
	}

	@Override
	public String newFileName() {
		return "new_product_line"; //$NON-NLS-1$ ;
	}

	@Override
	public String newObjectTitle() {
		return "Product Line";
	}

	@Override
	public String newObjectMessage() {
		return "New Product Line";
	}

	@Override
	public String newFileMessage() {
		return "Please specify a file name to store product data";
	}
}