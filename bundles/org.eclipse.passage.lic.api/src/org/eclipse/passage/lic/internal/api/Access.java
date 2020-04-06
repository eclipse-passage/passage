/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.api;

import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements;

/**
 * Top-level access cycle management is to be implemented here. Just started
 * with what we have for now: requirements resolution.
 */
public final class Access {

	private final Framework framework;

	public Access(Framework framework) {
		this.framework = framework;
	}

	public boolean canUse(String feature) {
		Set<Requirement> requirements = framework.requirementsRegistry().get().services().stream() //
				.map(ResolvedRequirements.Smart::new) //
				.flatMap(service -> service.forFeature(feature).stream()) //
				.collect(Collectors.toSet());
		if (requirements.isEmpty()) {
			return true;
		}
		// FIXME: EP: implement further
		return false;
	}

}
