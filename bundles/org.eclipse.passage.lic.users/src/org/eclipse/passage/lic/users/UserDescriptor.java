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
package org.eclipse.passage.lic.users;

/**
 * <p>
 * A <code>"User"</code> describes the licensee of your functionality.
 * <p>
 *
 */
public interface UserDescriptor {

	/**
	 * Returns the identifier of this user. This is the value of its
	 * <code>"identifier"</code> attribute.
	 *
	 * @return the identifier
	 */
	String getIdentifier();

	/**
	 * Returns the email of this user. This is the value of its <code>"email"</code>
	 * attribute.
	 *
	 * @return the email
	 */
	String getEmail();

	/**
	 * Returns the full name of this user. This is the value of its
	 * <code>"fullName"</code> attribute.
	 *
	 * @return the full name
	 */
	String getFullName();

	/**
	 * Returns the description of this user. This is the value of its
	 * <code>"description"</code> attribute.
	 *
	 * @return the description
	 */
	String getDescription();

	UserOriginDescriptor getUserOrigin();

}
