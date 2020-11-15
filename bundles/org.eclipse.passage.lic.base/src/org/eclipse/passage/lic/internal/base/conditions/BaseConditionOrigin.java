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

import java.util.Objects;

import org.eclipse.passage.lic.internal.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.internal.api.conditions.ConditionOrigin;

public final class BaseConditionOrigin implements ConditionOrigin {

	private final ConditionMiningTarget miner;
	private final String coordinates;

	public BaseConditionOrigin(ConditionMiningTarget miner, String coordinates) {
		Objects.requireNonNull(miner, "BaseConditionOrigin::miner"); //$NON-NLS-1$
		Objects.requireNonNull(coordinates, "BaseConditionOrigin::coordinates"); //$NON-NLS-1$
		this.miner = miner;
		this.coordinates = coordinates;
	}

	@Override
	public ConditionMiningTarget miner() {
		return miner;
	}

	@Override
	public String coordinates() {
		return coordinates;
	}

}
