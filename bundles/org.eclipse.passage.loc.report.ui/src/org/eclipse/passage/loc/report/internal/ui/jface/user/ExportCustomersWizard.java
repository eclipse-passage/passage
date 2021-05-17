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

import java.nio.file.Path;
import java.util.Set;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.passage.loc.internal.products.ProductRegistry;
import org.eclipse.passage.loc.report.internal.core.user.CustomerExportService;
import org.eclipse.passage.loc.report.internal.core.user.CustomerStorage;
import org.eclipse.passage.loc.report.internal.ui.i18n.ExportCustomersWizardMessages;
import org.eclipse.passage.loc.report.internal.ui.i18n.ExportWizardMessages;
import org.eclipse.passage.loc.report.internal.ui.jface.FileForExport;
import org.eclipse.passage.loc.report.internal.ui.jface.TargetPage;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;

final class ExportCustomersWizard extends Wizard {

	private final CustomerExportService export;
	private final DataForExport data;
	private final PreviewPage preview;
	private final ScopePage scope;
	private final TargetPage target;

	// private final String scope = "Scope"; //$NON-NLS-1$

	public ExportCustomersWizard(ProductRegistry products, CustomerStorage customers, CustomerExportService export) {
		this.export = export;
		this.data = new DataForExport(this::identifiers, this::path, this::open);
		this.preview = new PreviewPage(customers, data);
		this.scope = new ScopePage(new Products(products, customers), preview);
		this.target = new TargetPage(preview);
		setWindowTitle(ExportCustomersWizardMessages.ExposedExportCustomersWizard_dialogTitle);
	}

	@Override
	public void addPages() {
		addPage(scope);
		addPage(target);
		addPage(preview);
	}

	@Override
	public void createPageControls(Composite container) {
		super.createPageControls(container);
		scope.installInitial();
		target.installInitial();
	}

	private Set<String> identifiers() {
		return scope.identifiers();
	}

	private Path path() {
		return target.path();
	}

	private boolean open() {
		return target.open();
	}

	@Override
	public boolean performFinish() {
		Path file = new FileForExport(data.target(), "users").get(); //$NON-NLS-1$
		try {
			new ProgressMonitorDialog(getShell()).run(//
					true, //
					true, //
					new MonitoredExportOperation(export, data.products(), file));
		} catch (Exception e) {
			MessageDialog.openError(//
					getShell(), //
					ExportWizardMessages.ExportWizard_errorTitle, //
					e.getLocalizedMessage());
			return false;
		}
		if (data.open()) {
			Program.launch(file.toAbsolutePath().toString());
		}
		return true;
	}

	@Override
	public boolean canFinish() {
		return data.complete();
	}

}
