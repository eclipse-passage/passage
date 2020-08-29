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
package org.eclipse.passage.loc.internal.users;

import static org.eclipse.passage.loc.internal.api.LicensingEvents.CREATE;
import static org.eclipse.passage.loc.internal.api.LicensingEvents.DELETE;
import static org.eclipse.passage.loc.internal.api.LicensingEvents.READ;
import static org.eclipse.passage.loc.internal.api.LicensingEvents.TOPIC_SEP;
import static org.eclipse.passage.loc.internal.api.LicensingEvents.UPDATE;

/**
 * User registry events and event topic definitions.
 *
 * @since 0.4.0
 */
public final class UserRegistryEvents {

	/**
	 * Base name of all Users events
	 */
	public static final String USERS_TOPIC_BASE = "org/eclipse/passage/lic/users/registry"; //$NON-NLS-1$

	/**
	 * Base name of all User Origin events
	 */
	public static final String USER_ORIGIN_TOPIC_BASE = USERS_TOPIC_BASE + TOPIC_SEP + "UserOrigin"; //$NON-NLS-1$

	/**
	 * User Origin <code>create</code> event
	 */
	public static final String USER_ORIGIN_CREATE = USER_ORIGIN_TOPIC_BASE + TOPIC_SEP + CREATE;

	/**
	 * User Origin <code>read</code> event
	 */
	public static final String USER_ORIGIN_READ = USER_ORIGIN_TOPIC_BASE + TOPIC_SEP + READ;

	/**
	 * User Origin <code>update</code> event
	 */
	public static final String USER_ORIGIN_UPDATE = USER_ORIGIN_TOPIC_BASE + TOPIC_SEP + UPDATE;

	/**
	 * User Origin <code>delete</code> event
	 */
	public static final String USER_ORIGIN_DELETE = USER_ORIGIN_TOPIC_BASE + TOPIC_SEP + DELETE;

	/**
	 * Base name of all User events
	 */
	public static final String USER_TOPIC_BASE = USERS_TOPIC_BASE + TOPIC_SEP + "User"; //$NON-NLS-1$

	/**
	 * User <code>create</code> event
	 */
	public static final String USER_CREATE = USER_TOPIC_BASE + TOPIC_SEP + CREATE;

	/**
	 * User <code>read</code> event
	 */
	public static final String USER_READ = USER_TOPIC_BASE + TOPIC_SEP + READ;

	/**
	 * User <code>update</code> event
	 */
	public static final String USER_UPDATE = USER_TOPIC_BASE + TOPIC_SEP + UPDATE;

	/**
	 * User <code>delete</code> event
	 */
	public static final String USER_DELETE = USER_TOPIC_BASE + TOPIC_SEP + DELETE;

	/**
	 * Base name of all User License events
	 */
	public static final String USER_LICENSE_TOPIC_BASE = USERS_TOPIC_BASE + TOPIC_SEP + "UserLicense"; //$NON-NLS-1$

	/**
	 * User License <code>create</code> event
	 */
	public static final String USER_LICENSE_CREATE = USER_LICENSE_TOPIC_BASE + TOPIC_SEP + CREATE;

	/**
	 * User License <code>read</code> event
	 */
	public static final String USER_LICENSE_READ = USER_LICENSE_TOPIC_BASE + TOPIC_SEP + READ;

	/**
	 * User License <code>update</code> event
	 */
	public static final String USER_LICENSE_UPDATE = USER_LICENSE_TOPIC_BASE + TOPIC_SEP + UPDATE;

	/**
	 * User License <code>delete</code> event
	 */
	public static final String USER_LICENSE_DELETE = USER_LICENSE_TOPIC_BASE + TOPIC_SEP + DELETE;

	private UserRegistryEvents() {
		// block
	}

}
