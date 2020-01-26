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
package org.eclipse.passage.lic.api.access;

import static org.eclipse.passage.lic.api.LicensingEvents.ALL_SUB_TOPICS;
import static org.eclipse.passage.lic.api.LicensingEvents.TOPIC_BASE;
import static org.eclipse.passage.lic.api.LicensingEvents.TOPIC_SEP;

import org.eclipse.passage.lic.api.conditions.LicensingCondition;

/**
 * Codes for Access Management cycle events.
 *
 * @since 0.4.0
 */
public final class AccessEvents {

	private AccessEvents() {
		// block
	}

	/**
	 * Base name for all access management cycle events
	 * 
	 * @since 0.4.0
	 */
	public static final String TOPIC = TOPIC_BASE + TOPIC_SEP + "AccessEvents"; //$NON-NLS-1$

	/**
	 * Name for all access management events
	 * 
	 * @since 0.4.0
	 */
	public static final String TOPIC_ALL = TOPIC + TOPIC_SEP + ALL_SUB_TOPICS;

	/**
	 * Sent when {@link LicensingCondition}(s) are extracted
	 * 
	 * @since 0.4.0
	 */
	public static final String CONDITIONS_EXTRACTED = TOPIC + TOPIC_SEP + "conditionsExtracted"; //$NON-NLS-1$

	/**
	 * Sent when {@link LicensingCondition}(s) are evaluated
	 * 
	 * @since 0.4.0
	 */
	public static final String CONDITIONS_EVALUATED = TOPIC + TOPIC_SEP + "conditionsEvaluated"; //$NON-NLS-1$

	/**
	 * Sent when {@link FeaturePermission}(s) are examined
	 * 
	 * @since 0.4.0
	 */
	public static final String PERMISSIONS_EXAMINED = TOPIC + TOPIC_SEP + "permissionsExamined"; //$NON-NLS-1$

	/**
	 * Sent when {@link FeaturePermission}s are expired
	 * 
	 * @since 0.6
	 */
	public static final String PERMISSIONS_EXPIRED = TOPIC + TOPIC_SEP + "permissionsExpired"; //$NON-NLS-1$

	/**
	 * Sent when restrictions are executed
	 * 
	 * @since 0.4.0
	 */
	public static final String RESTRICTIONS_EXECUTED = TOPIC + TOPIC_SEP + "restrictionsExecuted"; //$NON-NLS-1$

}