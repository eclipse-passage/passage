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
package org.eclipse.passage.lic.users;

import java.util.List;

/**
 * <p>
 * A <code>"User Origin"</code> provides access to <code>"User"</code>
 * descriptors. The typical example of the <code>"User Origin"</code> is the
 * list of users registered on your web site to download
 * <code>"Product Version"</code>.
 * <p>
 *
 */
public interface UserOriginDescriptor {

	/**
	 * Returns the identifier of this user origin. This is the value of its
	 * <code>"identifier"</code> attribute.
	 *
	 * @return the identifier
	 */
	String getIdentifier();

	/**
	 * Returns the name of this user origin. This is the value of its
	 * <code>"name"</code> attribute.
	 *
	 * @return the name
	 */
	String getName();

	/**
	 * Returns the description of this user origin. This is the value of its
	 * <code>"description"</code> attribute.
	 *
	 * @return the description
	 */
	String getDescription();

	/**
	 * Returns the <code>"User"</code>(s) contained in this user origin. This is the
	 * value of its <code>"users"</code> reference.
	 *
	 * @return the users
	 * @since 2.0
	 */
	List<? extends UserDescriptor> getUsers();

	/**
	 * @since 2.0
	 */
	List<? extends UserGroupDescriptor> getGroups();

}
