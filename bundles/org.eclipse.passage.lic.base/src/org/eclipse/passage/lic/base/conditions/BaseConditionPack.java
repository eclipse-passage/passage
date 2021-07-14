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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.api.conditions.ConditionOrigin;
import org.eclipse.passage.lic.api.conditions.ConditionPack;

/**
 * @since 1.1
 */
public final class BaseConditionPack implements ConditionPack {

	private final ConditionOrigin origin;
	private final Collection<Condition> conditions;

	public BaseConditionPack(ConditionOrigin origin, Collection<Condition> conditions) {
		Objects.requireNonNull(origin, "BaseConditionPack::origin"); //$NON-NLS-1$
		Objects.requireNonNull(conditions, "BaseConditionPack::conditions"); //$NON-NLS-1$
		this.origin = origin;
		this.conditions = new ArrayList<>(conditions);
	}

	@Override
	public ConditionOrigin origin() {
		return origin;
	}

	@Override
	public Collection<Condition> conditions() {
		return conditions;
	}

}
