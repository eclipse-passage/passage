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

/**
 * @since 0.2
 */
@SuppressWarnings("restriction")
public interface LicenseReportExportService {

	void exportLicensePlans(LicensePlanReportParameters params, Path target, Progress<LicensePlanReport> progress)
			throws ReportException;

}
