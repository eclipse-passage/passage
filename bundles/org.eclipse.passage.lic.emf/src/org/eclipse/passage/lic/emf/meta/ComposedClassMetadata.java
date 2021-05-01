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
package org.eclipse.passage.lic.emf.meta;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Creates composed class metadata to suitable for dynamic environment
 * 
 * @since 2.0
 */
public final class ComposedClassMetadata implements ComposableClassMetadata {

	private final Set<ClassMetadata> registry;

	public ComposedClassMetadata() {
		registry = new LinkedHashSet<>();
	}

	@Override
	public Optional<EntityMetadata> find(Class<?> clazz) {
		return registry.stream()//
				.map(m -> m.find(clazz))//
				.filter(Optional::isPresent)//
				.map(Optional::get)//
				.findFirst();
	}

	@Override
	public void consider(ClassMetadata fragment) {
		Objects.requireNonNull(fragment);
		registry.add(fragment);
	}

	@Override
	public void forget(ClassMetadata fragment) {
		Objects.requireNonNull(fragment);
		registry.remove(fragment);
	}

}
