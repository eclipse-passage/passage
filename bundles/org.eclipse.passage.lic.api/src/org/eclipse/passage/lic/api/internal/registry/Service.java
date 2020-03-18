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
package org.eclipse.passage.lic.api.internal.registry;

/**
 * <p>
 * General notion of a {@code service} interface which can potentially have
 * multiple implementations that need to be somehow <i>collected and managed
 * together</i>.
 * </p>
 * 
 * @param <I> sub type of {@linkplain ServiceId}
 * @param <C> sub type of {@linkplain Configuration}
 */
public interface Service<I extends ServiceId, C extends Configuration> {

	I id();

	C configuration();

}
