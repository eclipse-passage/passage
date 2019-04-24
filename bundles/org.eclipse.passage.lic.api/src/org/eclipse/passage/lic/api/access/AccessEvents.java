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
package org.eclipse.passage.lic.api.access;

import static org.eclipse.passage.lic.api.LicensingEvents.ALL_SUB_TOPICS;
import static org.eclipse.passage.lic.api.LicensingEvents.TOPIC_BASE;
import static org.eclipse.passage.lic.api.LicensingEvents.TOPIC_SEP;

import org.eclipse.passage.lic.api.conditions.LicensingCondition;

public final class AccessEvents {

	private AccessEvents() {
		// block
	}

	/**
	 * Base name for all access management cycle events
	 */
	public static final String TOPIC = TOPIC_BASE + TOPIC_SEP + "AccessEvents"; //$NON-NLS-1$

	/**
	 * Name for all access management events
	 */
	public static final String TOPIC_ALL = TOPIC + TOPIC_SEP + ALL_SUB_TOPICS;

	/**
	 * Sent when {@link LicensingCondition}(s) are extracted
	 */
	public static final String CONDITIONS_EXTRACTED = TOPIC + TOPIC_SEP + "conditionsExtracted"; //$NON-NLS-1$

	/**
	 * Sent when {@link LicensingCondition}(s) are evaluated
	 */
	public static final String CONDITIONS_EVALUATED = TOPIC + TOPIC_SEP + "conditionsEvaluated"; //$NON-NLS-1$

	/**
	 * Sent when {@link FeaturePermission}(s) are examined
	 */
	public static final String PERMISSIONS_EXAMINED = TOPIC + TOPIC_SEP + "permissionsExamined"; //$NON-NLS-1$

	/**
	 * Sent when restrictions are executed
	 */
	public static final String RESTRICTIONS_EXECUTED = TOPIC + TOPIC_SEP + "restrictionsExecuted"; //$NON-NLS-1$

}