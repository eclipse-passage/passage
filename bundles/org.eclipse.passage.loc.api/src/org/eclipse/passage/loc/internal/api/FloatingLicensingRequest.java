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

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;

/**
 * Collection of all the data required to issue a floating license pack.
 */
public interface FloatingLicensingRequest {

	/**
	 * Returns the identifier of this licensing request.
	 */
	String identifier();

	/**
	 * Returns the creation date of this licensing request.
	 */
	Date creationDate();

	/**
	 * Identifiers of all the <code>User</code>s selected for this licensing
	 * request.
	 */
	Collection<String> users();

	String productIdentifier();

	String productVersion();

	/**
	 * Identifier of the license plan used
	 */
	String plan();

	ZonedDateTime validFrom();

	ZonedDateTime validUntil();

	/**
	 * Full name of a <code>User</code> identified by the given {@code user} value.
	 */
	String fullName(String user);

	/**
	 * Type of licensing condition for a <code>User</code> identified by the given
	 * {@code user} value.
	 */
	String conditionType(String user);

	/**
	 * Condition expression for a <code>User</code> identified by the given
	 * {@code user} value.
	 */
	String conditionExpression(String user);

}
