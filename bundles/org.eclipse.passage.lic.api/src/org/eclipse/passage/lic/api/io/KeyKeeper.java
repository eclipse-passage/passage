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
package org.eclipse.passage.lic.api.io;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.passage.lic.api.LicensingConfiguration;

/**
 * Provides the key required for given configuration
 *
 * @since 0.4.0
 */
public interface KeyKeeper {

	/**
	 * Create a stream for key reading
	 *
	 * @param configuration general licensing configuration of a running product
	 * @return the stream to read the key
	 * @throws IOException in case of any file system operation misbehaviour
	 * @since 0.4.0
	 */
	InputStream openKeyStream(LicensingConfiguration configuration) throws IOException;

}
