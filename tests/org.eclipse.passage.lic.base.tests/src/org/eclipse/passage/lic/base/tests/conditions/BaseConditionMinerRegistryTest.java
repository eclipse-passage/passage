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
package org.eclipse.passage.lic.base.tests.conditions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.nio.file.Path;

import org.eclipse.passage.lic.base.conditions.BaseConditionMinerRegistry;
import org.eclipse.passage.lic.base.conditions.BasePathConditionMiner;
import org.eclipse.passage.lic.runtime.conditions.ConditionMiner;
import org.junit.Test;

public class BaseConditionMinerRegistryTest {

	private static final ConditionMiner MINER1 = new BasePathConditionMiner() {

		@Override
		protected Path getBasePath() {
			return null;
		}
	};

	private static final ConditionMiner MINER2 = new BasePathConditionMiner() {

		@Override
		protected Path getBasePath() {
			return null;
		}
	};

	@Test
	public void testBindUnbind() {
		BaseConditionMinerRegistry registry = new BaseConditionMinerRegistry();
		Iterable<ConditionMiner> conditionMiners = registry.getConditionMiners();
		assertFalse(conditionMiners.iterator().hasNext());
		registry.registerConditionMiner(MINER1, null);
		conditionMiners = registry.getConditionMiners();
		assertEquals(MINER1, conditionMiners.iterator().next());

		registry.unregisterConditionMiner(MINER2, null);
		conditionMiners = registry.getConditionMiners();
		assertEquals(MINER1, conditionMiners.iterator().next());

		registry.unregisterConditionMiner(MINER1, null);
		conditionMiners = registry.getConditionMiners();
		assertFalse(conditionMiners.iterator().hasNext());
	}
}
