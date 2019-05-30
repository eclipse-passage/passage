/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
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
package org.eclipse.passage.lic.api.access;

import java.util.Date;

import org.eclipse.passage.lic.api.conditions.LicensingCondition;

/**
 * Collects the data required to issue the license
 * 
 * @since 0.5.0
 *
 */
public interface LicensingRequest {

	/**
	 * Returns the identifier of this licensing request. This is the value of its
	 * <code>"identifier"</code> attribute.
	 *
	 * @return the identifier
	 */
	String getIdentifier();

	/**
	 * Returns the creation date of this licensing request. This is the value of its
	 * <code>"creationDate"</code> attribute.
	 *
	 * @return the creation date
	 */
	Date getCreationDate();

	/**
	 * Returns the <code>"User"</code> identifier of this licensing request. This is
	 * the value of its <code>"userIdentifier"</code> attribute.
	 *
	 * @return the user identifier
	 */
	String getUserIdentifier();

	/**
	 * Returns the <code>"Product"</code> identifier of this licensing request. This
	 * is the value of its <code>"productIdentifier"</code> attribute.
	 *
	 * @return the product identifier
	 */
	String getProductIdentifier();

	/**
	 * Returns the <code>"Product Version"</code> version of this licensing request.
	 * This is the value of its <code>"productVersion"</code> attribute.
	 *
	 * @return the product version
	 */
	String getProductVersion();

	/**
	 * Returns the <code>"License Plan"</code> identifier of this licensing request.
	 * This is the value of its <code>"planIdentifier"</code> attribute.
	 *
	 * @return the plan identifier
	 */
	String getPlanIdentifier();

	/**
	 * Returns the validity period start date of this licensing request. This is the
	 * value of its <code>"validFrom"</code> attribute.
	 *
	 * @return the valid from
	 * @see LicensingCondition#getValidFrom()
	 */
	Date getValidFrom();

	/**
	 * Returns the validity period end date of this licensing request. This is the
	 * value of its <code>"validUntil"</code> attribute.
	 *
	 * @return the valid until
	 * @see LicensingCondition#getValidUntil()
	 */
	Date getValidUntil();

	/**
	 * Returns the condition type of this licensing request. This is the value of
	 * its <code>"conditionType"</code> attribute.
	 *
	 * @return the condition type
	 * @see LicensingCondition#getConditionType()
	 */
	String getConditionType();

	/**
	 * Returns the condition expression of this licensing request. This is the value
	 * of its <code>"conditionExpression"</code> attribute.
	 *
	 * @return the condition expression
	 * @see LicensingCondition#getConditionExpression()
	 */
	String getConditionExpression();

}
