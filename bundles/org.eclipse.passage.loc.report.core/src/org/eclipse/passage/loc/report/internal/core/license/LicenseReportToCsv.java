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
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.report.internal.core.license;

import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.passage.loc.report.internal.core.Csv;
import org.eclipse.passage.loc.report.internal.core.ExistingFileStream;
import org.eclipse.passage.loc.report.internal.core.i18n.LicensesReportMessages;
import org.eclipse.passage.loc.yars.internal.api.DefaultDosHandler;
import org.eclipse.passage.loc.yars.internal.api.DosHandleMedia;
import org.eclipse.passage.loc.yars.internal.api.Progress;
import org.eclipse.passage.loc.yars.internal.api.ReportException;
import org.eclipse.passage.loc.yars.internal.api.SingleSwoopExport;

/**
 * @since 0.2
 */
@SuppressWarnings("restriction")
public final class LicenseReportToCsv {

	private final LicenseStorage source;

	public LicenseReportToCsv(LicenseStorage source) {
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
										new Csv<LicensePlanReport>( //
												new ExistingFileStream(target), //
												header(parameters)), //
										new DefaultDosHandler()), //
								progress);
	}

	private String[] header(LicensePlanReportParameters parameters) {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.YYYY"); //$NON-NLS-1$
		List<String> header = new ArrayList<>(Arrays.asList(//
				LicensesReportMessages.getString("LicenseReportToCsv_header_planName"), // //$NON-NLS-1$
				LicensesReportMessages.getString("LicenseReportToCsv_header_planId"), // //$NON-NLS-1$
				LicensesReportMessages.getString("LicenseReportToCsv_header_amountOfLicenses"), //$NON-NLS-1$
				LicensesReportMessages.getString("LicenseReportToCsv_header_amountOfFloatingLicenses"))); // //$NON-NLS-1$
		if (parameters.explain()) {
			header.add(String.format(//
					LicensesReportMessages.getString("LicenseReportToCsv_details"), // //$NON-NLS-1$
					format.format(parameters.from()), //
					format.format(parameters.to())//
			));
			header.add(LicensesReportMessages.getString("LicenseReportToCsv_floatingDetails")); //$NON-NLS-1$
		}
		return header.toArray(new String[header.size()]);
	}
}
