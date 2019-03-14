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
package org.eclipse.passage.lic.registry.users;

import org.eclipse.passage.lic.runtime.LicensingEvents;

public class UsersEvents {

	/**
	 * Base name of all Users events
	 */
	public static final String USERS_TOPIC_BASE = "org/eclipse/passage/lic/registry/users"; //$NON-NLS-1$

	/**
	 * Base name of all User Origin events
	 */
	public static final String USER_ORIGIN_TOPIC_BASE = USERS_TOPIC_BASE + LicensingEvents.TOPIC_SEP + "UserOrigin"; //$NON-NLS-1$

	/**
	 * User Origin <code>create</code> event
	 */
	public static final String USER_ORIGIN_CREATE = USER_ORIGIN_TOPIC_BASE + LicensingEvents.TOPIC_SEP
			+ LicensingEvents.CREATE;

	/**
	 * User Origin <code>read</code> event
	 */
	public static final String USER_ORIGIN_READ = USER_ORIGIN_TOPIC_BASE + LicensingEvents.TOPIC_SEP
			+ LicensingEvents.READ;

	/**
	 * User Origin <code>update</code> event
	 */
	public static final String USER_ORIGIN_UPDATE = USER_ORIGIN_TOPIC_BASE + LicensingEvents.TOPIC_SEP
			+ LicensingEvents.UPDATE;

	/**
	 * User Origin <code>delete</code> event
	 */
	public static final String USER_ORIGIN_DELETE = USER_ORIGIN_TOPIC_BASE + LicensingEvents.TOPIC_SEP
			+ LicensingEvents.DELETE;

	/**
	 * Base name of all User events
	 */
	public static final String USER_TOPIC_BASE = USERS_TOPIC_BASE + LicensingEvents.TOPIC_SEP + "User"; //$NON-NLS-1$

	/**
	 * User <code>create</code> event
	 */
	public static final String USER_CREATE = USER_TOPIC_BASE + LicensingEvents.TOPIC_SEP + LicensingEvents.CREATE;

	/**
	 * User <code>read</code> event
	 */
	public static final String USER_READ = USER_TOPIC_BASE + LicensingEvents.TOPIC_SEP + LicensingEvents.READ;

	/**
	 * User <code>update</code> event
	 */
	public static final String USER_UPDATE = USER_TOPIC_BASE + LicensingEvents.TOPIC_SEP + LicensingEvents.UPDATE;

	/**
	 * User <code>delete</code> event
	 */
	public static final String USER_DELETE = USER_TOPIC_BASE + LicensingEvents.TOPIC_SEP + LicensingEvents.DELETE;

}
