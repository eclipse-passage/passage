/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.products.migration;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.passage.lic.emf.ecore.util.DelegatingEPackage;
import org.eclipse.passage.lic.products.model.meta.ProductsPackage;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@Component
public class ProductsMigrator {

	@Activate
	public void activate() {
		migrate030();
		migrate040();
	}

	private void migrate030() {
		String nsUri = "http://www.eclipse.org/passage/lic/0.3.3"; //$NON-NLS-1$
		ProductsPackage delegate = ProductsPackage.eINSTANCE;
		List<String> classifiers = new ArrayList<>();
		classifiers.add(delegate.getProductLine().getName());
		classifiers.add(delegate.getProduct().getName());
		classifiers.add(delegate.getProductVersion().getName());
		classifiers.add(delegate.getProductVersionFeature().getName());
		DelegatingEPackage.delegate(nsUri, delegate, classifiers);
	}

	private void migrate040() {
		String nsUri = "http://www.eclipse.org/passage/lic/products/0.4.0"; //$NON-NLS-1$
		ProductsPackage delegate = ProductsPackage.eINSTANCE;
		EPackage.Registry.INSTANCE.put(nsUri, delegate);
	}

}
