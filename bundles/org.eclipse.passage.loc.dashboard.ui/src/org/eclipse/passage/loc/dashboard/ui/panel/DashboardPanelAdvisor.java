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
package org.eclipse.passage.loc.dashboard.ui.panel;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.passage.lic.licenses.registry.LicenseRegistry;
import org.eclipse.passage.lic.products.registry.ProductRegistry;
import org.eclipse.passage.lic.users.registry.UserRegistry;
import org.eclipse.passage.loc.internal.features.FeatureRegistry;
import org.eclipse.swt.widgets.Composite;

public interface DashboardPanelAdvisor {

	void init(IEclipseContext context);

	void createHeaderInfo(Composite parent);

	void createFeatureInfo(Composite parent, FeatureRegistry featureRegistry);

	void updateFeatureInfo(FeatureRegistry featureRegistry);

	void createProductInfo(Composite parent, ProductRegistry productRegistry);

	void updateProductInfo(ProductRegistry productRegistry);

	void createUserInfo(Composite parent, UserRegistry userRegistry);

	void updateUserInfo(UserRegistry userRegistry);

	void createLicenseInfo(Composite parent, LicenseRegistry licenseRegistry);

	void updateLicenseInfo(LicenseRegistry licenseRegistry);

	void createFooterInfo(Composite parent);

	void dispose(IEclipseContext context);

}
