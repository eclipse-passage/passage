/*******************************************************************************
 * Copyright (c) 2018, 2024 ArSysOp
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
package org.eclipse.passage.loc.products.ui;

import java.util.Collection;
import java.util.Optional;

import org.eclipse.passage.lic.products.model.api.Product;
import org.eclipse.passage.lic.products.model.api.ProductVersion;
import org.eclipse.passage.lic.products.model.meta.ProductsPackage;
import org.eclipse.passage.loc.internal.products.ProductRegistry;
import org.eclipse.passage.loc.internal.products.ui.i18n.ProductsUiMessages;
import org.eclipse.passage.loc.workbench.LocWokbench;
import org.eclipse.swt.widgets.Shell;

public class ProductsUi {

	public static final String BUNDLE_SYMBOLIC_NAME = "org.eclipse.passage.loc.products.ui"; //$NON-NLS-1$

	public static final String PERSPECTIVE_MAIN = BUNDLE_SYMBOLIC_NAME + '.' + "perspective.main"; //$NON-NLS-1$

	public static Product selectProduct(Shell shell, ProductRegistry registry, Optional<Product> initial) {
		return LocWokbench.selectClassifier(shell, //
				ProductsPackage.eINSTANCE.getProduct().getName(), //
				ProductsUiMessages.ProductsUi_select_product, //
				registry.products(), //
				initial, //
				Product.class);
	}

	public static ProductVersion selectProductVersion(Shell shell, ProductRegistry registry) {
		String classifier = ProductsPackage.eINSTANCE.getProductVersion().getName();
		String title = ProductsUiMessages.ProductsUi_select_product_line;
		Collection<ProductVersion> input = registry.productVersions();
		Class<ProductVersion> clazz = ProductVersion.class;
		return LocWokbench.selectClassifier(shell, //
				classifier, //
				title, //
				input, //
				Optional.empty(), //
				clazz);
	}

}
