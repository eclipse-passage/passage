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
package org.eclipse.passage.lic.internal.products.e4.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.products.edit.ProductsEditPlugin;
import org.eclipse.passage.lic.products.model.meta.ProductsPackage;
import org.eclipse.swt.widgets.Display;

public class ProductsE4UiProcessor {

	@Execute
	void process(IEclipseContext context) {
		Display display = context.get(Display.class);
		if (display != null) {
			doRegisterImages();
		}
	}

	private void doRegisterImages() {
		LicensingImages.getImageRegistry();
		String pattern = "$nl$/icons/full/obj16/%s"; //$NON-NLS-1$

		registerProducts(pattern);
	}

	private void registerProducts(String pattern) {
		Map<String, String> paths = new HashMap<String, String>();
		ProductsPackage products = ProductsPackage.eINSTANCE;
		paths.put(products.getName(), String.format(pattern, "product.png")); //$NON-NLS-1$
		paths.put(products.getProductLine().getName(), String.format(pattern, "product.png")); //$NON-NLS-1$
		paths.put(products.getProduct().getName(), String.format(pattern, "product.png")); //$NON-NLS-1$
		paths.put(products.getProductVersion().getName(), String.format(pattern, "product.png")); //$NON-NLS-1$
		paths.put(products.getProductVersionFeature().getName(), String.format(pattern, "product.png")); //$NON-NLS-1$
		LicensingImages.declareImages(ProductsEditPlugin.class, paths);
	}

}
