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
