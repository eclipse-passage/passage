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
package org.eclipse.passage.lic.api.io;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.passage.lic.api.LicensingConfiguration;

/**
 * Provides the key required for given configuration
 *
 */
public interface KeyKeeper {
	
	/**
	 * 
	 * @param configuration
	 * @return the stream to read the key
	 * @throws IOException
	 */
	
	InputStream openKeyStream(LicensingConfiguration configuration) throws IOException;

}
