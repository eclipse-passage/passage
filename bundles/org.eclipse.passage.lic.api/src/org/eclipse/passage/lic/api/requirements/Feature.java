/*******************************************************************************
 * Copyright (c) 2020, 2024 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support and improvements
 *******************************************************************************/
package org.eclipse.passage.lic.api.requirements;

import org.eclipse.passage.lic.api.FeatureIdentifier;

/**
 * <p>
 * Describes a <i> feature under licensing</i> in context of access cycle.
 * </p>
 * <p>
 * Intended to be implemented as a <i>data-class</i>.
 * </p>
 * 
 * @see Requirement
 * @see org.eclipse.passage.lic.api
 * @since 2.1
 */
public interface Feature {

	/**
	 * Name of the feature under requirement
	 */
	String name();

	/**
	 * Version of the feature under requirement
	 */
	String version();

	/**
	 * Identifier of the feature under licensing
	 * 
	 * @since 4.0
	 */
	FeatureIdentifier identifier();

	/**
	 * Freely named provider of the feature
	 */
	String provider();

}
