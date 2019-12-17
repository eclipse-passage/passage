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
 * Base is an abstraction of an information storage.
 * </p>
 * <p>
 * The interface is parameterized with the type of a single unit of an
 * information.
 * </p>
 * <p>
 * A base can cover user registry or set of files, or be physically organized as
 * the author desires.
 * </p>
 * <p>
 * The only contract here is the type of core entity.
 * </p>
 *
 * @since 0.1
 */
@SuppressWarnings("unused")
public interface Storage<E> {

}
