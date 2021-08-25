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
package org.eclipse.passage.lic.base.access;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.agreements.GlobalAgreement;
import org.eclipse.passage.lic.api.conditions.ConditionPack;
import org.eclipse.passage.lic.api.conditions.evaluation.Emission;
import org.eclipse.passage.lic.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.api.conditions.evaluation.PermissionEmittingService;
import org.eclipse.passage.lic.api.registry.Registry;
import org.eclipse.passage.lic.api.registry.StringServiceId;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.SumOfCollections;
import org.eclipse.passage.lic.base.access.Permissions.AppliedLicenses;

/**
 * FIXME: Has public visibility only for testing.
 * 
 * @since 2.1
 */
public final class Permissions implements Supplier<ServiceInvocationResult<AppliedLicenses>> {

	private final Registry<StringServiceId, PermissionEmittingService> registry;
	private final Collection<ConditionPack> conditions;
	private final LicensedProduct product;

	public Permissions(Registry<StringServiceId, PermissionEmittingService> registry,
			Collection<ConditionPack> conditions, LicensedProduct product) {
		this.registry = registry;
		this.conditions = conditions;
		this.product = product;
	}

	@Override
	public ServiceInvocationResult<AppliedLicenses> get() {
		BaseServiceInvocationResult<Collection<Permission>> permissions = permissions();
		return new BaseServiceInvocationResult<>(//
				permissions.diagnostic(), //
				new AppliedLicenses(//
						permissions.data().get(), //
						agreements())//
		);
	}

	private BaseServiceInvocationResult<Collection<Permission>> permissions() {
		return registry.services().stream() //
				.map(service -> service.emit(conditions, product))//
				.reduce(new BaseServiceInvocationResult.Sum<>(new SumOfCollections<Emission>()))//
				.map(r -> new BaseServiceInvocationResult<Collection<Permission>>(//
						r.diagnostic(), //
						allPermissions(r.data())))//
				.orElse(new BaseServiceInvocationResult<>(Collections.emptyList()));
	}

	private Collection<Permission> allPermissions(Optional<Collection<Emission>> emissions) {
		if (!emissions.isPresent()) {
			return Collections.emptyList();
		}
		return emissions.get().stream() //
				.reduce(//
						new ArrayList<Permission>(), //
						new SumEmissionsToData(), //
						new SumOfCollections<Permission>());
	}

	private Collection<GlobalAgreement> agreements() {
		return conditions.stream()//
				.map(ConditionPack::agreements)//
				.flatMap(Collection::stream)//
				.collect(Collectors.toList());
	}

	/**
	 * Here we do not need `ConditionPack` granularity, thus unfold each one to
	 * amass all the permissions we have.
	 */
	private final static class SumEmissionsToData
			implements BiFunction<Collection<Permission>, Emission, Collection<Permission>> {

		@Override
		public Collection<Permission> apply(Collection<Permission> sum, Emission emission) {
			sum.addAll(emission.permissions());
			return sum;
		}

	}

	public static final class AppliedLicenses {

		private final Collection<Permission> permissions;
		private final Collection<GlobalAgreement> agreements;

		AppliedLicenses() {
			this(Collections.emptyList(), Collections.emptyList());
		}

		AppliedLicenses(Collection<Permission> permissions, Collection<GlobalAgreement> agreements) {
			this.permissions = permissions;
			this.agreements = agreements;
		}

		Collection<Permission> permissions() {
			return permissions;
		}

		Collection<GlobalAgreement> agreements() {
			return agreements;
		}
	}

}
