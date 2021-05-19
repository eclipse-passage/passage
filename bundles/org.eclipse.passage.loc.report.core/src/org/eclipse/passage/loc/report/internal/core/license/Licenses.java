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

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.passage.lic.licenses.FloatingLicensePackDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.PersonalLicensePackDescriptor;
import org.eclipse.passage.loc.internal.licenses.LicenseRegistry;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @since 0.2 TODO: https://bugs.eclipse.org/bugs/show_bug.cgi?id=573488
 */
@Component
public final class Licenses implements LicenseStorage {

	private LicenseRegistry licenses;

	@Override
	public List<LicensePlanDescriptor> plans() {
		return StreamSupport.stream(licenses.getLicensePlans().spliterator(), false)//
				.collect(Collectors.toList());
	}

	@Override
	public List<? extends PersonalLicensePackDescriptor> personal(String plan) {
		return licenses(plan, LicensePlanDescriptor::getPersonal);
	}

	@Override
	public List<? extends FloatingLicensePackDescriptor> floating(String plan) {
		return licenses(plan, LicensePlanDescriptor::getFloating);
	}

	private <T> List<T> licenses(String plan, Function<LicensePlanDescriptor, List<T>> get) {
		Optional<LicensePlanDescriptor> mayBePlan = plan(plan);
		if (!mayBePlan.isPresent()) {
			return Collections.emptyList();
		}
		return get.apply(mayBePlan.get());
	}

	@Override
	public Optional<LicensePlanDescriptor> plan(String plan) {
		return Optional.ofNullable(licenses.getLicensePlan(plan));
	}

	@Reference
	public void installLicenseRegistry(LicenseRegistry registry) {
		this.licenses = registry;
	}

}
