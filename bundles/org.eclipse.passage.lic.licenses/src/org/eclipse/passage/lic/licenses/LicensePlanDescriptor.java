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

import java.util.List;

/**
 * <p>
 * A <code>"License Plan"</code> describes the what is allowed to use for the
 * given <code>"Licensing Configuration"</code>. Used as a 'template' to create
 * <code>"License Pack"</code>
 * <p>
 * 
 * @since 0.5.0
 * @see PersonalLicensePackDescriptor
 */
public interface LicensePlanDescriptor {

	/**
	 * Returns the identifier of this license plan. This is the value of its
	 * <code>"identifier"</code> attribute.
	 *
	 * @return the identifier
	 */
	String getIdentifier();

	/**
	 * Returns the name of this license plan. This is the value of its
	 * <code>"name"</code> attribute.
	 *
	 * @return the name
	 */
	String getName();

	/**
	 * Returns the description of this license plan. This is the value of its
	 * <code>"description"</code> attribute.
	 *
	 * @return the description
	 */
	String getDescription();

	/**
	 * @since 2.1
	 */
	List<String> getAgreements();

	/**
	 * Returns the <code>"License Plan Features"</code>(s) contained in this license
	 * plan. This is the value of its <code>"licensePlanFeatures"</code> reference.
	 *
	 * @return the license plan features
	 * @since 2.0
	 */
	List<? extends LicensePlanFeatureDescriptor> getFeatures();

	/**
	 * @since 2.0
	 */
	List<? extends PersonalLicensePackDescriptor> getPersonal();

	/**
	 * @since 2.0
	 */
	List<? extends FloatingLicensePackDescriptor> getFloating();

}
