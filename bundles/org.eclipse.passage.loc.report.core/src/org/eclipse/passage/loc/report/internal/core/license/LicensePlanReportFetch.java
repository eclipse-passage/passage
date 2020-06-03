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

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.users.UserLicenseDescriptor;
import org.eclipse.passage.loc.yars.internal.api.FetchedData;

/**
 * @since 0.2
 */
@SuppressWarnings("restriction")
final class LicensePlanReportFetch implements FetchedData<LicenseStorage, LicensePlanReport> {

	private final LicenseStorage storage;
	private final LicensePlanReportParameters parameters;

	LicensePlanReportFetch(LicenseStorage storage, LicensePlanReportParameters parameters) {
		this.storage = storage;
		this.parameters = parameters;
	}

	@Override
	public List<LicensePlanReport> get() {
		return parameters.plans().stream() //
				.map(this::entry) //
				.collect(Collectors.toList());
	}

	private LicensePlanReport entry(String id) {
		LicensePlanDescriptor plan = storage.plan(id);
		List<UserLicenseDescriptor> licenses = storage.licenses(id).stream()//
				.filter(lic -> lic.getIssueDate().after(parameters.from())) //
				.filter(lic -> lic.getIssueDate().before(parameters.to()))//
				.collect(Collectors.toList());
		return new LicensePlanReport(//
				plan, //
				licenses.size(), //
				licenses.stream() //
						.collect(Collectors.groupingBy(UserLicenseDescriptor::getUser))//
		);

	}

}
