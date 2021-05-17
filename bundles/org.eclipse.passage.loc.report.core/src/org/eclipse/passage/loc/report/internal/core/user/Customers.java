/*******************************************************************************
 * Copyright (c) 2019, 2021 ArSysOp
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
package org.eclipse.passage.loc.report.internal.core.user;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.PersonalLicensePackDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.loc.internal.licenses.LicenseRegistry;
import org.eclipse.passage.loc.internal.users.UserRegistry;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * <p>
 * OSGi {@code component} implementation for the {@linkplain CustomerStorage}
 * interface.
 * </p>
 * 
 * <p>
 * {@linkplain UserRegistry} injection is intended to be done by OSGi.
 * </p>
 * 
 * @since 0.2
 *
 *        TODO: https://bugs.eclipse.org/bugs/show_bug.cgi?id=573488
 */
@Component
public final class Customers implements CustomerStorage {

	private UserRegistry users;
	private LicenseRegistry licenses;

	@Override
	public Set<UserDescriptor> forProducts(Set<String> products) {
		return licenses.getLicensePlans().stream() //
				.map(plan -> productLicenses(plan, products))//
				.flatMap(Collection::stream)//
				.map(this::user)//
				.distinct()//
				.map(users::getUser)//
				.collect(Collectors.toSet());
	}

	@Override
	public Set<String> products() {
		return licenses.getLicensePlans().stream()//
				.map(LicensePlanDescriptor::getPersonal)//
				.flatMap(Collection::stream)//
				.map(this::product)//
				.collect(Collectors.toSet());
	}

	/**
	 * It is required to install {@code LIC} {@linkplain UserRegistry} as it is the
	 * source of information provided.
	 * 
	 * @since 0.1
	 */
	@Reference
	public void installUserRegistry(UserRegistry registry) {
		this.users = registry;
	}

	/**
	 * It is required to install {@code LIC} {@linkplain LicensesRegistry} as it is
	 * the source of information provided.
	 * 
	 * @since 0.1
	 */
	@Reference
	public void installLicensesRegistry(LicenseRegistry registry) {
		this.licenses = registry;
	}

	private Set<PersonalLicensePackDescriptor> productLicenses(LicensePlanDescriptor plan, Set<String> products) {
		return plan.getPersonal().stream()//
				.filter(lic -> forProduct(lic, products))//
				.collect(Collectors.toSet());
	}

	private boolean forProduct(PersonalLicensePackDescriptor lic, Set<String> products) {
		return products.contains(lic.getLicense().getProduct().getIdentifier());
	}

	private String user(PersonalLicensePackDescriptor pack) {
		return pack.getLicense().getUser().getIdentifier();
	}

	private String product(PersonalLicensePackDescriptor pack) {
		return pack.getLicense().getProduct().getIdentifier();
	}

}
