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
package org.eclipse.passage.lic.licenses;

/**
 * <p>
 * A <code>"License Grant"</code> is a unit inside a <code>"License Pack"</code>
 * with all the information required to decide regarding permissions for the
 * particular <code>"Feature Version"</code>.
 * <p>
 * 
 * @since 2.0
 * @see PersonalLicensePackDescriptor
 */
public interface PersonalFeatureGrantDescriptor {

	/**
	 * Returns the identifier of this license pack. This is the value of its
	 * <code>"identifier"</code> attribute.
	 *
	 * @return the identifier
	 */
	String getIdentifier();

	FeatureRefDescriptor getFeature();

	ValidityPeriodDescriptor getValid();

	EvaluationInstructionsDescriptor getUserAuthentication();

	/**
	 * Returns the capacity of this license grant. Used for floating license. This
	 * is the value of its <code>"capacity"</code> attribute.
	 *
	 * @return the capacity
	 */
	int getCapacity();

	long getVivid();

	/**
	 * Returns the containing license pack of this license grant.
	 *
	 * @return the license pack
	 */
	PersonalLicensePackDescriptor getPack();

}
