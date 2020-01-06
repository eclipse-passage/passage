/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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
package org.eclipse.passage.loc.api;

import static org.eclipse.passage.loc.api.OperatorEvents.TOPIC_SEP;
import static org.eclipse.passage.loc.api.OperatorEvents.create;

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
	 * Sent when {@link LicensePack} is issued in decoded form
	 */
	public static final String PUBLIC_CREATED = TOPIC + TOPIC_SEP + "publicCreated"; //$NON-NLS-1$

	/**
	 * Sent when {@link LicensePack} is issued in encoded form
	 */
	public static final String PRIVATE_CREATED = TOPIC + TOPIC_SEP + "privateCreated"; //$NON-NLS-1$

	public static Event publicCreated(String path) {
		return create(PUBLIC_CREATED, path);
	}

	public static Event privateCreated(String path) {
		return create(PRIVATE_CREATED, path);
	}

}
