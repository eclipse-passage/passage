/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.api.conditions;

import java.util.Map;

import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.LicensingResult;

public interface ConditionMinerRegistry {

	Iterable<ConditionMiner> getConditionMiners();

	void registerConditionMiner(ConditionMiner conditionMiner, Map<String, Object> properties);

	void unregisterConditionMiner(ConditionMiner conditionMiner, Map<String, Object> properties);

	LicensingResult importConditions(String source, LicensingConfiguration configuration);

	String getConditionMinerTarget(ConditionMiner miner);

}
