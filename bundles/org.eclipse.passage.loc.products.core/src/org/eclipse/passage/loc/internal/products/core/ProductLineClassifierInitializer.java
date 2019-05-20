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
import org.eclipse.passage.lic.emf.edit.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.products.core.Products;
import org.osgi.service.component.annotations.Component;

@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + Products.DOMAIN_NAME })
public final class ProductLineClassifierInitializer implements ClassifierInitializer {
	@Override
	public String newObjectIdentifier() {
		return "new.product.line"; //$NON-NLS-1$ ;
	}

	@Override
	public String newObjectName() {
		return ProductsCoreMessages.ProductLineClassifierInitializer_object_name;
	}

	@Override
	public String newFileName() {
		return "new_product_line"; //$NON-NLS-1$ ;
	}

	@Override
	public String newObjectTitle() {
		return ProductsCoreMessages.ProductLineClassifierInitializer_object_title;
	}

	@Override
	public String newObjectMessage() {
		return ProductsCoreMessages.ProductLineClassifierInitializer_object_message;
	}

	@Override
	public String newFileMessage() {
		return ProductsCoreMessages.ProductLineClassifierInitializer_file_message;
	}
}