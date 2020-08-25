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
package org.eclipse.passage.lic.base;

import org.eclipse.passage.lic.api.LicensingResult;

public class SystemReporter {

	public static final SystemReporter INSTANCE = new SystemReporter();

	public void logResult(LicensingResult result) {
		int severity = result.getSeverity();
		String message = result.getMessage();
		Throwable exception = result.getException();
		switch (severity) {
		case LicensingResult.WARNING:
		case LicensingResult.ERROR:
		case LicensingResult.CANCEL:
			if (message != null) {
				System.err.println(message);
			}
			if (exception != null) {
				exception.printStackTrace();
			}
			break;
		case LicensingResult.OK:
		case LicensingResult.INFO:
			break;

		default:
			System.err.println(result);
			break;
		}
	}

}
