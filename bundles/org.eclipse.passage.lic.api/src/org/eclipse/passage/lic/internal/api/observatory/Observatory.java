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
package org.eclipse.passage.lic.internal.api.observatory;

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
 */
public interface Observatory<T extends Limited> {

	/**
	 * Put a {@link Limited} entry under the observatory control.
	 */
	void watch(T limited);

	/**
	 * Remove a {@link Limited} entry from the observatory control intentionally
	 * prior it's expiration.
	 */
	void forget(T limited);

}
