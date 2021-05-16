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

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.PersonalLicensePackDescriptor;
import org.eclipse.passage.loc.yars.internal.api.DosHandleMedia;
import org.eclipse.passage.loc.yars.internal.api.ExportData;
import org.eclipse.passage.loc.yars.internal.api.Progress;

/**
 * Single export entry: describe how many licenses have been issues for a single
 * LicensePlan
 * 
 * @since 0.2
 */
@SuppressWarnings("restriction")
public final class LicensePlanReport implements ExportData<LicensePlanReport, DosHandleMedia<LicensePlanReport>> {

	private final LicensePlanDescriptor plan;
	private final int amount;
	private final Map<String, List<PersonalLicensePackDescriptor>> licenses;
	private final boolean explain;
	private final SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd"); //$NON-NLS-1$

	LicensePlanReport(LicensePlanDescriptor plan, int amount, Map<String, List<PersonalLicensePackDescriptor>> licenses,
			boolean explain) {
		this.plan = plan;
		this.amount = amount;
		this.licenses = licenses;
		this.explain = explain;
	}

	@Override
	public void write(DosHandleMedia<LicensePlanReport> media, Progress<LicensePlanReport> progress) {
		media.inner(plan.getName(), "plan-name"); //$NON-NLS-1$
		media.inner(plan.getIdentifier(), "plan-id"); //$NON-NLS-1$
		media.inner(Integer.toString(amount), "licenses"); //$NON-NLS-1$
		if (explain) {
			media.inner(//
					licenses.keySet().stream()//
							.map(this::user) //
							.collect(Collectors.joining(",")), //$NON-NLS-1$
					"users"); //$NON-NLS-1$
		}

	}

	private String user(String user) {
		return String.format("%s (%s)", //$NON-NLS-1$
				user, //
				licenses.get(user).stream()//
						.map(pack -> pack.getLicense().getIssueDate())//
						.map(format::format) //
						.collect(Collectors.joining(", "))//$NON-NLS-1$
		);
	}

}
