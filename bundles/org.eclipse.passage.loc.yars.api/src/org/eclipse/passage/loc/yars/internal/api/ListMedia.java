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
 * {@code ListMedia} is designed to be used by {@linkplain ExportData}
 * implementors.
 * </p>
 * 
 * @param T type of a single entity in a <i>list</i>
 * @see ExportData
 * @see org.eclipse.passage.loc.yars.internal.api
 * @since 0.1
 */
public interface ListMedia<T> {

	/**
	 * <p>
	 * Intended to be called ones on the start of an export process, prior any data
	 * is processed.
	 * </p>
	 * <p>
	 * Implement a one-per-export actions here, like CSV header writing
	 * </p>
	 * 
	 * @since 0.1
	 */
	void start() throws ReportException;

	/**
	 * <p>
	 * Intended to be called ones after the actual data are all processed.
	 * </p>
	 * <p>
	 * Implement a <i>post scriptum</i> actions invocation here, like analytics or
	 * signature appending.
	 * </p>
	 * 
	 * @since 0.1
	 */
	void finish() throws ReportException;

	/**
	 * <p>
	 * Is called one per an data entry, prior it's processing.
	 * </p>
	 * 
	 * @since 0.1
	 */
	void startNode(T node) throws ReportException;

	/**
	 * <p>
	 * Is called one per an data entry, after it is processed.
	 * </p>
	 * 
	 * @since 0.1
	 */
	void finishNode(T node) throws ReportException;

	/**
	 * Is called to each data property intended to be processed
	 * 
	 * @since 0.1
	 */
	void inner(String data, String name) throws ReportException;

}
