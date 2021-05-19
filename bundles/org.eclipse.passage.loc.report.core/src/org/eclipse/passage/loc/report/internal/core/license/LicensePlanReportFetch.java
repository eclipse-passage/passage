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

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.licenses.FloatingLicensePackDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.LicenseRequisitesDescriptor;
import org.eclipse.passage.lic.licenses.PersonalLicensePackDescriptor;
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
				.filter(Optional::isPresent) //
				.map(Optional::get) //
				.collect(Collectors.toList());
	}

	private Optional<LicensePlanReport> entry(String id) {
		Optional<LicensePlanDescriptor> plan = storage.plan(id);
		if (!plan.isPresent()) {
			return Optional.empty();
		}
		return Optional.of(//
				new LicensePlanReport(//
						plan.get(), //
						licenses(//
								all -> all.personal(id), //
								PersonalLicensePackDescriptor::getLicense, //
								pack -> pack.getLicense().getUser().getIdentifier()), //
						licenses(//
								all -> all.floating(id), //
								FloatingLicensePackDescriptor::getLicense, //
								pack -> pack.getLicense().getCompany().getIdentifier()), //
						parameters.explain()//
				)//
		);

	}

	private <P> Map<String, List<P>> licenses(//
			Function<LicenseStorage, List<? extends P>> packs, //
			Function<P, LicenseRequisitesDescriptor> license, //
			Function<P, String> owner) {
		return packs.apply(storage).stream()//
				.filter(pack -> license.apply(pack).getIssueDate().after(parameters.from())) //
				.filter(pack -> license.apply(pack).getIssueDate().before(parameters.to()))//
				.collect(Collectors.groupingBy(owner));
	}

}
