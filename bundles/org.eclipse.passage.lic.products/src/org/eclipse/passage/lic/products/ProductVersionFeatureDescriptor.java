/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
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
package org.eclipse.passage.lic.products;

import org.eclipse.passage.lic.api.restrictions.RestrictionLevel;

/**
 * <p>
 * A <code>"Product Version Feature"</code> describes the state of functionality
 * included to the <code>"Product Version"</code> . It is used as a reference to
 * formulate <code>"License Plan"</code>.
 * <p>
 *
 */
public interface ProductVersionFeatureDescriptor {

	/**
	 * Returns the feature identifier of this product version feature. This is the
	 * value of its <code>"featureIdentifier"</code> attribute.
	 *
	 * @return the feature identifier
	 */
	String getFeatureIdentifier();

	/**
	 * Returns the feature version of this product version feature. This is the
	 * value of its <code>"featureVersion"</code> attribute.
	 *
	 * @return the feature version
	 */
	String getFeatureVersion();

	/**
	 * Returns the restriction level of this product version feature. This is the
	 * value of its <code>"restrictionLevel"</code> attribute.
	 *
	 * @return the restriction level
	 * 
	 * @see RestrictionLevel
	 */
	String getRestrictionLevel();

	ProductVersionDescriptor getProductVersion();

}
