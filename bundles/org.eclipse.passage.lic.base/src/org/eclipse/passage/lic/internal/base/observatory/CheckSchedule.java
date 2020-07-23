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
package org.eclipse.passage.lic.internal.base.observatory;

import java.time.temporal.ChronoUnit;

/**
 * <p>
 * Schedule for observatory checks.
 * </p>
 * 
 * <p>
 * Been defined for <i>5 minutes</i>, causes the observatory to check for new
 * expired entries each 5 minutes (or so).
 * </p>
 *
 * @since 0.6
 */
public class CheckSchedule {

	private final int amount;
	private final ChronoUnit unit;

	/**
	 * <p>
	 * Use desired units to define the most suitable check schedule.
	 * </p>
	 * 
	 * <p>
	 * For example, {@code new CheckSchedule(2, ChronoUnit.DAYS)}, been given to an
	 * observatory, will cause the closest expiration check to happen only in two
	 * days since the observatory opening.
	 * </p>
	 * 
	 * @param amount number of units
	 * @unit {@linkplain ChronoUnit} constant to measure {@code amount}
	 * @since 0.6
	 */
	public CheckSchedule(int amount, ChronoUnit unit) {
		this.amount = amount;
		this.unit = unit;
	}

	/**
	 * Default schedules are measured in minutes.
	 * 
	 * @param minutes number of minutes
	 * @since 0.6
	 */
	public CheckSchedule(int minutes) {
		this(minutes, ChronoUnit.MINUTES);
	}

	/**
	 * Default schedule is 10 minutes.
	 * 
	 * @since 0.6
	 */
	public CheckSchedule() {
		this(10, ChronoUnit.MINUTES);
	}

	/**
	 * Reports the scheduled period duration in seconds.
	 * 
	 * @since 0.6
	 */
	public long seconds() {
		return unit.getDuration().getSeconds() * amount;
	}

}
