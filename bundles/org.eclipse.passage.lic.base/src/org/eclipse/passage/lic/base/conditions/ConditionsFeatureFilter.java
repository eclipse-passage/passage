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
package org.eclipse.passage.lic.base.conditions;

import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.conditions.ConditionPack;
import org.eclipse.passage.lic.base.FeatureFilter;

/**
 * @since 2.1
 */
public final class ConditionsFeatureFilter implements Supplier<FeatureFilter<ConditionPack>> {

	private final String feature;

	public ConditionsFeatureFilter(String feature) {
		this.feature = feature;
	}

	@Override
	public FeatureFilter<ConditionPack> get() {
		return new FeatureFilter<ConditionPack>(feature, this::filtered);
	}

	private Optional<ConditionPack> filtered(ConditionPack pack, String incoming) {
		ConditionPack filtered = new FeatureConditionPack(pack, incoming);
		return filtered.conditions().isEmpty() ? Optional.empty() : Optional.of(filtered);
	}

}
