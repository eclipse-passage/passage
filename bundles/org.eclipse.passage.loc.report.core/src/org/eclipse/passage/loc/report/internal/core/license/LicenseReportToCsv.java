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

import org.eclipse.passage.loc.report.internal.core.Csv;
import org.eclipse.passage.loc.report.internal.core.ExistingFileStream;
import org.eclipse.passage.loc.yars.internal.api.DefaultDosHandler;
import org.eclipse.passage.loc.yars.internal.api.DosHandleMedia;
import org.eclipse.passage.loc.yars.internal.api.Progress;
import org.eclipse.passage.loc.yars.internal.api.ReportException;
import org.eclipse.passage.loc.yars.internal.api.SingleSwoopExport;

/**
 * @since 0.2
 */
@SuppressWarnings("restriction")
final class LicenseReportToCsv {

	private final LicenseStorage source;

	LicenseReportToCsv(LicenseStorage source) {
		this.source = source;
	}

	public void export(//
			LicensePlanReportParameters parameters, //
			Path target, //
			Progress<LicensePlanReport> progress) throws ReportException {
		new SingleSwoopExport<LicenseStorage, LicensePlanReport>(//
				new LicensePlanReportQuery().fetch(source, parameters)) //
						.write( //
								new DosHandleMedia<LicensePlanReport>( //
										new Csv( //
												new ExistingFileStream(target), //
												"License plan", //$NON-NLS-1$
												"Amount of licenses", //$NON-NLS-1$
												"Users"), //$NON-NLS-1$
										new DefaultDosHandler()), //
								progress);
	}
}
