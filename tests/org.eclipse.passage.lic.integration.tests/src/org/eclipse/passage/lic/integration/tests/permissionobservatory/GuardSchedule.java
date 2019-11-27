/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
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
package org.eclipse.passage.lic.integration.tests.permissionobservatory;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Observatory component is configured to production environment and checks
 * watched permissions ones in several minutes.
 * </p>
 * <p>
 * For test purposes we must shorten this schedule to seconds for the test to
 * finish shortly.
 * </p>
 * 
 * @since 0.6
 */
class GuardSchedule {
	private final int seconds;

	GuardSchedule(int seconds) {
		this.seconds = seconds;
	}

	Map<String, Object> map() {
		Map<String, Object> config = new HashMap<>();
		config.put("observatory.schedule", seconds); //$NON-NLS-1$
		return config;
	}

}
