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
package org.eclipse.passage.loc.products.ui.handlers;

import javax.inject.Named;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.passage.lic.runtime.products.ProductVersionDescriptor;
import org.eclipse.passage.loc.runtime.ProductOperatorService;
import org.eclipse.swt.widgets.Shell;

public class ProductExportHandler {

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) ProductVersionDescriptor productVersion,
			IEclipseContext context) {
		ProductOperatorService service = context.get(ProductOperatorService.class);
		IStatus status = service.createProductKeys(productVersion);
		Shell shell = context.get(Shell.class);
		if (status.isOK()) {
			String message = status.getMessage();
			MessageDialog.openInformation(shell, "Product Key Export", message);
		} else {
			ErrorDialog.openError(shell, "Error", "Error during product key export", status);
		}
	}

	@CanExecute
	public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) @Optional ProductVersionDescriptor productVersion) {
		return productVersion != null;
	}

}