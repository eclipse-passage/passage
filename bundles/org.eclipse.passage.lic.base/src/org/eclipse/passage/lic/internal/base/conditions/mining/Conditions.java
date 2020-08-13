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
package org.eclipse.passage.lic.internal.base.conditions.mining;

import java.util.Collection;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.FeatureFilter;
import org.eclipse.passage.lic.internal.base.SumOfCollections;
import org.eclipse.passage.lic.internal.base.conditions.FeatureConditionPack;
import org.eclipse.passage.lic.internal.base.diagnostic.code.NoServicesOfType;
import org.eclipse.passage.lic.internal.base.i18n.AccessCycleMessages;

@SuppressWarnings("restriction")
public final class Conditions implements Supplier<ServiceInvocationResult<Collection<ConditionPack>>> {

	private final Registry<StringServiceId, MinedConditions> registry;
	private final LicensedProduct product;
	private final Function<ServiceInvocationResult<Collection<ConditionPack>>, ServiceInvocationResult<Collection<ConditionPack>>> filter;

	public Conditions(Registry<StringServiceId, MinedConditions> registry, LicensedProduct product, String feature) {
		this(registry, product, new FeatureFilter<ConditionPack>(feature, new Filtered()));
	}

	public Conditions(Registry<StringServiceId, MinedConditions> registry, LicensedProduct product) {
		this(registry, product, Function.identity());
	}

	public Conditions(Registry<StringServiceId, MinedConditions> registry, LicensedProduct product,
			Function<ServiceInvocationResult<Collection<ConditionPack>>, ServiceInvocationResult<Collection<ConditionPack>>> filter) {
		this.registry = registry;
		this.product = product;
		this.filter = filter;
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
				.map(filter) //
				.orElse(new BaseServiceInvocationResult<>());

	}

	private static final class Filtered implements BiFunction<ConditionPack, String, Optional<ConditionPack>> {

		@Override
		public Optional<ConditionPack> apply(ConditionPack pack, String incoming) {
			ConditionPack filtered = new FeatureConditionPack(pack, incoming);
			return filtered.conditions().isEmpty() ? Optional.empty() : Optional.of(filtered);
		}

	}

}
