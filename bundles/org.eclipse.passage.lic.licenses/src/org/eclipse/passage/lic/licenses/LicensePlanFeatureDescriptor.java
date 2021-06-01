/*******************************************************************************
 * Copyright (c) 2019, 2021 ArSysOp
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
package org.eclipse.passage.lic.licenses;

/**
 * <p>
 * A <code>"License Pack Feature"</code> is a unit inside a
 * <code>"License Plan"</code> that describes a <code>"Feature Version"</code>
 * allowed to use. Used as a 'template' to create <code>"License Grant"</code>
 * <p>
 * 
 * @since 0.5.0
 * @see PersonalFeatureGrantDescriptor
 */
public interface LicensePlanFeatureDescriptor {

	/**
	 * @since 2.0
	 */
	FeatureRefDescriptor getFeature();

	/**
	 * @since 2.0
	 */
	int getCapacity();

	/**
	 * @since 2.0
	 */
	long getVivid();

	/**
	 * Returns the containing license plan of this license plan feature.
	 *
	 * @return the license plan
	 * @since 2.0
	 */
	LicensePlanDescriptor getPlan();

}
