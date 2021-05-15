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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.emf.ecore.EClass;

/**
 * 
 * @since 2.0
 *
 */
public final class SimpleClassRoutes implements EClassRoutes {

	private final Map<String, EClass> map;

	public SimpleClassRoutes() {
		map = new LinkedHashMap<>();
	}

	@Override
	public void define(String found, EClass destination) {
		map.put(found, destination);
	}

	@Override
	public Map<String, EClass> defined() {
		return new LinkedHashMap<>(map);
	}

	@Override
	public Optional<EClass> route(String found) {
		return Optional.ofNullable(map.get(found));
	}

}
