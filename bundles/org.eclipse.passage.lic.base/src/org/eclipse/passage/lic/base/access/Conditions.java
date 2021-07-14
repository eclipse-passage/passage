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

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.api.conditions.ConditionPack;
import org.eclipse.passage.lic.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.api.registry.Registry;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.SumOfCollections;
import org.eclipse.passage.lic.base.conditions.ConditionsFeatureFilter;
import org.eclipse.passage.lic.base.diagnostic.code.NoServicesOfType;
import org.eclipse.passage.lic.internal.base.i18n.AccessCycleMessages;

/**
 * @since 2.1
 */
public final class Conditions implements Supplier<ServiceInvocationResult<Collection<ConditionPack>>> {

	private final Registry<ConditionMiningTarget, MinedConditions> registry;
	private final LicensedProduct product;
	private final Function<//
			ServiceInvocationResult<Collection<ConditionPack>>, //
			ServiceInvocationResult<Collection<ConditionPack>>> filter;

	public Conditions(Registry<ConditionMiningTarget, MinedConditions> registry, LicensedProduct product,
			String feature) {
		this(registry, product, new ConditionsFeatureFilter(feature).get());
	}

	public Conditions(Registry<ConditionMiningTarget, MinedConditions> registry, LicensedProduct product) {
		this(registry, product, Function.identity());
	}

	public Conditions(Registry<ConditionMiningTarget, MinedConditions> registry, LicensedProduct product,
			Function<ServiceInvocationResult<Collection<ConditionPack>>, ServiceInvocationResult<Collection<ConditionPack>>> filter) {
		Objects.requireNonNull(registry, "Conditions::registry"); //$NON-NLS-1$
		Objects.requireNonNull(product, "Conditions::product"); //$NON-NLS-1$
		Objects.requireNonNull(filter, "Conditions::filter"); //$NON-NLS-1$
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
				.orElse(new BaseServiceInvocationResult<>(Collections.emptyList()));

	}

}
