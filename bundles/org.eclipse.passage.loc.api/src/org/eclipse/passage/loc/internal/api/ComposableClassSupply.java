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

/**
 * Allows to compose class supply for different domains
 *
 */
public interface ComposableClassSupply extends ClassSupply {

	/**
	 * Adds domain-specific class supply fragment to consider during search
	 * 
	 * @param fragment the domain-specific class supply, must not be
	 *                 <code>null</code>
	 */
	void consider(ClassSupply fragment);

	/**
	 * Removes domain-specific class supply fragment from consideration
	 * 
	 * @param fragment the domain-specific class supply, must not be
	 *                 <code>null</code>
	 */
	void forget(ClassSupply fragment);
}
