/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
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
package org.eclipse.passage.lic.api;

/**
 * <p>A checked exception representing a failure.</p>
 * <p> Licensing exceptions contain a result object describing the cause of the exception. </p>
 * 
 * @see LicensingResult
 * @since 0.4.0
 */
public class LicensingException extends Exception {

	private static final long serialVersionUID = 1L;

	private LicensingResult diagnostic;

	/**
	 * @since 0.4.0
	 */
	public LicensingException(LicensingResult result) {
		super(result.getMessage(), result.getException());
		this.diagnostic = result;
	}

	/**
	 * @since 0.4.0
	 */
	public final LicensingResult getResult() {
		return diagnostic;
	}
}
