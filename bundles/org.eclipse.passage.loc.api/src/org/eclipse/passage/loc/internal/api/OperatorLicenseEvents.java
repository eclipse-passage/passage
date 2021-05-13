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
package org.eclipse.passage.loc.internal.api;

import static org.eclipse.passage.loc.internal.api.OperatorEvents.TOPIC_SEP;
import static org.eclipse.passage.loc.internal.api.OperatorEvents.create;

import org.osgi.service.event.Event;

public final class OperatorLicenseEvents {

	private OperatorLicenseEvents() {
		// block
	}

	/**
	 * Base name of all License Operator events
	 */
	public static final String TOPIC = "org/eclipse/passage/loc/api/OperatorLicenseEvents"; //$NON-NLS-1$

	/**
	 * Sent when decoded {@link PersonalLicensePack} is issued
	 */
	public static final String DECODED_ISSUED = TOPIC + TOPIC_SEP + "decodedIssued"; //$NON-NLS-1$

	/**
	 * Sent when encoded {@link PersonalLicensePack} is issued in encoded form
	 */
	public static final String ENCODED_ISSUED = TOPIC + TOPIC_SEP + "encodedIssued"; //$NON-NLS-1$

	public static Event decodedIssued(String path) {
		return create(DECODED_ISSUED, path);
	}

	public static Event encodedIssued(String path) {
		return create(ENCODED_ISSUED, path);
	}

}
