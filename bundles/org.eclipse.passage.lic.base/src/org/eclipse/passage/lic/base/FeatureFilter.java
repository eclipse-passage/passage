/*******************************************************************************
 * Copyright (c) 2020, 2024 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support and improvements
 *******************************************************************************/
package org.eclipse.passage.lic.base;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.FeatureIdentifier;
import org.eclipse.passage.lic.api.ServiceInvocationResult;

/**
 * 
 * @param <T>
 * 
 * @since 2.1
 */
public final class FeatureFilter<T>
		implements Function<ServiceInvocationResult<Collection<T>>, ServiceInvocationResult<Collection<T>>> {

	private final FeatureIdentifier feature;
	private final BiFunction<T, FeatureIdentifier, Optional<T>> map;

	/**
	 * @since 4.0
	 */
	public FeatureFilter(FeatureIdentifier feature, BiFunction<T, FeatureIdentifier, Optional<T>> map) {
		this.feature = Objects.requireNonNull(feature);
		this.map = Objects.requireNonNull(map);
	}

	@Override
	public ServiceInvocationResult<Collection<T>> apply(ServiceInvocationResult<Collection<T>> original) {
		return new BaseServiceInvocationResult<Collection<T>>(//
				original.diagnostic(), //
				filter(original.data()));
	}

	private Collection<T> filter(Optional<Collection<T>> targets) {
		return targets.map(this::filter).orElse(Collections.emptySet());
	}

	private Collection<T> filter(Collection<T> targets) {
		return targets.stream()//
				.map(target -> map.apply(target, feature))//
				.filter(Optional::isPresent) //
				.map(Optional::get) //
				.collect(Collectors.toCollection(LinkedHashSet::new));
	}

}
