/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.net.api.handle;

import java.io.IOException;

public interface NetRequest {

	String parameter(String name);

	/**
	 * Returns the address of the remote that send this request.
	 * <p>
	 * By default, this is the first hop of the underlying network connection, but
	 * it may be wrapped to represent a more remote end point.
	 * </p>
	 */
	String remoteAddress();

	byte[] content() throws IOException;

}
