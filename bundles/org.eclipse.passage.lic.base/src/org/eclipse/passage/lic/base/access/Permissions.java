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

import org.eclipse.passage.lic.api.registry.Registry;
import org.eclipse.passage.lic.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Emission;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.PermissionEmittingService;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.SumOfCollections;

/**
 * FIXME: Has public visibility only for testing.
 * 
 * @since 1.1
 */
public final class Permissions implements Supplier<ServiceInvocationResult<Collection<Permission>>> {

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
	public ServiceInvocationResult<Collection<Permission>> get() {
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

}
