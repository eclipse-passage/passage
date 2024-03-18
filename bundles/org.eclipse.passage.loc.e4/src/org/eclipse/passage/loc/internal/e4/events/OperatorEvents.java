/*******************************************************************************
 * Copyright (c) 2018, 2024 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support
 *******************************************************************************/
package org.eclipse.passage.loc.internal.e4.events;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.osgi.service.event.Event;

public final class OperatorEvents {

	/**
	 * Topic separator character
	 */
	private final String topicSeparator = "/"; //$NON-NLS-1$

	/**
	 * Wild card character for matching all sub topics
	 */
	private final String allSubTopics = "*"; //$NON-NLS-1$

	public Event create(String topic, Object data) {
		Map<String, Object> properties = new HashMap<>();
		properties.put(IEventBroker.DATA, data);
		Event event = new Event(topic, properties);
		return event;
	}

	public String allSubTopics() {
		return allSubTopics;
	}

	public String topicSeparator() {
		return topicSeparator;
	}

}
