/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
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
package org.eclipse.passage.loc.internal.api;

import static org.eclipse.passage.loc.internal.api.OperatorEvents.TOPIC_SEP;
import static org.eclipse.passage.loc.internal.api.OperatorEvents.create;

import java.security.KeyPair;

import org.osgi.service.event.Event;

public final class OperatorProductEvents {

	private OperatorProductEvents() {
		// block
	}

	/**
	 * Base name of all License Operator events
	 */
	public static final String TOPIC = "org/eclipse/passage/loc/api/OperatorProductEvents"; //$NON-NLS-1$

	/**
	 * Sent when {@link KeyPair} is generated and persisted
	 */
	public static final String KEYS_CREATED = TOPIC + TOPIC_SEP + "keysCreated"; //$NON-NLS-1$

	public static Event keysCreated(String path) {
		return create(KEYS_CREATED, path);
	}

}
