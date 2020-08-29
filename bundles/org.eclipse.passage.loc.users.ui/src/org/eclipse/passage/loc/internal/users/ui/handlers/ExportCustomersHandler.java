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
package org.eclipse.passage.loc.internal.users.ui.handlers;

import java.util.Optional;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.loc.internal.products.ProductRegistry;
import org.eclipse.passage.loc.internal.users.ui.i18n.UsersUiMessages;
import org.eclipse.passage.loc.report.internal.core.user.CustomerExportService;
import org.eclipse.passage.loc.report.internal.core.user.CustomerStorage;
import org.eclipse.passage.loc.report.internal.ui.jface.user.ExposedExportCustomersWizard;
import org.eclipse.swt.widgets.Shell;

public class ExportCustomersHandler {

	@Execute
	public void execute(IEclipseContext context) {
		Optional<ProductRegistry> products = service(ProductRegistry.class, context);
		if (!products.isPresent()) {
			return;
		}
		Optional<CustomerStorage> customers = service(CustomerStorage.class, context);
		if (!customers.isPresent()) {
			return;
		}
		Optional<CustomerExportService> export = service(CustomerExportService.class, context);
		if (!export.isPresent()) {
			return;
		}
		new ExposedExportCustomersWizard(//
				products.get(), //
				customers.get(), //
				export.get()//
		).accept(context.get(Shell.class));
	}

	private <S> Optional<S> service(Class<S> service, IEclipseContext context) {
		Optional<S> implementation = Optional.ofNullable(context.get(service));
		if (!implementation.isPresent()) {
			MessageDialog.openError(//
					context.get(Shell.class), //
					UsersUiMessages.ExportCustomersHandler_unavailableTitle, //
					NLS.bind(UsersUiMessages.ExportCustomersHandler_unavailableMessage, service.getName())//
			);
		}
		return implementation;
	}

}
