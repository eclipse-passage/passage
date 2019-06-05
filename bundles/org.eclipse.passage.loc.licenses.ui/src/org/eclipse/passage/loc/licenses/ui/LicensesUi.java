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
package org.eclipse.passage.loc.licenses.ui;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.passage.lic.emf.edit.ComposedAdapterFactoryProvider;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.lic.licenses.registry.LicenseRegistry;
import org.eclipse.passage.loc.internal.licenses.ui.i18n.LicensesUiMessages;
import org.eclipse.passage.loc.workbench.LocWokbench;
import org.eclipse.swt.widgets.Shell;

public class LicensesUi {

	public static final String BUNDLE_SYMBOLIC_NAME = "org.eclipse.passage.loc.licenses.ui"; //$NON-NLS-1$

	public static final String PERSPECTIVE_MAIN = BUNDLE_SYMBOLIC_NAME + '.' + "perspective.main"; //$NON-NLS-1$

	public static LicensePlanDescriptor selectLicensePlanDescriptor(Shell shell, ComposedAdapterFactoryProvider provider,
			LicenseRegistry registry, LicensePlanDescriptor initial) {
		String classifier = LicensesPackage.eINSTANCE.getLicensePlan().getName();
		String title = LicensesUiMessages.LicensesUi_select_license_plan;
		Iterable<? extends LicensePlanDescriptor> input = registry.getLicensePlans();
		Class<LicensePlanDescriptor> clazz = LicensePlanDescriptor.class;
		ComposedAdapterFactory factory = provider.getComposedAdapterFactory();
		return LocWokbench.selectClassifier(shell, factory, classifier, title, input, initial, clazz);
	}

}
