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
package org.eclipse.passage.lic.base.conditions;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.runtime.conditions.ConditionTransport;
import org.eclipse.passage.lic.runtime.conditions.ConditionTransportRegistry;

public class BaseConditionTransportRegistry implements ConditionTransportRegistry {

	private final Map<String, ConditionTransport> conditionTransports = new LinkedHashMap<>();

	@Override
	public Iterable<ConditionTransport> getConditionTransports() {
		return conditionTransports.values();
	}

	@Override
	public ConditionTransport getConditionTransportForContentType(String contentType) {
		return conditionTransports.get(contentType);
	}

	@Override
	public void registerConditionTransport(ConditionTransport transport, Map<String, Object> properties) {
		String contentType = String.valueOf(properties.get(LicensingProperties.LICENSING_CONTENT_TYPE));
		conditionTransports.put(contentType, transport);
	}

	@Override
	public void unregisterConditionTransport(ConditionTransport transport, Map<String, Object> properties) {
		String contentType = String.valueOf(properties.get(LicensingProperties.LICENSING_CONTENT_TYPE));
		conditionTransports.remove(contentType, transport);
	}

}
