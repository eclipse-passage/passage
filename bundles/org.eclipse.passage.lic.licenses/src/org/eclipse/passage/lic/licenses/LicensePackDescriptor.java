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
package org.eclipse.passage.lic.licenses;

/**
 * <p>
 * A <code>"License Pack"</code> is a container for
 * <code>"License Grant"</code>(s). Created by <code>"Operator"</code> and
 * transferred to the <code>"User"</code> to be evaluated at runtime for the
 * given <code>"Licensing Configuration"</code>.
 * <p>
 * 
 * @since 0.4.0
 * @see LicenseGrantDescriptor
 */
public interface LicensePackDescriptor {

	/**
	 * Returns the identifier of this license pack. This is the value of its
	 * <code>"identifier"</code> attribute.
	 *
	 * @return the identifier
	 */
	String getIdentifier();

	/**
	 * Returns the <code>"License Grant"</code>(s) contained in this license pack.
	 * This is the value of its <code>"licenseGrants"</code> reference.
	 *
	 * @return the license grants
	 */
	Iterable<? extends LicenseGrantDescriptor> getLicenseGrants();

	/**
	 * Returns the <code>"User"</code>" identifier of this license pack. This is the
	 * value of its <code>"userIdentifier"</code> attribute.
	 *
	 * @return the user identifier
	 */
	String getUserIdentifier();

	/**
	 * Returns the <code>"Product"</code>" identifier of this license pack. This is
	 * the value of its <code>"productIdentifier"</code> attribute.
	 *
	 * @return the product identifier
	 */
	String getProductIdentifier();

	/**
	 * Returns the <code>"Product Version"</code>" version of this license pack.
	 * This is the value of its <code>"productVersion"</code> attribute.
	 *
	 * @return the product version
	 */
	String getProductVersion();

}
