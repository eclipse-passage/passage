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
package org.eclipse.passage.lic.runtime;

/**
 * Licensing reporting service used to report the result of licensing activity.
 *
 */
public interface LicensingReporter {

	/**
	 * Log the result
	 * 
	 * @param result
	 */
	void logResult(LicensingResult result);

	/**
	 * Post the result (asynchronous delivery)
	 * 
	 * @param result
	 */
	void postResult(LicensingResult result);

	/**
	 * Send the result (synchronous delivery)
	 * 
	 * @param result
	 */
	void sendResult(LicensingResult result);

}
