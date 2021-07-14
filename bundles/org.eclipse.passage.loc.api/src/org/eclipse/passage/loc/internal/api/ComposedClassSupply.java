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
package org.eclipse.passage.loc.internal.api;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.eclipse.passage.lic.api.MandatoryService;

/**
 * Creates composed class supply that is suitable for dynamic environment
 * 
 */
public final class ComposedClassSupply implements ComposableClassSupply {

	private final Set<ClassSupply> registry;

	public ComposedClassSupply() {
		registry = new LinkedHashSet<>();
	}

	@Override
	public Optional<InstanceSupply<?>> find(Class<?> clazz, MandatoryService context) {
		for (ClassSupply supply : registry) {
			Optional<InstanceSupply<?>> find = supply.find(clazz, context);
			if (find.isPresent()) {
				return Optional.of(find.get());
			}
		}
		return Optional.empty();
	}

	@Override
	public void consider(ClassSupply fragment) {
		Objects.requireNonNull(fragment);
		registry.add(fragment);
	}

	@Override
	public void forget(ClassSupply fragment) {
		Objects.requireNonNull(fragment);
		registry.remove(fragment);
	}

}
