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
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.PermissionsExaminationService;
import org.eclipse.passage.lic.internal.api.restrictions.Restriction;

@SuppressWarnings("restriction")
public final class Restrictions implements Supplier<ServiceInvocationResult<Collection<Restriction>>> {

	private final Registry<StringServiceId, PermissionsExaminationService> registry;
	private final Collection<Requirement> requirements;
	private final Collection<Permission> permissions;
	private final LicensedProduct product;

	public Restrictions(Registry<StringServiceId, PermissionsExaminationService> registry,
			Collection<Requirement> requirements, Collection<Permission> permissions, LicensedProduct product) {
		this.registry = registry;
		this.requirements = requirements;
		this.permissions = permissions;
		this.product = product;
	}

	@Override
	public ServiceInvocationResult<Collection<Restriction>> get() {
		if (registry.services().isEmpty()) {
			return new ServiceInvocationResult.Error<>();
		}
		return new ServiceInvocationResult.Success<>(//
				registry.services().stream()//
						.map(service -> service.examine(requirements, permissions, product))//
						.flatMap(Collection::stream) //
						.collect(Collectors.toSet()));
	}

}
