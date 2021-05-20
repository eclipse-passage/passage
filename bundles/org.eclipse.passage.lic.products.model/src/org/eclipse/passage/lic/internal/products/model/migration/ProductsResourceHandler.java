/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.products.model.migration;

import java.util.Collections;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.passage.lic.emf.migration.DelegateClassifiers;
import org.eclipse.passage.lic.emf.migration.EClassRoutes;
import org.eclipse.passage.lic.emf.migration.EFeatureRoutes;
import org.eclipse.passage.lic.emf.migration.EnsureStructure;
import org.eclipse.passage.lic.emf.migration.SimpleClassRoutes;
import org.eclipse.passage.lic.emf.migration.SimpleFeatureRoutes;
import org.eclipse.passage.lic.emf.xmi.MigratingResourceHandler;
import org.eclipse.passage.lic.products.model.meta.ProductsPackage;

public final class ProductsResourceHandler extends MigratingResourceHandler {

	@Override
	protected void ensureMigrations() {
		migrate030();
		migrate040();
	}

	@Override
	protected EFeatureRoutes attributes() {
		return new SimpleFeatureRoutes();
	}

	@Override
	protected EnsureStructure structures() {
		// not yet needed
		return e -> Collections.emptyList();
	}

	private void migrate030() {
		String uri = "http://www.eclipse.org/passage/lic/0.3.3"; //$NON-NLS-1$
		ProductsPackage delegate = ProductsPackage.eINSTANCE;
		EClassRoutes.Smart smart = new EClassRoutes.Smart(new SimpleClassRoutes());
		smart.define(delegate.getProductLine());
		smart.define(delegate.getProduct());
		smart.define(delegate.getProductVersion());
		smart.define(delegate.getProductVersionFeature());
		new DelegateClassifiers(uri).delegate(smart);
	}

	private void migrate040() {
		String uri = "http://www.eclipse.org/passage/lic/products/0.4.0"; //$NON-NLS-1$
		ProductsPackage delegate = ProductsPackage.eINSTANCE;
		EPackage.Registry.INSTANCE.computeIfAbsent(uri, ns -> delegate);
	}

}
