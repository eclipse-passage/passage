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
 *      ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.report.internal.ui.jface.user;

import java.util.function.Consumer;

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.passage.loc.internal.products.ProductRegistry;
import org.eclipse.passage.loc.report.internal.core.user.CustomerExportService;
import org.eclipse.passage.loc.report.internal.core.user.CustomerStorage;
import org.eclipse.swt.widgets.Shell;

/**
 * @since 0.2
 */
public final class ExposedExportCustomersWizard implements Consumer<Shell> {

	private final ProductRegistry products;
	private final CustomerStorage customers;
	private final CustomerExportService export;

	public ExposedExportCustomersWizard(ProductRegistry products, CustomerStorage customers,
			CustomerExportService export) {
		this.products = products;
		this.customers = customers;
		this.export = export;
	}

	@Override
	public void accept(Shell shell) {
		WizardDialog dialog = new WizardDialog(//
				shell, //
				new ExportCustomersWizard(products, customers, export)//
		);
		dialog.setPageSize(700, 400);
		dialog.open();
	}

}
