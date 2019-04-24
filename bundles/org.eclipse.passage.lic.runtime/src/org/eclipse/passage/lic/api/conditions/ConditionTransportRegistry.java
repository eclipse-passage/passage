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

public interface ConditionTransportRegistry {

	Iterable<ConditionTransport> getConditionTransports();

	ConditionTransport getConditionTransportForContentType(String contentType);

	void registerConditionTransport(ConditionTransport transport, Map<String, Object> properties);

	void unregisterConditionTransport(ConditionTransport transport, Map<String, Object> properties);

}
