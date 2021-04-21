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
package org.eclipse.passage.lic.internal.licenses.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.emf.ecore.EStructuralFeature;

public final class SimpleFeatureRoute implements EFeatureRoute {

	private final Map<String, EStructuralFeature> map;

	public SimpleFeatureRoute() {
		map = new HashMap<>();
	}

	@Override
	public Optional<EStructuralFeature> apply(String found) {
		return Optional.ofNullable(map.get(found));
	}

	@Override
	public void add(String found, EStructuralFeature destination) {
		map.put(found, destination);
	}

}
