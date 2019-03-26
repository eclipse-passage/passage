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
package org.eclipse.passage.lic.runtime.requirements;

import static org.eclipse.passage.lic.runtime.LicensingEvents.ALL_SUB_TOPICS;
import static org.eclipse.passage.lic.runtime.LicensingEvents.TOPIC_BASE;
import static org.eclipse.passage.lic.runtime.LicensingEvents.TOPIC_SEP;

public final class RequirementEvents {

	private RequirementEvents() {
		// block
	}

	/**
	 * Base name for all {@link LicensingRequirement}(s) events
	 */
	public static final String TOPIC = TOPIC_BASE + TOPIC_SEP + "RequirementEvents"; //$NON-NLS-1$

	/**
	 * Name for all {@link LicensingRequirement}(s) events
	 */
	public static final String TOPIC_ALL = TOPIC + TOPIC_SEP + ALL_SUB_TOPICS;

	/**
	 * Sent when {@link LicensingRequirement}(s) are resolved
	 */
	public static final String REQUIREMENTS_RESOLVED = TOPIC + TOPIC_SEP + "requirementsResolved"; //$NON-NLS-1$

}