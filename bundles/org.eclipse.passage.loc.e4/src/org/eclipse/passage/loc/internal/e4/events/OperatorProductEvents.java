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

import java.security.KeyPair;

import org.osgi.service.event.Event;

public final class OperatorProductEvents {

	/**
	 * Base name of all License Operator events
	 */
	private final String topic;

	/**
	 * Sent when {@link KeyPair} is generated and persisted
	 */
	private final String keyCreated;

	public OperatorProductEvents() {
		topic = "org/eclipse/passage/loc/api/OperatorProductEvents"; //$NON-NLS-1$
		keyCreated = topic + new OperatorEvents().topicSeparator() + "keysCreated"; //$NON-NLS-1$
	}

	public Event keysCreated(String path) {
		return new OperatorEvents().create(keyCreated, path);
	}

	public String topic() {
		return topic;
	}

}
