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
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.SumOfCollections;
import org.eclipse.passage.lic.internal.base.conditions.FeatureConditionPack;
import org.eclipse.passage.lic.internal.base.diagnostic.code.NoServicesOfType;
import org.eclipse.passage.lic.internal.base.i18n.AccessCycleMessages;

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
			return new BaseServiceInvocationResult<Collection<ConditionPack>>(//
					new Trouble(//
							new NoServicesOfType(AccessCycleMessages.getString("Conditions.servive_type")), // //$NON-NLS-1$
							AccessCycleMessages.getString("Conditions.no_miners"))); //$NON-NLS-1$
		}
		return registry.services().stream() //
				.map(miner -> miner.all(product)) //
				.reduce(new BaseServiceInvocationResult.Sum<>(new SumOfCollections<ConditionPack>()))//
				.map(this::filter)//
				.orElse(new BaseServiceInvocationResult<>());

	}

	private ServiceInvocationResult<Collection<ConditionPack>> filter(
			ServiceInvocationResult<Collection<ConditionPack>> overwhelming) {
		return new BaseServiceInvocationResult<Collection<ConditionPack>>(//
				overwhelming.diagnostic(), //
				filter(overwhelming.data()));
	}

	private Collection<ConditionPack> filter(Optional<Collection<ConditionPack>> conditions) {
		return conditions.map(this::filter).orElse(Collections.emptySet());
	}

	private Collection<ConditionPack> filter(Collection<ConditionPack> conditions) {
		return conditions.stream()//
				.map(pack -> new FeatureConditionPack(pack, feature))//
				.filter(pack -> !pack.conditions().isEmpty()) //
				.collect(Collectors.toSet());
	}
}
