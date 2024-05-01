/*******************************************************************************
 * Copyright (c) 2019, 2024 ArSysOp
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
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.licenses.model.api.LicensePlan;
import org.eclipse.passage.lic.licenses.model.api.LicenseRequisites;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;
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
 */
@Component
public final class Customers implements CustomerStorage {

	private UserRegistry users;
	private LicenseRegistry licenses;

	@Override
	public Set<UserDescriptor> personsUsedProducts(Set<String> products) {
		return licenses.plans().stream() //
				.map(plan -> licenses(plan.getPersonal(), products, PersonalLicensePack::getLicense))//
				.flatMap(Collection::stream)//
				.map(this::user)//
				.distinct()//
				.map(users::getUser)//
				.collect(Collectors.toSet());
	}

	@Override
	public Set<UserOriginDescriptor> companiesUsedProducts(Set<String> products) {
		return licenses.plans().stream() //
				.map(plan -> licenses(plan.getFloating(), products, FloatingLicensePack::getLicense))//
				.flatMap(Collection::stream)//
				.map(this::company)//
				.distinct()//
				.map(users::getUserOrigin)//
				.collect(Collectors.toSet());
	}

	@Override
	public Set<String> products() {
		return licenses.plans().stream()//
				.map(LicensePlan::getPersonal)//
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

	private <T> Set<T> licenses(Collection<T> packs, Set<String> products, Function<T, LicenseRequisites> license) {
		return packs.stream()//
				.filter(pack -> forProduct(license.apply(pack), products))//
				.collect(Collectors.toSet());
	}

	private boolean forProduct(LicenseRequisites lic, Set<String> products) {
		return products.contains(lic.getProduct().getIdentifier());
	}

	private String user(PersonalLicensePack pack) {
		return pack.getLicense().getUser().getIdentifier();
	}

	private String company(FloatingLicensePack pack) {
		return pack.getLicense().getCompany().getIdentifier();
	}

	private String product(PersonalLicensePack pack) {
		return pack.getLicense().getProduct().getIdentifier();
	}

}
