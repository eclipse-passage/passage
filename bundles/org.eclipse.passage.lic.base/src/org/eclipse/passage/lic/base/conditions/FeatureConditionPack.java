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

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.agreements.GlobalAgreement;
import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.api.conditions.ConditionOrigin;
import org.eclipse.passage.lic.api.conditions.ConditionPack;

/**
 * Subset of a {@code parent} {@linkplain ConditionPack} contained
 * {@linkplain Condition}s for single feature.
 * 
 * @since 2.1
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

	@Override
	public Collection<GlobalAgreement> agreements() {
		return parent.agreements(); // these are global (external for the product) and mandatory for each feature
	}

}
