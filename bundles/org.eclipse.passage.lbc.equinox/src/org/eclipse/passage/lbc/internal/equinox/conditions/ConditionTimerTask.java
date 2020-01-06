/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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
package org.eclipse.passage.lbc.internal.equinox.conditions;

import java.util.TimerTask;

import org.eclipse.passage.lic.net.TimeConditions;

abstract class ConditionTimerTask extends TimerTask {

	private boolean isStopped = false;
	private final String timeToLive;

	public ConditionTimerTask(String timeToLive) {
		this.timeToLive = timeToLive;
	}

	@Override
	public void run() {
		if (isStopped) {
			return;
		}
		if (TimeConditions.isFutureLocalDateTime(timeToLive)) {
			isStopped = true;
			timeExpired();
		}
	}

	public void stopTask() {
		isStopped = true;
	}

	abstract void timeExpired();

}
