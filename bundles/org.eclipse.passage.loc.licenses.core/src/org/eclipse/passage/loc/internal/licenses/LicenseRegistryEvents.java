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
package org.eclipse.passage.loc.internal.licenses;

import static org.eclipse.passage.loc.internal.api.LicensingEvents.CREATE;
import static org.eclipse.passage.loc.internal.api.LicensingEvents.DELETE;
import static org.eclipse.passage.loc.internal.api.LicensingEvents.READ;
import static org.eclipse.passage.loc.internal.api.LicensingEvents.TOPIC_SEP;
import static org.eclipse.passage.loc.internal.api.LicensingEvents.UPDATE;

/**
 * License registry events and event topic definitions.
 *
 * @since 0.4.0
 */
public final class LicenseRegistryEvents {

	/**
	 * Base name of all Licenses events
	 */
	public static final String LICENSES_TOPIC_BASE = "org/eclipse/passage/lic/licenses/registry"; //$NON-NLS-1$

	/**
	 * Base name of all License Plan events
	 * 
	 * @since 0.5.0
	 */
	public static final String LICENSE_PLAN_TOPIC_BASE = LICENSES_TOPIC_BASE + TOPIC_SEP + "LicensePlan"; //$NON-NLS-1$

	/**
	 * License Plan <code>create</code> event
	 * 
	 * @since 0.5.0
	 */
	public static final String LICENSE_PLAN_CREATE = LICENSE_PLAN_TOPIC_BASE + TOPIC_SEP + CREATE;

	/**
	 * License Plan <code>read</code> event
	 * 
	 * @since 0.5.0
	 */
	public static final String LICENSE_PLAN_READ = LICENSE_PLAN_TOPIC_BASE + TOPIC_SEP + READ;

	/**
	 * License Plan <code>update</code> event
	 * 
	 * @since 0.5.0
	 */
	public static final String LICENSE_PLAN_UPDATE = LICENSE_PLAN_TOPIC_BASE + TOPIC_SEP + UPDATE;

	/**
	 * License Plan <code>delete</code> event
	 * 
	 * @since 0.5.0
	 */
	public static final String LICENSE_PLAN_DELETE = LICENSE_PLAN_TOPIC_BASE + TOPIC_SEP + DELETE;

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

	private LicenseRegistryEvents() {
		// block
	}

}
