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
package org.eclipse.passage.loc.runtime;

import org.osgi.service.event.Event;

public class ProductOperatorEvents extends OperatorEvents {

	private ProductOperatorEvents() {
		//block
	}

	/**
	 * Base name of all License Operator events
	 */
	public static final String TOPIC = "org/eclipse/passage/loc/runtime/ProductOperatorEvents"; //$NON-NLS-1$
	
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
