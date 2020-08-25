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
package org.eclipse.passage.lic.internal.equinox.events;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.osgi.service.event.Event;

public final class EquinoxEvent implements Supplier<Event> {

	// @see org.eclipse.e4.core.services.events.IEventBroker.DATA
	private final String key = "org.eclipse.e4.data"; //$NON-NLS-1$

	private final String topic;
	private final Object data;

	public EquinoxEvent(String topic, Object data) {
		this.topic = topic;
		this.data = data;
	}

	@Override
	public Event get() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(key, data);
		return new Event(topic, properties);
	}

}
