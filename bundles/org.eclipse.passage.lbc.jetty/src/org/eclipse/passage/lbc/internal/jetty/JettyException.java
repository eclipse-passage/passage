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
package org.eclipse.passage.lbc.internal.jetty;

/**
 * Unrecoverable Jetty Server lifecycle error
 * 
 * @see JettyServer
 *
 */
public class JettyException extends Exception {

	private static final long serialVersionUID = 1L;

	public JettyException(String message, Throwable cause) {
		super(message, cause);
	}

}
