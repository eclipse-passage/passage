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
package org.eclipse.passage.lic.internal.base.permission.observatory;

import java.util.Set;
import java.util.function.Consumer;

/**
 * <p>
 * This observatory keeps an eye on any captured entry, that can say in a moment
 * of time whether it is expired already or not yet.
 * </p>
 * 
 * <p>
 * Ones in a period of time the observatory checks all watched entries if they
 * are expired already. And for the ones who do, a configured action is called.
 * </p>
 * 
 * <p>
 * An entry can be put under the observatoryControl and removed from it
 * preliminary, prior this is done by TTL.
 * </p>
 * 
 * @since 0.6
 */
public final class GuardedObservatory<T extends Limited> {
	private final Observatory<T> observatory;
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
	public GuardedObservatory(int schedule, Consumer<Set<T>> farewell) {
		observatory = new Observatory<T>();
		guard = new Guard<T>(schedule, observatory, farewell);
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
	public void watch(T limited) {
		observatory.watch(limited);
	}

	/**
	 * Remove a {@link Limited} entry from the observatory control intentionally
	 * prior it's expiration.
	 * 
	 * @since 0.6
	 */
	public void forget(T limited) {
		observatory.forget(limited);
	}

}
