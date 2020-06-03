/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.loc.report.internal.core.license;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;

import org.eclipse.passage.loc.report.internal.core.BaseExportCommandTest;
import org.eclipse.passage.loc.yars.internal.api.Progress;
import org.eclipse.passage.loc.yars.internal.api.ReportException;
import org.junit.Test;

/**
 * plain unit test
 */
@SuppressWarnings("restriction")
public class ExportLicenseReportCommandTest extends BaseExportCommandTest<TestLicenses> {

	@Test
	public void ñsvExport() {
		Path output = outputFile(""); //$NON-NLS-1$
		TestLicenses data = new TestLicenses.Empty();
		exportSilent(data, output);
		assertEquals(data.csv(), results(output));
	}

	@Override
	protected void export(TestLicenses data, Path output) throws ReportException {
		new LicenseReportToCsv(data.storage())//
				.export(parameters(), output, new Progress.Inane<LicensePlanReport>());

	}

	private LicensePlanReportParameters parameters() {
		return null;
	}

}
