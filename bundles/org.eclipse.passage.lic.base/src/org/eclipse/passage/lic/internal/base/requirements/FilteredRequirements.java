/*******************************************************************************
 * Copyright (c) 2022 ArSysOp
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
package org.eclipse.passage.lic.internal.base.requirements;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.registry.StringServiceId;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.requirements.ResolvedRequirements;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;

public final class FilteredRequirements implements ResolvedRequirements {

	private final ResolvedRequirements delegate;
	private final Predicate<Requirement> condition;

	public FilteredRequirements(ResolvedRequirements delegate, Predicate<Requirement> condition) {
		Objects.requireNonNull(delegate, "FilteredRequirements::delegate"); //$NON-NLS-1$
		Objects.requireNonNull(condition, "FilteredRequirements::condition"); //$NON-NLS-1$
		this.delegate = delegate;
		this.condition = condition;
	}

	@Override
	public StringServiceId id() {
		return new StringServiceId("filtered-" + delegate.id().toString()); //$NON-NLS-1$
	}

	@Override
	public ServiceInvocationResult<Collection<Requirement>> all() {
		ServiceInvocationResult<Collection<Requirement>> all = delegate.all();
		Optional<Collection<Requirement>> some = all.data().map(this::filtered);
		return new BaseServiceInvocationResult<>(all.diagnostic(), some);
	}

	private Collection<Requirement> filtered(Collection<Requirement> all) {
		return all.stream()//
				.filter(condition)//
				.collect(Collectors.toList());
	}

}
