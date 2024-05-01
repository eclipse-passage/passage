/*******************************************************************************
 * Copyright (c) 2020, 2024 ArSysOp
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

import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.licenses.model.api.LicensePlan;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
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
	public List<LicensePlan> plans() {
		return StreamSupport.stream(licenses.plans().spliterator(), false)//
				.collect(Collectors.toList());
	}

	@Override
	public List<PersonalLicensePack> personal(String plan) {
		return licenses(plan, LicensePlan::getPersonal);
	}

	@Override
	public List<FloatingLicensePack> floating(String plan) {
		return licenses(plan, LicensePlan::getFloating);
	}

	private <T> List<T> licenses(String plan, Function<LicensePlan, List<T>> get) {
		Optional<LicensePlan> mayBePlan = plan(plan);
		if (!mayBePlan.isPresent()) {
			return Collections.emptyList();
		}
		return get.apply(mayBePlan.get());
	}

	@Override
	public Optional<LicensePlan> plan(String plan) {
		return licenses.plan(plan);
	}

	@Reference
	public void installLicenseRegistry(LicenseRegistry registry) {
		this.licenses = registry;
	}

}
