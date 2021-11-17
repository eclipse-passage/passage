/*******************************************************************************
 * Copyright (c) 2019, 2021 ArSysOp
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
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.passage.lic.jface.actions.LicensedRunnableUI;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.loc.dashboard.ui.wizards.IssueLicenseWizard;
import org.eclipse.passage.loc.dashboard.ui.wizards.PersonalDataPack;
import org.eclipse.passage.loc.internal.api.OperatorLicenseService;
import org.eclipse.swt.widgets.Shell;

public class DashboardIssueLicenseHandler {

	private final String feature = "org.eclipse.passage.loc.operator.issue.personal"; //$NON-NLS-1$

	@Execute
	public void execute(IEclipseContext context,
			@Named(IServiceConstants.ACTIVE_SELECTION) @Optional LicensePlanDescriptor plan,
			@Named(IServiceConstants.ACTIVE_SELECTION) @Optional UserDescriptor user,
			@Named(IServiceConstants.ACTIVE_SELECTION) @Optional ProductVersionDescriptor product) {
		Shell shell = context.get(Shell.class);
		new LicensedRunnableUI(() -> shell, feature, () -> startWizard(shell, context, plan, user, product)).run();
	}

	private void startWizard(Shell shell, IEclipseContext context, LicensePlanDescriptor plan, UserDescriptor user,
			ProductVersionDescriptor product) {
		IssueLicenseWizard wizard = new IssueLicenseWizard(context, new PersonalDataPack(//
				java.util.Optional.ofNullable(plan), //
				java.util.Optional.ofNullable(user), //
				java.util.Optional.ofNullable(product)//
		));
		open(shell, wizard);
	}

	private void open(Shell shell, IssueLicenseWizard wizard) {
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.create();
		shape(dialog);
		dialog.open();
	}

	private void shape(WizardDialog dialog) {
		Shell shell = dialog.getShell();
		shell.setImage(LicensingImages.getImage(LicensesPackage.eINSTANCE.getPersonalLicensePack().getName()));
		shell.setSize(Math.max(1000, shell.getSize().x), Math.max(800, shell.getSize().y));
	}

	@CanExecute
	public boolean canExecute(IEclipseContext context) {
		return context.get(OperatorLicenseService.class) != null;
	}

}
