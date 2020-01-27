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
package org.eclipse.passage.lic.net;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeConditions {

	public static final String CONDITION_TYPE_TIME = "time"; //$NON-NLS-1$
	public static final String PROPERTY_LOCALTIMESTAMP = "localtimestamp"; //$NON-NLS-1$

	private TimeConditions() {
		//block
	}

	public static boolean isFutureLocalDateTime(String value) {
		try {
			LocalDateTime dateTime = LocalDateTime.parse(value);
			LocalDateTime now = LocalDateTime.now();
			Duration duration = Duration.between(now, dateTime);
			return (!duration.isNegative());
		} catch (Exception e) {
			//FIXME: logger.debug(e);
			return false;
		}
	}

}
