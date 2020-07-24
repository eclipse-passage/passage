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

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.observatory.Limited;

final class Pool<T extends Limited> {
	private final Set<T> watched = new HashSet<>();

	void watch(T target) {
		synchronized (watched) {
			watched.add(target);
		}
	}

	void forget(T target) {
		synchronized (watched) {
			watched.remove(target);
		}
	}

	Expired<T> popExpired() {
		synchronized (watched) {
			Set<T> expired = watched.stream() //
					.filter(Limited::expired)//
					.collect(Collectors.toSet());
			watched.removeAll(expired);
			return new Expired<T>(expired);
		}
	}
}
