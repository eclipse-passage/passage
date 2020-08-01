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
package org.eclipse.passage.lic.internal.base.access;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Emission;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.PermissionEmittingService;
import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;

/**
 * FIXME: Has public visibility only for testing.
 */
@SuppressWarnings("restriction")
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
				.flatMap(Collection::stream) //
				.filter(Emission::successful) //
				.map(Emission::permissions) //
				.flatMap(Collection::stream) //
				.collect(Collectors.toSet());
	}

}
