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
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;

final class FeatureFilter<T>
		implements Function<ServiceInvocationResult<Collection<T>>, ServiceInvocationResult<Collection<T>>> {

	private final String feature;
	private final BiFunction<T, String, Optional<T>> map;

	FeatureFilter(String feature, BiFunction<T, String, Optional<T>> map) {
		this.feature = feature;
		this.map = map;
	}

	@Override
	public ServiceInvocationResult<Collection<T>> apply(ServiceInvocationResult<Collection<T>> overwhelming) {
		return new BaseServiceInvocationResult<Collection<T>>(//
				overwhelming.diagnostic(), //
				filter(overwhelming.data()));
	}

	private Collection<T> filter(Optional<Collection<T>> targets) {
		return targets.map(this::filter).orElse(Collections.emptySet());
	}

	private Collection<T> filter(Collection<T> targets) {
		return targets.stream()//
				.map(target -> map.apply(target, feature))//
				.filter(Optional::isPresent) //
				.map(Optional::get) //
				.collect(Collectors.toSet());
	}

}
