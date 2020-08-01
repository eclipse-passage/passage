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
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionMiningException;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.base.conditions.FeatureConditionPack;

/**
 * FIXME: Has public visibility only for testing.
 */
@SuppressWarnings("restriction")
public final class Conditions implements Supplier<ServiceInvocationResult<Collection<ConditionPack>>> {

	private final Registry<StringServiceId, MinedConditions> registry;
	private final LicensedProduct product;
	private final String feature;

	public Conditions(Registry<StringServiceId, MinedConditions> registry, LicensedProduct product, String feature) {
		this.registry = registry;
		this.product = product;
		this.feature = feature;
	}

	@Override
	public ServiceInvocationResult<Collection<ConditionPack>> get() {
		if (registry.services().isEmpty()) {
			return new ServiceInvocationResult.Error<>();
		}
		ServiceInvocationResult.Sum<Collection<ConditionPack>> sum = //
				new ServiceInvocationResult.Sum<>(new SumOfCollections<ConditionPack>());
		return registry.services().stream() //
				.map(this::mineWithService)//
				.reduce(sum)//
				.orElse(new ServiceInvocationResult.Success<>(Collections.emptyList()));

	}

	private ServiceInvocationResult<Collection<ConditionPack>> mineWithService(MinedConditions service) {
		try {
			return new ServiceInvocationResult.Success<>(//
					service.all(product).stream()//
							.map(pack -> new FeatureConditionPack(pack, feature))//
							.filter(pack -> !pack.conditions().isEmpty())//
							.collect(Collectors.toSet()));
		} catch (ConditionMiningException e) {
			return new ServiceInvocationResult.Error<>();
		}
	}

}
