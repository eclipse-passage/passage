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
package org.eclipse.passage.loc.report.internal.ui.jface.license;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.passage.loc.report.internal.core.license.LicensePlanReport;
import org.eclipse.passage.loc.report.internal.core.license.LicensePlanReportParameters;
import org.eclipse.passage.loc.report.internal.core.license.LicenseReportExportService;
import org.eclipse.passage.loc.report.internal.ui.i18n.ExportCustomersWizardMessages;
import org.eclipse.passage.loc.report.internal.ui.jface.VisibleProgress;
import org.eclipse.passage.loc.yars.internal.api.ReportException;

@SuppressWarnings("restriction")
final class MonitoredExportOperation implements IRunnableWithProgress {

	private final LicenseReportExportService service;
	private final LicensePlanReportParameters parameters;
	private final Path file;

	MonitoredExportOperation(//
			LicenseReportExportService service, //
			LicensePlanReportParameters parameters, //
			Path file) {
		this.service = service;
		this.parameters = parameters;
		this.file = file;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		try {
			service.exportLicensePlans(//
					parameters, //
					file, //
					new VisibleProgress<LicensePlanReport>(//
							monitor, //
							ExportCustomersWizardMessages.VisibleProgress_task//
					)//
			);
		} catch (ReportException e) {
			throw new InvocationTargetException(e);
		}
	}

}
