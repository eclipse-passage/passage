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
package org.eclipse.passage.lic.internal.products.migration;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.passage.lic.emf.ecore.util.DelegatingEPackage;
import org.eclipse.passage.lic.model.meta.LicPackage;
import org.eclipse.passage.lic.products.model.meta.ProductsPackage;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@Component
public class ProductsMigrator {

	@Activate
	public void activate() {
		LicPackage.eINSTANCE.toString();// init existing
		String nsUri = "http://www.eclipse.org/passage/lic/0.3.3"; //$NON-NLS-1$
		ProductsPackage delegate = ProductsPackage.eINSTANCE;
		List<String> classifiers = new ArrayList<>();
		classifiers.add(delegate.getProductLine().getName());
		classifiers.add(delegate.getProduct().getName());
		classifiers.add(delegate.getProductVersion().getName());
		classifiers.add(delegate.getProductVersionFeature().getName());
		DelegatingEPackage.delegate(nsUri, delegate, classifiers);
	}

}
