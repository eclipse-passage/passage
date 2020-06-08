package org.eclipse.passage.loc.report.internal.ui.jface.license;

import java.util.function.Consumer;

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.passage.loc.report.internal.core.license.LicenseReportExportService;
import org.eclipse.passage.loc.report.internal.core.license.LicenseStorage;
import org.eclipse.passage.loc.report.internal.ui.i18n.ExportLicenseReportWizardMessages;
import org.eclipse.swt.widgets.Shell;

/**
 * @since 0.2
 */
public final class ExposedIssuedLicensesReportWizard implements Consumer<Shell> {

	private final LicenseStorage storage;
	private final LicenseReportExportService export;

	public ExposedIssuedLicensesReportWizard(LicenseStorage storage, LicenseReportExportService export) {
		this.storage = storage;
		this.export = export;
	}

	@Override
	public void accept(Shell shell) {
		WizardDialog dialog = new WizardDialog(//
				shell, //
				new IssuedLicensesReportWizard(storage, export)//
		);
		dialog.setTitle(ExportLicenseReportWizardMessages.ExposedIssuedLicensesReportWizard_dialogTitle);
		dialog.setPageSize(700, 300);
		dialog.open();
	}

}
