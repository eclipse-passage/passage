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

import static org.eclipse.passage.loc.internal.api.BaseLicensingResult.CODE_NOMINAL;
import static org.eclipse.passage.loc.internal.api.LicensingResult.ERROR;
import static org.eclipse.passage.loc.internal.api.LicensingResult.OK;

/**
 * 
 * @since 0.5.0
 * @deprecated
 */
@Deprecated
public final class LicensingResults {

	private LicensingResults() {
		// block
	}

	public static LicensingResult createOK(String message) {
		return new BaseLicensingResult(OK, message, LicensingResults.class.getName());
	}

	/**
	 * Creates a {@link LicensingResult} with {@link LicensingResult#ERROR} severity
	 * for the given message source and throwable data
	 * 
	 * @param message   a human-readable message, localized to the current locale
	 * @param source    the unique identifier of the reporter
	 * @param exception a low-level exception
	 * @return the licensing result object
	 * 
	 * @see LicensingResult
	 */
	public static LicensingResult createError(String message, String source, Throwable exception) {
		return new BaseLicensingResult(ERROR, message, CODE_NOMINAL, source, exception);
	}

}
