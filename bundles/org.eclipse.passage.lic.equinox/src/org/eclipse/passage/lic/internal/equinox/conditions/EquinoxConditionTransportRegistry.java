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
package org.eclipse.passage.lic.internal.equinox.conditions;

import java.util.Map;

import org.eclipse.passage.lic.api.conditions.ConditionTransport;
import org.eclipse.passage.lic.api.conditions.ConditionTransportRegistry;
import org.eclipse.passage.lic.base.conditions.BaseConditionTransportRegistry;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

@Component
public class EquinoxConditionTransportRegistry extends BaseConditionTransportRegistry
		implements ConditionTransportRegistry {

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	@Override
	public void registerConditionTransport(ConditionTransport transport, Map<String, Object> properties) {
		super.registerConditionTransport(transport, properties);
	}

	@Override
	public void unregisterConditionTransport(ConditionTransport transport, Map<String, Object> properties) {
		super.unregisterConditionTransport(transport, properties);
	}

}
