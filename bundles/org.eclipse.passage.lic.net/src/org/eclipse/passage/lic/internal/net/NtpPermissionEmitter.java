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
package org.eclipse.passage.lic.internal.net;

import org.eclipse.passage.lic.base.access.BasePermissionEmitter;
import org.eclipse.passage.lic.net.TimeConditions;

public class NtpPermissionEmitter extends BasePermissionEmitter {

	public NtpPermissionEmitter() {
	}

	@Override
	protected boolean evaluateSegment(String key, String value) {
		switch (key) {
		case TimeConditions.PROPERTY_LOCALTIMESTAMP:
			return TimeConditions.isFutureLocalDateTime(value);
		default:
			return false;
		}
	}

}
