/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.emf.migration;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * 
 * @since 2.0
 *
 */
public final class SimpleFeatureRoutes implements EFeatureRoutes {

	private final Map<String, EStructuralFeature> map;

	public SimpleFeatureRoutes() {
		map = new HashMap<>();
	}

	@Override
	public Optional<EStructuralFeature> route(String found, EClass context) {
		Optional<EStructuralFeature> candidate = Optional.ofNullable(map.get(key(found, context)));
		if (candidate.isPresent()) {
			return candidate;
		}
		for (EClass upper : context.getEAllSuperTypes()) {
			Optional<EStructuralFeature> inherited = route(found, upper);
			if (inherited.isPresent()) {
				return inherited;
			}
		}
		return Optional.empty();
	}

	@Override
	public void define(String found, EStructuralFeature destination) {
		map.put(destination.getEContainingClass().getName() + '.' + found, destination);
	}

	private String key(String found, EClass scope) {
		return scope.getName() + '.' + found;
	}

}
