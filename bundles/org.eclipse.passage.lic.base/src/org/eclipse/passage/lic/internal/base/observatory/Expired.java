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

/**
 * <p>
 * Set of {@link Limited}s, that have been found expired on a check.
 * </p>
 * <p>
 * Expired {@link Limited}s need to be <i>disposed</i> - it's the last care we
 * can do for those, who have run out of time on the Earth.
 * </p>
 *
 * @see #dispose
 */
final class Expired<T extends Limited> {

	private final Set<T> origin;

	Expired(Set<T> origin) {
		this.origin = origin;
	}

	/**
	 * <p>
	 * The last attention an observatory pays to expired entries is a
	 * <i>disposal</i>.
	 * </p>
	 * <p>
	 * Here all expired {@link Limited}s are passed to the {@code farewell} action
	 * in one fell swoop.
	 * </p>
	 *
	 * @param farewell should be a fast-running action. Never gets {@code null} or
	 *                 empty set of entries.
	 */
	void dispose(Consumer<Set<T>> farewell) {
		if (origin.isEmpty()) {
			return;
		}
		farewell.accept(origin);
	}

}
