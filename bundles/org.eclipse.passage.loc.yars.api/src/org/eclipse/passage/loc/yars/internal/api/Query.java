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
 * Contract for meta-information about a {@code fetch}.
 * </p>
 * <p>
 * When a client asks a <i>query</i> to {@linkplain #fetch()}, it actually does
 * not interact with the given {@code storage}, but only instantiate a dedicated
 * {@linkplain FetchedData} instance for the given {@linkplain FetchParams}.
 * </p>
 * <p>
 * Thus the instance of the {@linkplain Query} can be treated as an actual fetch
 * commands factory - reusable instance containing enough meta-information to
 * beget parameterized data requests.
 * </p>
 * 
 * @param <S> type of storage - the query is aware of a storage structure.
 * @param <R> type of resulting data that begotten {@linkplain FetchedData} will
 *            produce.
 * @param <P> particular type of fetch parameters, can be specific both for the
 *            storage and for the query itself.
 * @see org.eclipse.passage.loc.yars.internal.api
 * 
 * @since 0.1
 */
public interface Query<S extends Storage<?>, R, P extends FetchParams> {

	/**
	 * Identifier of a query to be used by a rosters of sorts.
	 *
	 * @since 0.1
	 */
	String id();

	/**
	 * Human readable (under l10n) explanation of a query sense. Can be used in UI
	 * interfaces, if there is a necessity to get a user know the difference between
	 * several queries in disposal.
	 * 
	 * @since 0.1
	 */
	String description();

	/**
	 * <p>
	 * Does not perform a data fetch, but just creates a new instance of a dedicated
	 * {@linkplain FetchedData}, who is an actual fetcher (intended to be
	 * implemented in a lazy way).
	 * </p>
	 * <p>
	 * As a fetcher is storage-aware, so it is constructed with one.
	 * </p>
	 * <p>
	 * There is a way to convey all necessary parameters to a fetch-query by passing
	 * an instance of {@linkplain FetchParams}}. In the case there is no need in
	 * parameterization, use instance of {@linkplain FetchParams.Empty}.
	 * </p>
	 * 
	 * @param storage    target of fetching
	 * @param properties parameters for the fetch-query
	 * 
	 * @see FetchedData
	 * @see FetchParams
	 * @see FetchParams.Empty
	 * 
	 * @since 0.1
	 */
	FetchedData<S, R> fetch(S storage, P properties);

}
