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
 * <p>
 * Represents a final export format, like CSV file (s) or JSON entry.
 * </p>
 * <p>
 * Intended to export ({@code write} to an output) <i>list</i>-like collection
 * (something iterable) of a single type ({@code T})
 * </p>
 * <p>
 * ListMedia is designed to be used by {@linkplain ExportData} implementors. It
 * has at least two kinds of clients:
 * </p>
 * 
 * @param T type of a single entity in a <i>list</i>
 * @param C output format type
 * 
 * @see ExportData
 * @see org.eclipse.passage.loc.yars.internal.api
 * @since 0.1
 */
public interface ListMedia<T> {

	/**
	 * @since 0.1
	 */
	void start() throws ReportException;

	/**
	 * @since 0.1
	 */
	void finish() throws ReportException;

	/**
	 * @since 0.1
	 */
	void startNode(T node) throws ReportException;

	/**
	 * @since 0.1
	 */
	void finishNode(T node) throws ReportException;

	/**
	 * @since 0.1
	 */
	void inner(String data, String name) throws ReportException;

}
