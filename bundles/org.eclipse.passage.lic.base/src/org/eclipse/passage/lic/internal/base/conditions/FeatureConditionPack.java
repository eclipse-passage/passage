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
package org.eclipse.passage.lic.internal.base.conditions;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.ConditionOrigin;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;

/**
 * Subset of a {@code parent} {@linkplain ConditionPack} contained
 * {@linkplain Condition}s for single feature.
 */
public final class FeatureConditionPack implements ConditionPack {

	private final ConditionPack parent;
	private final String feature;

	public FeatureConditionPack(ConditionPack parent, String feature) {
		Objects.requireNonNull(parent, "FeatureConditionPack::parent"); //$NON-NLS-1$
		Objects.requireNonNull(feature, "FeatureConditionPack::feature"); //$NON-NLS-1$
		this.parent = parent;
		this.feature = feature;
	}

	@Override
	public ConditionOrigin origin() {
		return parent.origin();
	}

	@Override
	public Collection<Condition> conditions() {
		// FIXME: work for CachingFunction
		return parent.conditions().stream()//
				.filter(condition -> condition.feature().equals(feature))//
				.collect(Collectors.toSet());
	}

}
