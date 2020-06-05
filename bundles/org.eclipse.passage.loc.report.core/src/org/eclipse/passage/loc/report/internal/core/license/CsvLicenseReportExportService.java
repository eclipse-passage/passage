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

import org.eclipse.passage.loc.yars.internal.api.Progress;
import org.eclipse.passage.loc.yars.internal.api.ReportException;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * <p>
 * OSGi {@code component} that implements the package central
 * {@linkplain LicenseReportExportService} interface with {@code csv}-targeted
 * export.
 * </p>
 * <p>
 * {@linkplain LicenseStorage} reference is intended to be injected by OSGi.
 * </p>
 * 
 * @since 0.2
 */
@SuppressWarnings("restriction")
@Component
public final class CsvLicenseReportExportService implements LicenseReportExportService {

	private LicenseStorage source;

	@Override
	public void exportLicensePlans(LicensePlanReportParameters params, Path target,
			Progress<LicensePlanReport> progress) throws ReportException {
		new LicenseReportToCsv(source).export(params, target, progress);
	}

	/**
	 * It is required to install a {@linkplain LicenseStorage} to the service prior
	 * any export action is asked as it is the base source of information to be
	 * exported.
	 * 
	 * @since 0.1
	 */
	@Reference
	public void installLicensePlanStorage(LicenseStorage storage) {
		source = storage;
	}

}
