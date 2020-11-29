/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lbc.internal.api;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @since 1.0
 */
public interface FloatingResponse {

	boolean failed();

	Error error();

	boolean carriesPayload();

	/**
	 * Leave the stream open - must be closed by a calling party.
	 * 
	 * @param output sink-stream for the response payload
	 */
	void write(OutputStream output) throws IOException;

	public static interface Error {

		int code();

		String message();
	}
}
