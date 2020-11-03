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
import java.util.function.Consumer;

import org.eclipse.passage.lic.internal.api.observatory.Limited;
import org.eclipse.passage.lic.internal.api.observatory.Observatory;

/**
 * <p>
 * This observatory keeps an eye on any captured entry, that can say in a moment
 * of time whether it is expired already or not yet.
 * </p>
 * 
 * <p>
 * Ones in a period of time the observatory checks all watched entries if they
 * are expired already. And for the ones who do, a configured action is called.
 * Since this moment an expired entry in not watched anymore.
 * </p>
 * 
 * <p>
 * There is no check on an observatory opening. The closest one takes place only
 * after a scheduled period of time is over.
 * </p>
 * 
 * <p>
 * An entry can be put under the observatory control and removed from it
 * preliminary, prior this is done by TTL.
 * </p>
 * 
 * <p>
 * To configure an observatory, you need only define
 * <ul>
 * <li><i>how often</i> it will check expiration and</li>
 * <li>what will it do for expired entries</li>
 * </ul>
 * </p>
 * 
 * @since 0.6
 */
public final class BaseObservatory<T extends Limited> implements Observatory<T> {

	private final Pool<T> pool;
	private final Guard<T> guard;

	/**
	 * <p>
	 * When the time comes and an entry expires, {@code farewell} action is called
	 * for it.
	 * </p>
	 * 
	 * <p>
	 * The observatory does the brushing checks each {@code schedule} seconds, so an
	 * entry cannot be processed precisely in a moment it expires, but some time
	 * like {@code schedule} seconds after.
	 * </p>
	 * 
	 * @param schedule period (in seconds) between checks
	 * @param farewell handler to be notified of expired entries. Is not intended to
	 *                 work long. Never bothered for naught: never gets {@code null}
	 *                 or empty set.
	 * @since 0.6
	 */
	public BaseObservatory(CheckSchedule schedule, Consumer<Set<T>> farewell) {
		pool = new Pool<T>();
		guard = new Guard<T>(schedule, pool, farewell);
	}

	/**
	 * The observatory can accept entries to watch and dismiss them after, but
	 * checks start only after it is open.
	 * 
	 * @since 0.6
	 */
	public void open() {
		new Thread(guard).start();
	}

	/**
	 * Put a {@link Limited} entry under the observatory control.
	 * 
	 * @since 0.6
	 */
	@Override
	public void watch(T limited) {
		pool.watch(limited);
	}

	/**
	 * Remove a {@link Limited} entry from the observatory control intentionally
	 * prior it's expiration.
	 * 
	 * @since 0.6
	 */
	@Override
	public void forget(T limited) {
		pool.forget(limited);
	}

}
