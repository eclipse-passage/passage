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
package org.eclipse.passage.lic.base.requirements;

import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.base.FeatureFilter;

/**
 * @since 2.1
 */
public final class RequirementsFeatureFilter implements Supplier<FeatureFilter<Requirement>> {

	private final String feature;

	public RequirementsFeatureFilter(String feature) {
		this.feature = feature;
	}

	@Override
	public FeatureFilter<Requirement> get() {
		return new FeatureFilter<Requirement>(feature, this::filtered);
	}

	private Optional<Requirement> filtered(Requirement origin, String incoming) {
		return incoming.equals(origin.feature().identifier()) ? Optional.of(origin) : Optional.empty();
	}

}
