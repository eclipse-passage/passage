/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.api;

/**
 * 
 * Resolves a service instance for a given type if present
 * 
 * @since 2.1
 *
 */
@FunctionalInterface
public interface MandatoryService {

	/**
	 * Resolve a service instance by a given type class
	 * 
	 * @param <T>  a type of a service to resolve
	 * @param type a class of a service to resolve
	 * @return a resolved service instance
	 */
	<T> T get(Class<T> type);

}
