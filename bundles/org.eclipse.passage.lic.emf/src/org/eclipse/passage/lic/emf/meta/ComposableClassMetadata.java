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
package org.eclipse.passage.lic.emf.meta;

/**
 * Allows to compose class metadata for different domains
 *
 * @since 2.0
 */
public interface ComposableClassMetadata extends ClassMetadata {

	/**
	 * Adds domain-specific metadata fragment to consider during search
	 * 
	 * @param fragment the domain-specific metadata, must not be <code>null</code>
	 */
	void consider(ClassMetadata fragment);

	/**
	 * Removes domain-specific metadata fragment from consideration
	 * 
	 * @param fragment the domain-specific metadata, must not be <code>null</code>
	 */
	void forget(ClassMetadata fragment);
}
