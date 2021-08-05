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
import org.eclipse.passage.loc.internal.agreements.AgreementRegistry;
import org.eclipse.passage.loc.internal.features.FeatureRegistry;
import org.eclipse.passage.loc.internal.licenses.LicenseRegistry;
import org.eclipse.passage.loc.internal.products.ProductRegistry;
import org.eclipse.passage.loc.internal.users.UserRegistry;
import org.eclipse.swt.widgets.Composite;

public interface DashboardPanelAdvisor {

	void init(IEclipseContext context);

	void createHeaderInfo(Composite parent);

	void createFeatureInfo(Composite parent, FeatureRegistry registry);

	void updateFeatureInfo(FeatureRegistry registry);

	void createProductInfo(Composite parent, ProductRegistry registry);

	void updateProductInfo(ProductRegistry registry);

	void createUserInfo(Composite parent, UserRegistry registry);

	void updateUserInfo(UserRegistry registry);

	void createLicenseInfo(Composite parent, LicenseRegistry registry);

	void updateLicenseInfo(LicenseRegistry registry);

	void createAgreementInfo(Composite parent, AgreementRegistry registry);

	void updateAgreementInfo(AgreementRegistry registry);

	void createFooterInfo(Composite parent);

	void dispose(IEclipseContext context);

}
