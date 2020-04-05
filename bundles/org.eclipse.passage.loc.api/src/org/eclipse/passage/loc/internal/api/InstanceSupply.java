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
package org.eclipse.passage.loc.internal.api;

import java.util.Optional;

/**
 * 
 * Supplies instance of the given type, typically
 *
 * @param <T> a type of supplied instance
 */
@FunctionalInterface
public interface InstanceSupply<T> {

	/**
	 * Triggers a process of instance selection or instance creation that may
	 * require user interaction
	 * 
	 * @return a selected or created instance or empty {@link Optional}
	 */
	Optional<T> supply();

}
