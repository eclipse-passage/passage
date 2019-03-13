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
package org.eclipse.passage.lic.registry.licenses;

import org.eclipse.passage.lic.registry.RegistryEvents;

public class LicensesEvents extends RegistryEvents {

	/**
	 * Base name of all Licenses events
	 */
	public static final String LICENSES_TOPIC_BASE = "ru/arsysop/passage/lic/registry/licenses"; //$NON-NLS-1$

	/**
	 * Base name of all License Pack events
	 */
	public static final String LICENSE_PACK_TOPIC_BASE = LICENSES_TOPIC_BASE + TOPIC_SEP + "LicensePack"; //$NON-NLS-1$

	/**
	 * License Pack <code>create</code> event
	 */
	public static final String LICENSE_PACK_CREATE = LICENSE_PACK_TOPIC_BASE + TOPIC_SEP + CREATE;

	/**
	 * License Pack <code>read</code> event
	 */
	public static final String LICENSE_PACK_READ = LICENSE_PACK_TOPIC_BASE + TOPIC_SEP + READ;

	/**
	 * License Pack <code>update</code> event
	 */
	public static final String LICENSE_PACK_UPDATE = LICENSE_PACK_TOPIC_BASE + TOPIC_SEP + UPDATE;

	/**
	 * License Pack <code>delete</code> event
	 */
	public static final String LICENSE_PACK_DELETE = LICENSE_PACK_TOPIC_BASE + TOPIC_SEP + DELETE;

	/**
	 * Base name of all License Grant events
	 */
	public static final String LICENSE_GRANT_TOPIC_BASE = LICENSES_TOPIC_BASE + TOPIC_SEP + "LicenseGrant"; //$NON-NLS-1$

	/**
	 * License Grant <code>create</code> event
	 */
	public static final String LICENSE_GRANT_CREATE = LICENSE_GRANT_TOPIC_BASE + TOPIC_SEP + CREATE;

	/**
	 * License Grant <code>read</code> event
	 */
	public static final String LICENSE_GRANT_READ = LICENSE_GRANT_TOPIC_BASE + TOPIC_SEP + READ;

	/**
	 * License Grant <code>update</code> event
	 */
	public static final String LICENSE_GRANT_UPDATE = LICENSE_GRANT_TOPIC_BASE + TOPIC_SEP + UPDATE;

	/**
	 * License Grant <code>delete</code> event
	 */
	public static final String LICENSE_GRANT_DELETE = LICENSE_GRANT_TOPIC_BASE + TOPIC_SEP + DELETE;

}
