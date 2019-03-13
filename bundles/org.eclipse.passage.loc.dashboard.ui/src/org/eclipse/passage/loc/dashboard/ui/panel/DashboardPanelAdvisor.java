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
package org.eclipse.passage.loc.dashboard.ui.panel;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.passage.lic.registry.features.FeaturesRegistry;
import org.eclipse.passage.lic.registry.licenses.LicensesRegistry;
import org.eclipse.passage.lic.registry.products.ProductsRegistry;
import org.eclipse.passage.lic.registry.users.UsersRegistry;
import org.eclipse.swt.widgets.Composite;

public interface DashboardPanelAdvisor {

	void init(IEclipseContext context);

	void createHeaderInfo(Composite parent);

	void createFeatureInfo(Composite parent, FeaturesRegistry featureRegistry);

	void updateFeatureInfo(FeaturesRegistry featureRegistry);
	
	void createProductInfo(Composite parent, ProductsRegistry productRegistry);

	void updateProductInfo(ProductsRegistry productRegistry);
	
	void createUserInfo(Composite parent, UsersRegistry userRegistry);
	
	void updateUserInfo(UsersRegistry userRegistry);
	
	void createLicenseInfo(Composite parent, LicensesRegistry licenseRegistry);
	
	void updateLicenseInfo(LicensesRegistry licenseRegistry);

	void createFooterInfo(Composite parent);

	void dispose(IEclipseContext context);

}
