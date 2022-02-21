/*******************************************************************************
 * Copyright (c) 2022 ArSysOp
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
package org.eclipse.passage.lic.internal.base.access;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.passage.lic.api.acquire.GrantAcquisition;

/**
 * Stateful
 */
final class Storage {

	private final List<GrantAcquisition> grants = new ArrayList<>();

	Storage(List<GrantAcquisition> initial) {
		synchronized (this.grants) {
			this.grants.addAll(initial);
		}
	}

	void keep(GrantAcquisition grant) {
		synchronized (grants) {
			if (notYetSpotted(grant)) {
				grants.add(grant);
			}
		}
	}

	void forget(GrantAcquisition grant) {
		synchronized (grants) {
			grants.remove(grant);
		}
	}

	void forget(List<GrantAcquisition> bunch) {
		synchronized (grants) {
			grants.removeAll(bunch);
		}
	}

	List<GrantAcquisition> grants() {
		List<GrantAcquisition> all = new ArrayList<>();
		synchronized (grants) {
			all.addAll(grants);
		}
		return all;
	}

	private boolean notYetSpotted(GrantAcquisition grant) {
		return grants.stream()//
				.map(GrantAcquisition::identifier)//
				.noneMatch(grant.identifier()::equals);
	}

}
