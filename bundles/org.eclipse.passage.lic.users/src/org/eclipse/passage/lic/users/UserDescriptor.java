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
package org.eclipse.passage.lic.users;

/**
 * <p>
 * A <code>"User"</code> describes the licensee of your functionality.
 * <p>
 *
 */
public interface UserDescriptor extends LicenseOwnerDescriptor {

	/**
	 * Returns the preferred condition type of this user. This is the value of its
	 * <code>"preferredConditionType"</code> attribute.
	 *
	 * @return the preferred condition type
	 * @since 2.0
	 * @see LicenseGrantDescriptor#getConditionType()
	 */
	String getPreferredEvaluationType();

	/**
	 * Returns the preferred condition expression of this user. This is the value of
	 * its <code>"preferredConditionExpression"</code> attribute.
	 *
	 * @return the preferred condition expression
	 * @since 2.0
	 * @see LicenseGrantDescriptor#getConditionExpression()
	 */
	String getPreferredEvaluationExpression();

	/**
	 * Returns the containing origin of this user.
	 *
	 * @return the user origin
	 * @since 2.0
	 */
	UserOriginDescriptor getOrigin();

}
