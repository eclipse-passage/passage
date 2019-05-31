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
package org.eclipse.passage.lic.users;

import java.util.Date;

import org.eclipse.passage.lic.api.conditions.LicensingCondition;

/**
 * <p>
 * A <code>"User License"</code> describes the license issued to a
 * <code>"User"</code>.
 * <p>
 * 
 * @since 0.5.0
 *
 */
public interface UserLicenseDescriptor {

	/**
	 * Returns the <code>"License Plan"</code> identifier of this user license. This
	 * is the value of its <code>"planIdentifier"</code> attribute.
	 *
	 * @return the plan identifier
	 */
	String getPlanIdentifier();

	/**
	 * Returns the <code>"Product"</code> identifier of this user license. This is
	 * the value of its <code>"productIdentifier"</code> attribute.
	 *
	 * @return the product identifier
	 */
	String getProductIdentifier();

	/**
	 * Returns the <code>"Product Version"</code> version of this user license. This
	 * is the value of its <code>"productVersion"</code> attribute.
	 *
	 * @return the product version
	 */
	String getProductVersion();

	/**
	 * Returns the validity period start date of this user license. This is the
	 * value of its <code>"validFrom"</code> attribute.
	 *
	 * @return the valid from
	 * @see LicensingCondition#getValidFrom()
	 */
	Date getValidFrom();

	/**
	 * Returns the validity period end date of this user license. This is the value
	 * of its <code>"validUntil"</code> attribute.
	 *
	 * @return the valid until
	 * @see LicensingCondition#getValidUntil()
	 */
	Date getValidUntil();

	/**
	 * Returns the condition type of this user license. This is the value of its
	 * <code>"conditionType"</code> attribute.
	 *
	 * @return the condition type
	 * @see LicensingCondition#getConditionType()
	 */
	String getConditionType();

	/**
	 * Returns the condition expression of this user license. This is the value of
	 * its <code>"conditionExpression"</code> attribute.
	 *
	 * @return the condition expression
	 * @see LicensingCondition#getConditionExpression()
	 */
	String getConditionExpression();

	/**
	 * Returns the <code>"License Pack"</code> identifier of this user license. This
	 * is the value of its <code>"packIdentifier"</code> attribute.
	 *
	 * @return the pack identifier
	 */
	String getPackIdentifier();

	/**
	 * Returns the issue date of this user license. This is the value of its
	 * <code>"issueDate"</code> attribute.
	 *
	 * @return the issue date
	 */
	Date getIssueDate();

	/**
	 * Returns the containing user of this user license.
	 *
	 * @return the user
	 */
	UserDescriptor getUser();
}
