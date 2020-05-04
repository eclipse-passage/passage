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

import java.util.List;

/**
 * <p>
 * Aware of a particular {@linkplain Storage} structure, able to fetch data from
 * it according to encapsulated business logic.
 * </p>
 * <p>
 * Fetching details are hidden and can be as complicated as a case required.
 * Type of final the result can differ from the original type of stored entries.
 * </p>
 *
 * @param <S> a precise type of {@linkplain Storage}
 * @param <R> type of processing result, is not obliged to be equal with the
 *            type of stored entries. If the fetched data is intended to be
 *            further exported ({@code written}), then {@linkplain ExportData}
 *            is recommended to be used as {@code R}
 * @see org.eclipse.passage.loc.yars.internal.api
 * @since 0.1
 */
public interface FetchedData<S extends Storage<?>, R> {

	List<R> get();

}
