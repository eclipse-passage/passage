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

import org.eclipse.passage.loc.report.internal.core.i18n.LicensesReportMessages;
import org.eclipse.passage.loc.yars.internal.api.FetchedData;
import org.eclipse.passage.loc.yars.internal.api.Query;

/**
 * @since 0.2
 */
@SuppressWarnings("restriction")
final class LicensePlanReportQuery implements Query<LicenseStorage, LicensePlanReport, LicensePlanReportParameters> {

	@Override
	public String id() {
		return "LICENSES_FOR_PLAN"; //$NON-NLS-1$
	}

	@Override
	public String description() {
		return LicensesReportMessages.getString("LicensePlanReportQuery_description"); //$NON-NLS-1$
	}

	@Override
	public FetchedData<LicenseStorage, LicensePlanReport> fetch(LicenseStorage storage, LicensePlanReportParameters properties) {
		return new LicensePlanReportFetch(storage, properties);
	}

}
