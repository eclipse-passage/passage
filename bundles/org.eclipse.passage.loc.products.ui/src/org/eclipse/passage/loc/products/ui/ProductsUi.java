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
package org.eclipse.passage.loc.products.ui;

import org.eclipse.passage.lic.emf.edit.ProductDomainRegistry;
import org.eclipse.passage.lic.jface.LicensingImages;
import org.eclipse.passage.lic.model.meta.LicPackage;
import org.eclipse.passage.lic.runtime.products.ProductDescriptor;
import org.eclipse.passage.lic.runtime.products.ProductVersionDescriptor;
import org.eclipse.passage.loc.workbench.LocWokbench;
import org.eclipse.swt.widgets.Shell;

public class ProductsUi {

	public static final String BUNDLE_SYMBOLIC_NAME = "org.eclipse.passage.loc.products.ui"; //$NON-NLS-1$

	public static final String PERSPECTIVE_MAIN = BUNDLE_SYMBOLIC_NAME + '.' + "perspective.main"; //$NON-NLS-1$

	public static ProductDescriptor selectProductDescriptor(Shell shell, LicensingImages images, ProductDomainRegistry registry, ProductDescriptor initial) {
		String classifier = LicPackage.eINSTANCE.getProduct().getName();
		String title = "Select Product";
		Iterable<? extends ProductDescriptor> input = registry.getProducts();
		Class<ProductDescriptor> clazz = ProductDescriptor.class;
		return LocWokbench.selectClassifier(shell, images, registry, classifier, title, input, initial, clazz);
	}

	public static ProductVersionDescriptor selectProductVersionDescriptor(Shell shell, LicensingImages images,
			ProductDomainRegistry registry, ProductVersionDescriptor initial) {
		String classifier = LicPackage.eINSTANCE.getProductVersion().getName();
		String title = "Select Product Version";
		Iterable<? extends ProductVersionDescriptor> input = registry.getProductVersions();
		Class<ProductVersionDescriptor> clazz = ProductVersionDescriptor.class;
		return LocWokbench.selectClassifier(shell, images, registry, classifier, title, input, initial, clazz);
	}

}
