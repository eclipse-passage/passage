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
package org.eclipse.passage.lic.internal.equinox;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.passage.lic.api.LicensingEvents;
import org.eclipse.passage.lic.api.LicensingResult;
import org.osgi.service.event.Event;

public class EquinoxEvents {

	// @see org.eclipse.e4.core.services.events.IEventBroker.DATA
	public static final String PROPERTY_DATA = "org.eclipse.e4.data"; //$NON-NLS-1$

	public static Event createEvent(String topic, Object data) {
		Map<String, Object> properties = new HashMap<>();
		properties.put(PROPERTY_DATA, data);
		Event event = new Event(topic, properties);
		return event;
	}

	public static Event extractEvent(LicensingResult result) {
		Object attachmentTopic = result.getAttachment(LicensingEvents.PROPERTY_TOPIC);
		if (attachmentTopic instanceof String) {
			String topic = (String) attachmentTopic;
			Object data = result.getAttachment(LicensingEvents.PROPERTY_DATA);
			Map<String, Object> properties = new HashMap<>();
			properties.put(PROPERTY_DATA, data);
			// FIXME: we may need more attributes from LicensingResult
			properties.put("org.eclipse.passage.lic.api.event.code", result.getCode()); //$NON-NLS-1$
			return new Event(topic, properties);
		}
		return null;
	}

}
