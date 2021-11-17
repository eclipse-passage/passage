/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.loc.report.internal.ui.jface.license;

import java.nio.file.Path;
import java.util.Date;
import java.util.Set;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.passage.loc.report.internal.core.license.LicensePlanReportParameters;
import org.eclipse.passage.loc.report.internal.core.license.LicenseReportExportService;
import org.eclipse.passage.loc.report.internal.core.license.LicenseStorage;
import org.eclipse.passage.loc.report.internal.ui.i18n.ExportLicenseReportWizardMessages;
import org.eclipse.passage.loc.report.internal.ui.i18n.ExportWizardMessages;
import org.eclipse.passage.loc.report.internal.ui.jface.FileForExport;
import org.eclipse.passage.loc.report.internal.ui.jface.TargetPage;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;

final class IssuedLicensesReportWizard extends Wizard {

	private final LicenseReportExportService export;
	private final DataForExport data;
	private final PreviewPage preview;
	private final PlansPage scope;
	private final ConfigPage config;
	private final TargetPage target;

	// private final String scope = "Scope"; //$NON-NLS-1$

	public IssuedLicensesReportWizard(LicenseStorage storage, LicenseReportExportService export) {
		this.export = export;
		this.data = new DataForExport(//
				this::identifiers, //
				this::from, //
				this::to, //
				this::explain, //
				this::path, //
				this::open);
		this.preview = new PreviewPage(storage, data);
		this.scope = new PlansPage(new LicensePlans(storage), preview);
		this.config = new ConfigPage(preview);
		this.target = new TargetPage(preview);
		setWindowTitle(ExportLicenseReportWizardMessages.ExposedIssuedLicensesReportWizard_dialogTitle);
	}

	@Override
	public void addPages() {
		addPage(scope);
		addPage(config);
		addPage(target);
		addPage(preview);
	}

	@Override
	public void createPageControls(Composite container) {
		super.createPageControls(container);
		target.installInitial();
		scope.installInitial();
		config.installInitial();
		preview.update();

	}

	@Override
	public boolean performFinish() {
		Path file = new FileForExport(data.target(), "issued-licenses").get(); //$NON-NLS-1$
		try {
			new ProgressMonitorDialog(getShell()).run(//
					true, //
					true, //
					new MonitoredExportOperation(//
							export, //
							new LicensePlanReportParameters(//
									data.plans(), //
									data.from(), //
									data.to(), //
									data.explain()),
							file));
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

	private Path path() {
		return target.path();
	}

	private Set<String> identifiers() {
		return scope.identifiers();
	}

	private Date from() {
		return config.from();
	}

	private Date to() {
		return config.to();
	}

	private boolean explain() {
		return config.explain();
	}

	private boolean open() {
		return target.open();
	}

}
