/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
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
package org.eclipse.passage.loc.yars.internal.api;

/**
 * <p>
 * Represent data ready for export ({@code written}) to some output.
 * </p>
 * <p>
 * The implementation can handle a bunch of data of type {@code T} (say, result
 * of a fetch) and be responsible for the whole bunch export.
 * </p>
 * <p>
 * It also can represent interests of a single entry of type {@code T} to be
 * exported and be able to {@code write} only this one.
 * </p>
 * <p>
 * Actual export is implemented by {@linkplain ListMedia}
 * </p>
 *
 * @param O represent the type of output format
 * @param T type of data intended to be exported ({@code written})
 *
 * @see ListMedia
 * @since 0.1
 */
public interface ExportData<T> {

	/**
	 * <p>
	 * Export-ready entity must only be able to write it's content (encapsulated
	 * data) to the {@linkplain ListMedia}.
	 * </p>
	 * 
	 * @since 0.1
	 */
	<O> void write(ListMedia<T, O> media);

}
