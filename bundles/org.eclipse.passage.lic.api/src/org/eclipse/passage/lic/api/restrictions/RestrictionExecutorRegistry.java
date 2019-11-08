/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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
package org.eclipse.passage.lic.api.restrictions;

/**
 * Library of {@link RestrictionExecutor}s registered at a program runtime.
 *
 * @see RestrictionExecutor
 * @since 0.4.0
 */
public interface RestrictionExecutorRegistry {

	/**
	 * @since 0.4.0
	 */
	String getDefaultRestrictionLevelIdentifier();

	/**
	 * @since 0.4.0
	 */
	Iterable<String> getRestrictionLevelIdentifiers();

	/**
	 * @since 0.4.0
	 */
	RestrictionLevelDescriptor getRestrictionLevel(String identifier);

	/**
	 * @since 0.4.0
	 */
	Iterable<? extends RestrictionLevelDescriptor> getRestrictionLevels();

}
