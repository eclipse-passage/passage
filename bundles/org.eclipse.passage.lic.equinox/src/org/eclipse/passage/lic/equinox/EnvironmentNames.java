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
package org.eclipse.passage.lic.equinox;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.EvaluationType;
import org.eclipse.passage.lic.api.inspection.RuntimeEnvironment;

/**
 * @since 2.1
 */
public final class EnvironmentNames implements Supplier<List<String>> {

	// FIXME: work for caching supplier
	private final List<String> names;

	public EnvironmentNames() {
		this.names = new Environments().get().stream()//
				.map(RuntimeEnvironment::id)//
				.map(EvaluationType::identifier)//
				.collect(Collectors.toList());
	}

	@Override
	public List<String> get() {
		return names;
	}

}
