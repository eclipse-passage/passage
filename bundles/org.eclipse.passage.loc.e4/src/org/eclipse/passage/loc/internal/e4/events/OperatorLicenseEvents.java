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

import org.osgi.service.event.Event;

public final class OperatorLicenseEvents {

	/**
	 * Base name of all License Operator events
	 */
	private final String topic;

	/**
	 * Sent when decoded {@link PersonalLicensePack} is issued
	 */
	private final String decoded;

	/**
	 * Sent when encoded {@link PersonalLicensePack} is issued in encoded form
	 */
	private final String encoded;

	public OperatorLicenseEvents() {
		topic = "org/eclipse/passage/loc/api/OperatorLicenseEvents"; //$NON-NLS-1$
		String separator = new OperatorEvents().topicSeparator();
		decoded = topic + separator + "decodedIssued"; //$NON-NLS-1$
		encoded = topic + separator + "encodedIssued"; //$NON-NLS-1$
	}

	public Event decodedIssued(String path) {
		return new OperatorEvents().create(decoded, path);
	}

	public Event encodedIssued(String path) {
		return new OperatorEvents().create(encoded, path);
	}

	public String topic() {
		return topic;
	}

}
