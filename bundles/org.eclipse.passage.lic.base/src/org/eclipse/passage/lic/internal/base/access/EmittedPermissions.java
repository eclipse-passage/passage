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
import java.util.Collections;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Emission;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.PermissionEmittingService;
import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.base.conditions.evaluation.SumOfEmissions;

/**
 * FIXME: Has public visibility only for testing.
 */
@SuppressWarnings("restriction")
public final class EmittedPermissions implements Supplier<Emission> {

	private final Registry<StringServiceId, PermissionEmittingService> registry;
	private final Collection<Condition> conditions;
	private final LicensedProduct product;

	public EmittedPermissions(Registry<StringServiceId, PermissionEmittingService> registry,
			Collection<Condition> conditions, LicensedProduct product) {
		this.registry = registry;
		this.conditions = conditions;
		this.product = product;
	}

	@Override
	public Emission get() {
		SumOfEmissions sum = new SumOfEmissions();
		return registry.services().stream() //
				.map(service -> service.emit(conditions, product))//
				.reduce(sum)//
				.orElse(new Emission.Successful(Collections.emptyList()));
	}

}
