/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
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
package org.eclipse.passage.loc.dashboard.ui.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.loc.api.OperatorLicenseService;
import org.eclipse.passage.loc.dashboard.ui.wizards.IssueLicenseWizard;
import org.eclipse.swt.widgets.Shell;

public class DashboardIssueLicenseHandler {

	@Execute
	public void execute(IEclipseContext context,
			@Named(IServiceConstants.ACTIVE_SELECTION) @Optional LicensePlanDescriptor licensePlan,
			@Named(IServiceConstants.ACTIVE_SELECTION) @Optional UserDescriptor user,
			@Named(IServiceConstants.ACTIVE_SELECTION) @Optional ProductVersionDescriptor productVersion) {
		Shell shell = context.get(Shell.class);
		IssueLicenseWizard wizard = new IssueLicenseWizard(context);
		wizard.init(licensePlan, user, productVersion);
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.create();

		Shell createdShell = dialog.getShell();
		createdShell.setImage(LicensingImages.getImage(LicensesPackage.eINSTANCE.getLicensePack().getName()));
		dialog.getShell().setSize(Math.max(500, dialog.getShell().getSize().x), 500);
		dialog.open();
	}

	@CanExecute
	public boolean canExecute(IEclipseContext context) {
		return context.get(OperatorLicenseService.class) != null;
	}

}
