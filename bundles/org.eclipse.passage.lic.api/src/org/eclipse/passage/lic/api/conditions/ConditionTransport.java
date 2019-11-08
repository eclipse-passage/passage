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
package org.eclipse.passage.lic.api.conditions;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Transport interface for {@link LicensingCondition}(s).
 *
 * @since 0.4.0
 */
public interface ConditionTransport {

	/**
	 * Reads {@link LicensingCondition}(s) from the given {@link InputStream}. 
	 *
	 * @since 0.4.0
	 */
	Iterable<LicensingCondition> readConditions(InputStream input) throws IOException;

	/**
	 * Writes {@link LicensingCondition}(s) from the given {@link OutputStream}. 
	 *
	 * @since 0.4.0
	 */
	void writeConditions(Iterable<LicensingCondition> conditions, OutputStream output) throws IOException;

}
