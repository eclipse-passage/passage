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
 * {@code Storage} is an abstraction of an information base.
 * </p>
 * <p>
 * The interface is parameterized with the type of a single unit of an
 * information ({@code E}).
 * </p>
 * <p>
 * A {@code storage} can cover user registry or set of files, or be physically
 * organized as the author desires.
 * </p>
 * <p>
 * The only contract here is the type of entity whose interests a
 * {@code storage} protects.
 * </p>
 * 
 * @param <E> type of entries covered by the {@code storage}. Here we declare
 *            that a storage is dedicated to a single data type and does not
 *            represent a universal data base of sort.
 * @see org.eclipse.passage.loc.yars.internal.api
 * @since 0.1
 */
public interface Storage<E> {

}
