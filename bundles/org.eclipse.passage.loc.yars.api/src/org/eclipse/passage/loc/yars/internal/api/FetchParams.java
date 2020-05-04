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
 * {@linkplain Query} - specific parameters for a fetcher.
 * </p>
 * 
 * <p>
 * For example, a pagination query can make use of {@code params} if each
 * {@linkplain Query#fetch(Storage, FetchParams)} is requested for the next
 * <i>page</i> parameter, like, say, {@code pageNumber} and {@code pageSize}.
 * </p>
 * 
 * @see Query#fetch(Storage, FetchParams)
 * @see org.eclipse.passage.loc.yars.internal.api
 * @since 0.1
 */
public interface FetchParams {

	/**
	 * In wide variety of cases there is no need in parameterization for a query.
	 * This class implements the no-parameters instance.
	 * 
	 * @see FetchParams
	 * @see org.eclipse.passage.loc.yars.internal.api
	 * @since 0.1
	 */
	final class Empty implements FetchParams {

	}
}
