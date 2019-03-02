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
import org.eclipse.passage.lic.emf.edit.FeatureDomainRegistry;
import org.eclipse.passage.lic.emf.edit.LicenseDomainRegistry;
import org.eclipse.passage.lic.emf.edit.ProductDomainRegistry;
import org.eclipse.passage.lic.emf.edit.UserDomainRegistry;
import org.eclipse.swt.widgets.Composite;

public interface DashboardPanelAdvisor {

	void init(IEclipseContext context);

	void createHeaderInfo(Composite parent);

	void createFeatureInfo(Composite parent, FeatureDomainRegistry featureRegistry);

	void updateFeatureInfo(FeatureDomainRegistry featureRegistry);
	
	void createProductInfo(Composite parent, ProductDomainRegistry productRegistry);

	void updateProductInfo(ProductDomainRegistry productRegistry);
	
	void createUserInfo(Composite parent, UserDomainRegistry userRegistry);
	
	void updateUserInfo(UserDomainRegistry userRegistry);
	
	void createLicenseInfo(Composite parent, LicenseDomainRegistry licenseRegistry);
	
	void updateLicenseInfo(LicenseDomainRegistry licenseRegistry);

	void createFooterInfo(Composite parent);

	void dispose(IEclipseContext context);

}
