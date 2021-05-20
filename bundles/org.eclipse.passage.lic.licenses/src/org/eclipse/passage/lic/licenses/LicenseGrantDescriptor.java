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
package org.eclipse.passage.lic.licenses;

/**
 * <p>
 * A <code>"License Grant"</code> is a unit inside a <code>"License Pack"</code>
 * with all the information required to decide regarding permissions for the
 * particular <code>"Feature Version"</code>.
 * <p>
 * 
 * @since 0.4.0
 * @see PersonalLicensePackDescriptor
 */
public interface LicenseGrantDescriptor {

	/**
	 * Returns the identifier of this license pack. This is the value of its
	 * <code>"identifier"</code> attribute.
	 *
	 * @return the identifier
	 * @since 1.0
	 */
	String getIdentifier();

	/**
	 * @since 2.0
	 */
	FeatureRefDescriptor getFeature();

	/**
	 * @since 2.0
	 */
	ValidityPeriodDescriptor getValid();

	/**
	 * @since 2.0
	 */
	EvaluationInstructionsDescriptor getUserAuthentication();

	/**
	 * Returns the capacity of this license grant. Used for floating license. This
	 * is the value of its <code>"capacity"</code> attribute.
	 *
	 * @return the capacity
	 */
	int getCapacity();

	/**
	 * Returns the containing license pack of this license grant.
	 *
	 * @return the license pack
	 * @since 2.0
	 */
	PersonalLicensePackDescriptor getLicensePack();

}
