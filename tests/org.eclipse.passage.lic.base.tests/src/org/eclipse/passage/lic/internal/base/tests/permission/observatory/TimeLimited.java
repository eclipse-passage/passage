/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.base.tests.permission.observatory;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import org.eclipse.passage.lic.internal.api.observatory.Limited;

@SuppressWarnings("restriction")
final class TimeLimited implements Limited {
	private final String id;
	private final ZonedDateTime start;
	private final ZonedDateTime end;

	TimeLimited(ZonedDateTime start, ZonedDateTime end) {
		id = "L:" + Long.toHexString(System.currentTimeMillis()); //$NON-NLS-1$
		this.start = start;
		this.end = end;
	}

	TimeLimited(int endDelta) {
		this(ZonedDateTime.now(), ZonedDateTime.now().plus(endDelta, ChronoUnit.SECONDS));
	}

	TimeLimited() {
		this(3);
	}

	@Override
	public boolean expired() {
		ZonedDateTime now = ZonedDateTime.now();
		return now.isBefore(start) || now.isAfter(end);
	}

	@Override
	public String toString() {
		return String.format("%s (expired: %b)", id, expired()); //$NON-NLS-1$
	}

}
