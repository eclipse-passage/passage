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
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.api.registry.Registry;
import org.eclipse.passage.lic.api.registry.StringServiceId;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.requirements.ResolvedRequirements;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.SumOfCollections;
import org.eclipse.passage.lic.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.base.diagnostic.code.NoRequirements;
import org.eclipse.passage.lic.base.diagnostic.code.NoServicesOfType;
import org.eclipse.passage.lic.base.requirements.RequirementsFeatureFilter;
import org.eclipse.passage.lic.internal.base.i18n.BaseMessages;

/**
 * @since 2.1
 */
public final class Requirements implements Supplier<ServiceInvocationResult<Collection<Requirement>>> {

	private final Registry<StringServiceId, ResolvedRequirements> registry;
	private final Function<//
			ServiceInvocationResult<Collection<Requirement>>, //
			ServiceInvocationResult<Collection<Requirement>>> filter;
	private final Optional<String> feature;

	public Requirements(Registry<StringServiceId, ResolvedRequirements> registry, String feature) {
		this(registry, new RequirementsFeatureFilter(feature).get(), Optional.of(feature));
	}

	public Requirements(Registry<StringServiceId, ResolvedRequirements> registry) {
		this(registry, Function.identity(), Optional.empty());
	}

	public Requirements(Registry<StringServiceId, ResolvedRequirements> registry,
			Function<ServiceInvocationResult<Collection<Requirement>>, ServiceInvocationResult<Collection<Requirement>>> filter,
			Optional<String> feature) {
		Objects.requireNonNull(registry, "Requirements::registry"); //$NON-NLS-1$
		Objects.requireNonNull(filter, "Requirements::filter"); //$NON-NLS-1$
		this.registry = registry;
		this.filter = filter;
		this.feature = feature;
	}

	@Override
	public ServiceInvocationResult<Collection<Requirement>> get() {
		if (registry.services().isEmpty()) {
			return noServices();
		}
		ServiceInvocationResult<Collection<Requirement>> result = filtered();
		if (empty(result)) {
			return withNoRequirementsWarning(result);
		}
		return result;

	}

	private ServiceInvocationResult<Collection<Requirement>> filtered() {
		return registry.services().stream() //
				.map(ResolvedRequirements::all) //
				.reduce(new BaseServiceInvocationResult.Sum<>(new SumOfCollections<Requirement>()))//
				.map(filter)//
				.get(); // always exists
	}

	private BaseServiceInvocationResult<Collection<Requirement>> noServices() {
		return new BaseServiceInvocationResult<Collection<Requirement>>(//
				new Trouble(//
						new NoServicesOfType("requirement resolution"), //$NON-NLS-1$
						BaseMessages.getString("Requirements.failed"))); //$NON-NLS-1$
	}

	private boolean empty(ServiceInvocationResult<Collection<Requirement>> result) {
		return result.data().map(Collection::isEmpty).orElse(false);
	}

	private ServiceInvocationResult<Collection<Requirement>> withNoRequirementsWarning(
			ServiceInvocationResult<Collection<Requirement>> original) {
		return new BaseServiceInvocationResult<>(//
				new BaseDiagnostic(//
						original.diagnostic().severe(), //
						withNoRequirementsWarning(original.diagnostic().bearable())), //
				original.data()//
		);
	}

	private List<Trouble> withNoRequirementsWarning(List<Trouble> original) {
		List<Trouble> more = new ArrayList<>(original);
		more.add(new Trouble(//
				new NoRequirements(), //
				feature.map(fe -> String.format(BaseMessages.getString("Requirements.no_requirements_for_feature"), fe))// //$NON-NLS-1$
						.orElse(BaseMessages.getString("Requirements.no_requirements_for_product")))); //$NON-NLS-1$
		return more;
	}
}
