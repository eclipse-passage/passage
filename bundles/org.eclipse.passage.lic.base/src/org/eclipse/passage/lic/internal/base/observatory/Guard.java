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

import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import org.eclipse.passage.lic.internal.api.observatory.Limited;

final class Guard<T extends Limited> implements Runnable {

	private final long period;
	private final Pool<T> pool;
	private final Consumer<Set<T>> onExpire;
	private final Executor executor;

	/**
	 * @param onExpire never gets {@code null} or empty set. The callback is
	 *                 expected to complete shortly.
	 */
	Guard(CheckSchedule schedule, Pool<T> pool, Consumer<Set<T>> onExpire) {
		this.period = schedule.seconds();
		this.pool = pool;
		this.onExpire = onExpire;
		executor = Executors.newSingleThreadExecutor();
	}

	@Override
	public void run() {
		while (true) {
			rest();
			check();
		}
	}

	private void check() {
		executor.execute(() -> pool.popExpired().dispose(onExpire));
	}

	private void rest() {
		try {
			Thread.sleep(period * 1000);
		} catch (InterruptedException e) {
			// do nothing, just start new check ahead the schedule
		}
	}

}
