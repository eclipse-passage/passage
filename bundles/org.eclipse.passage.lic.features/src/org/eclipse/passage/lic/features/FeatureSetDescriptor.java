/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.features;

/**
 * <p>
 * The <code>"Feature Set"</code> groups the related functionality that may be
 * distributed via several <code>"Products"</code>.
 * <p>
 *
 */
public interface FeatureSetDescriptor {

	/**
	 * Returns the identifier of this feature set. This is the value of its
	 * <code>"identifier"</code> attribute.
	 *
	 * @return the identifier
	 */
	String getIdentifier();

	/**
	 * Returns the name of this feature set. This is the value of its
	 * <code>"name"</code> attribute.
	 *
	 * @return the name
	 */
	String getName();

	/**
	 * Returns the description of this feature set. This is the value of its
	 * <code>"description"</code> attribute.
	 *
	 * @return the description
	 */
	String getDescription();

	Iterable<? extends FeatureDescriptor> getFeatures();

}
