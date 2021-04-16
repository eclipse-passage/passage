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

import java.util.Date;

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
	 * Returns the date of issue of this license pack. This is the value of its
	 * <code>"issueDate"</code> attribute.
	 *
	 * @return the issue date
	 * 
	 * @since 0.5.0
	 */
	Date getIssueDate();

	/**
	 * Returns the <code>"User"</code>" identifier of this license pack. This is the
	 * value of its <code>"userIdentifier"</code> attribute.
	 *
	 * @return the user identifier
	 */
	String getUserIdentifier();

	/**
	 * Returns the <code>"User"</code>" full name of this license pack. This is the
	 * value of its <code>"userFullName"</code> attribute.
	 *
	 * @return the user full name
	 */
	String getUserFullName();

	/**
	 * Returns the request identifier of this license pack, i.e. the initial request
	 * to obtain this license pack. This is the value of its
	 * <code>"requestIdentifier"</code> attribute.
	 *
	 * @return the request identifier
	 * 
	 * @since 0.5.0
	 */
	String getRequestIdentifier();

	/**
	 * Returns the <code>"License Plan"</code>" identifier of this license pack.
	 * This is the value of its <code>"planIdentifier"</code> attribute.
	 *
	 * @return the plan identifier
	 * 
	 * @since 0.5.0
	 */
	String getPlanIdentifier();

	/**
	 * Returns the <code>"ProductRef"</code>" container of this license pack.
	 * 
	 * @since 2.0
	 */
	ProductRefDescriptor getProduct();

	/**
	 * Returns the <code>"License Grant"</code>(s) contained in this license pack.
	 * This is the value of its <code>"licenseGrants"</code> reference.
	 *
	 * @return the license grants
	 */
	Iterable<? extends LicenseGrantDescriptor> getLicenseGrants();

}
