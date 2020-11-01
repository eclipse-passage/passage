/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.loc.dashboard.ui.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.loc.dashboard.ui.wizards.floating.FloatingDataPack;
import org.eclipse.passage.loc.dashboard.ui.wizards.floating.IssueFloatingLicenseWizard;
import org.eclipse.passage.loc.internal.api.OperatorLicenseService;
import org.eclipse.swt.widgets.Shell;

public class DashboardIssueFloatingLicenseHandler {

	@Execute
	public void execute(IEclipseContext context,
			@Named(IServiceConstants.ACTIVE_SELECTION) @Optional LicensePlanDescriptor plan,
			@Named(IServiceConstants.ACTIVE_SELECTION) @Optional UserDescriptor user,
			@Named(IServiceConstants.ACTIVE_SELECTION) @Optional ProductVersionDescriptor product) {
		open(context, //
				new IssueFloatingLicenseWizard(//
						context, //
						new FloatingDataPack(//
								java.util.Optional.ofNullable(plan), //
								java.util.Optional.ofNullable(user), //
								java.util.Optional.ofNullable(product))) //
		);
	}

	private void open(IEclipseContext context, Wizard wizard) {
		WizardDialog dialog = new WizardDialog(context.get(Shell.class), wizard);
		dialog.create();
		dialog.getShell().setImage(LicensingImages.getImage(LicensesPackage.eINSTANCE.getLicensePack().getName()));
		dialog.getShell().setSize(Math.max(500, dialog.getShell().getSize().x), 500);
		dialog.open();
	}

	@CanExecute
	public boolean canExecute(IEclipseContext context) {
		return context.get(OperatorLicenseService.class) != null;
	}

}
