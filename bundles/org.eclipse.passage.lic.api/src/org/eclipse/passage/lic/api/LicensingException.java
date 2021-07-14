/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.api;

import java.time.ZonedDateTime;

/**
 * As licensing runtime is a complicated and infrastructure-dependent, wide
 * variety of things can occasionally go south. This general purpose exception
 * serves misbehaviors in crucial parts.
 * 
 * @since 2.1
 */
public final class LicensingException extends Exception {

	/**
	 * generated
	 */
	private static final long serialVersionUID = 7746884069745071894L;

	private final ZonedDateTime stamp;

	public LicensingException(String message, Throwable cause) {
		super(message, cause);
		stamp = ZonedDateTime.now();
	}

	public LicensingException(String message) {
		super(message);
		stamp = ZonedDateTime.now();
	}

	public LicensingException(Throwable cause) {
		super(cause);
		stamp = ZonedDateTime.now();
	}

	public ZonedDateTime stamp() {
		return stamp;
	}

}
