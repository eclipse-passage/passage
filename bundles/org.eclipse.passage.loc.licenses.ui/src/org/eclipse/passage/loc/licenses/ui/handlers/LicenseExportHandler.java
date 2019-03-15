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
package org.eclipse.passage.loc.licenses.ui.handlers;

import javax.inject.Named;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;
import org.eclipse.passage.loc.runtime.LicenseOperatorService;
import org.eclipse.swt.widgets.Shell;

public class LicenseExportHandler {

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) LicensePackDescriptor licensePack,
			IEclipseContext context) {
		LicenseOperatorService licenseService = context.get(LicenseOperatorService.class);
		Shell shell = context.get(Shell.class);
		IStatus status = licenseService.issueLicensePack(licensePack);
		if (status.isOK()) {
			MessageDialog.openInformation(shell, "License Pack Issued", status.getMessage());
		} else {
			ErrorDialog.openError(shell, "Error", "Error during license pack export", status);
		}
	}

	@CanExecute
	public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) @Optional LicensePackDescriptor licensePack,
			IEclipseContext context) {
		LicenseOperatorService licenseService = context.get(LicenseOperatorService.class);
		if (licenseService == null) {
			return false;
		}
		return licensePack != null;
	}

}