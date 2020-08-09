/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.passage.lic.api.conditions.ConditionMiner;
import org.eclipse.passage.lic.api.conditions.ConditionMinerRegistry;

public class BaseConditionMinerRegistry implements ConditionMinerRegistry {

	private final List<ConditionMiner> conditionMiners = new ArrayList<>();

	@Override
	public Iterable<ConditionMiner> getConditionMiners() {
		return Collections.unmodifiableList(conditionMiners);
	}

	@Override
	public void registerConditionMiner(ConditionMiner conditionMiner, Map<String, Object> properties) {
		conditionMiners.add(conditionMiner);
	}

	@Override
	public void unregisterConditionMiner(ConditionMiner conditionMiner, Map<String, Object> properties) {
		conditionMiners.remove(conditionMiner);
	}

	@Override
	public String getConditionMinerTarget(ConditionMiner miner) {
		if (miner instanceof BaseConditionMiner) {
			BaseConditionMiner basePathMiner = (BaseConditionMiner) miner;
			return basePathMiner.getBaseLocation();
		}
		return ""; //$NON-NLS-1$
	}

}
