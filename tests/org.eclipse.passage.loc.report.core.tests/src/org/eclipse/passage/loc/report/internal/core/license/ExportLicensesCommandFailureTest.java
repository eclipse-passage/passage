package org.eclipse.passage.loc.report.internal.core.license;

import java.nio.file.Path;

import org.eclipse.passage.loc.report.internal.core.ExportCommandFailureTest;
import org.eclipse.passage.loc.yars.internal.api.Progress;
import org.eclipse.passage.loc.yars.internal.api.ReportException;

@SuppressWarnings("restriction")
public class ExportLicensesCommandFailureTest extends ExportCommandFailureTest<TestLicenses> {

	@Override
	protected void export(TestLicenses data, Path output) throws ReportException {
		new LicenseReportToCsv(data.storage())//
				.export(//
						data.params(), //
						output, //
						new Progress.Inane<LicensePlanReport>()//
				);

	}

	@Override
	protected TestLicenses data() {
		return new TestLicenses.Empty();
	}

}
