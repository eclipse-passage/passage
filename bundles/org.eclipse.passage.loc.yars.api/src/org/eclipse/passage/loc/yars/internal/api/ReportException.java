/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.loc.yars.internal.api;

/**
 * Covers any error that can happen during a data into a format export.
 * 
 * @since 0.1
 */
@SuppressWarnings("serial") // not designed for serialization
public final class ReportException extends Exception {

	public ReportException(String message, Throwable cause) {
		super(message, cause);
	}

}
