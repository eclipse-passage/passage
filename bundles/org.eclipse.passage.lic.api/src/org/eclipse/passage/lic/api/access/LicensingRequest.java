/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.lic.api.access;

import java.util.Date;

/**
 * Collects the data required to issue the license
 *
 * @since 0.5.0
 */
public interface LicensingRequest {

	/**
	 * Returns the identifier of this licensing request. This is the value of its
	 * <code>"identifier"</code> attribute.
	 *
	 * @return the identifier
	 * @since 0.5.0
	 */
	String getIdentifier();

	/**
	 * Returns the creation date of this licensing request. This is the value of its
	 * <code>"creationDate"</code> attribute.
	 *
	 * @return the creation date
	 * @since 0.5.0
	 */
	Date getCreationDate();

	/**
	 * Returns the <code>"User"</code> identifier of this licensing request. This is
	 * the value of its <code>"userIdentifier"</code> attribute.
	 *
	 * @return the user identifier
	 * @since 0.5.0
	 */
	String getUserIdentifier();

	/**
	 * Returns the <code>"User"</code> full name of this licensing request. This is
	 * the value of its <code>"userFullName"</code> attribute.
	 *
	 * @return the user full name
	 * @since 0.5.0
	 */
	String getUserFullName();

	/**
	 * Returns the <code>"Product"</code> identifier of this licensing request. This
	 * is the value of its <code>"productIdentifier"</code> attribute.
	 *
	 * @return the product identifier
	 * @since 0.5.0
	 */
	String getProductIdentifier();

	/**
	 * Returns the <code>"Product Version"</code> version of this licensing request.
	 * This is the value of its <code>"productVersion"</code> attribute.
	 *
	 * @return the product version
	 * @since 0.5.0
	 */
	String getProductVersion();

	/**
	 * Returns the <code>"License Plan"</code> identifier of this licensing request.
	 * This is the value of its <code>"planIdentifier"</code> attribute.
	 *
	 * @return the plan identifier
	 * @since 0.5.0
	 */
	String getPlanIdentifier();

	/**
	 * Returns the validity period start date of this licensing request. This is the
	 * value of its <code>"validFrom"</code> attribute.
	 *
	 * @return the valid from
	 * @since 0.5.0
	 */
	Date getValidFrom();

	/**
	 * Returns the validity period end date of this licensing request. This is the
	 * value of its <code>"validUntil"</code> attribute.
	 *
	 * @return the valid until
	 * @since 0.5.0
	 */
	Date getValidUntil();

	/**
	 * Returns the condition type of this licensing request. This is the value of
	 * its <code>"conditionType"</code> attribute.
	 *
	 * @return the condition type
	 * @since 0.5.0
	 */
	String getConditionType();

	/**
	 * Returns the condition expression of this licensing request. This is the value
	 * of its <code>"conditionExpression"</code> attribute.
	 *
	 * @return the condition expression
	 * @since 0.5.0
	 */
	String getConditionExpression();

}
