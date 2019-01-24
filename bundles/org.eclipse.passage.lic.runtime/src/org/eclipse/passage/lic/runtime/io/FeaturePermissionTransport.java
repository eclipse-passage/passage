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
package org.eclipse.passage.lic.runtime.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.passage.lic.runtime.FeaturePermission;

/**
 * Transport interface for {@link FeaturePermission}(s)
 */
public interface FeaturePermissionTransport {

	/**
	 * Reads {@link FeaturePermission}(s) from the given {@link InputStream}. 
	 *
	 */
	Iterable<FeaturePermission> readFeaturePermissions(InputStream input) throws IOException;

	/**
	 * Writes {@link FeaturePermission}(s) from the given {@link OutputStream}. 
	 *
	 */
	void writeFeaturePermissions(Iterable<FeaturePermission> conditions, OutputStream output) throws IOException;

}
