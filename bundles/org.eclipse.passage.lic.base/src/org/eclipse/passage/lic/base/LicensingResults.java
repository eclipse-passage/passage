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
package org.eclipse.passage.lic.base;

import static org.eclipse.passage.lic.base.BaseLicensingResult.CODE_NOMINAL;
import static org.eclipse.passage.lic.runtime.LicensingResult.ERROR;
import static org.eclipse.passage.lic.runtime.LicensingResult.OK;

public class LicensingResults {

	public static BaseLicensingResult createHolder(String message) {
		return new BaseLicensingResult(OK, message, LicensingResults.class.getName());
	}

	public static BaseLicensingResult createHolder(String message, String source) {
		return new BaseLicensingResult(OK, message, source);
	}

	public static BaseLicensingResult createError(String message, Throwable e) {
		String source = LicensingResults.class.getName();
		return new BaseLicensingResult(ERROR, message, CODE_NOMINAL, source, e);
	}

	public static BaseLicensingResult createError(String message, String source, Throwable e) {
		return new BaseLicensingResult(ERROR, message, CODE_NOMINAL, source, e);
	}

	public static BaseLicensingResult createError(String message, int code, Throwable e) {
		String source = LicensingResults.class.getName();
		return new BaseLicensingResult(ERROR, message, code, source, e);
	}

	public static BaseLicensingResult createError(String message, int code, String source, Throwable e) {
		return new BaseLicensingResult(ERROR, message, code, source, e);
	}

}
