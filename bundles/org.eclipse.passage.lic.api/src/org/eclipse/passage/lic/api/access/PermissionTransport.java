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
package org.eclipse.passage.lic.api.access;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Transport interface for {@link FeaturePermission}(s)
 *
 * @since 0.4.0
 */
public interface PermissionTransport {

	/**
	 * Reads {@link FeaturePermission}(s) from the given {@link InputStream}. 
	 *
	 */
	Iterable<FeaturePermission> readPermissions(InputStream input) throws IOException;

	/**
	 * Writes {@link FeaturePermission}(s) from the given {@link OutputStream}. 
	 *
	 */
	void writePermissions(Iterable<FeaturePermission> conditions, OutputStream output) throws IOException;

}
