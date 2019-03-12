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
package org.eclipse.passage.lic.internal.equinox.conditions;

import java.util.Map;

import org.eclipse.passage.lic.base.conditions.BaseConditionMinerRegistry;
import org.eclipse.passage.lic.runtime.conditions.ConditionMiner;
import org.eclipse.passage.lic.runtime.conditions.ConditionMinerRegistry;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

@Component
public class EquinoxConditionMinerRegistry extends BaseConditionMinerRegistry implements ConditionMinerRegistry {

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	@Override
	public void registerConditionMiner(ConditionMiner conditionMiner, Map<String, Object> properties) {
		super.registerConditionMiner(conditionMiner, properties);
	}

	@Override
	public void unregisterConditionMiner(ConditionMiner conditionMiner, Map<String, Object> properties) {
		super.unregisterConditionMiner(conditionMiner, properties);
	}

}
