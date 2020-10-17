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
package org.eclipse.passage.loc.internal.api;

/**
 * Collects the data required to issue the license
 *
 * @since 0.5.0
 */
public interface PersonalLicenseRequest extends GeneralLicenseRequest {

	/**
	 * Returns the <code>"User"</code> identifier of this licensing request. This is
	 * the value of its <code>"userIdentifier"</code> attribute.
	 *
	 * @return the user identifier
	 * @since 0.5.0
	 */
	String user();

	/**
	 * Returns the <code>"User"</code> full name of this licensing request. This is
	 * the value of its <code>"userFullName"</code> attribute.
	 *
	 * @return the user full name
	 * @since 0.5.0
	 */
	String userFullName();

	/**
	 * Returns the condition type of this licensing request. This is the value of
	 * its <code>"conditionType"</code> attribute.
	 *
	 * @return the condition type
	 * @since 0.5.0
	 */
	String conditionType();

	/**
	 * Returns the condition expression of this licensing request. This is the value
	 * of its <code>"conditionExpression"</code> attribute.
	 *
	 * @return the condition expression
	 * @since 0.5.0
	 */
	String conditionExpression();

}
