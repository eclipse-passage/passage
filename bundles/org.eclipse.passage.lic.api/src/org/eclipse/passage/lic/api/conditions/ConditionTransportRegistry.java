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

/**
 * Contract for a registry of {@link ConditionTransport}s.
 *
 * <p>As {@link LicensingCondition} can be persisted in any form, a {@link ConditionTransport}
 * is associated with a particular  <i>content type</i> (like <i>application/json</i> or <i>application/xmk</i>).
 * </p>
 *
 * @see ConditionTransport
 * @see LicensingCondition
 * */
public interface ConditionTransportRegistry {

	/**
	 * Returns an aggregate of all registered {@link ConditionTransport}s.
	 *
	 * @return all registered {@link ConditionTransport}s.
	 * */
	Iterable<ConditionTransport> getConditionTransports();

	/**
	 * <p>
	 * Returns {@link ConditionTransport}, which is registered to handle (read and write)
	 * {@link LicensingCondition}s in the given {@code contentType}. The value is nullable.
	 * </p>
	 *
	 * @return a {@link ConditionTransport} registered for the given {@code contentType}, if any, and {@code null} otherwise.
	 * @see #registerConditionTransport(ConditionTransport, Map)
	 * */
	ConditionTransport getConditionTransportForContentType(String contentType);

	/**
	 * Appends the {@code transport} to the <i>registry</i> with the given set of properties (like content type).
	 * */
	void registerConditionTransport(ConditionTransport transport, Map<String, Object> properties);

	/**
	 * Removes the {@code transport} from the <i>registry</i>.
	 * */
	void unregisterConditionTransport(ConditionTransport transport, Map<String, Object> properties);

}
