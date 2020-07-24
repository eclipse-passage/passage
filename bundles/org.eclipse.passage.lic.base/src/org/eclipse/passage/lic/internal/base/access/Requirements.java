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
package org.eclipse.passage.lic.internal.base.access;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements;
import org.eclipse.passage.lic.internal.base.i18n.BaseMessages;
import org.eclipse.passage.lic.internal.base.requirements.UnsatisfiableRequirement;

/**
 * FIXME: Has public visibility only for testing.
 */
public final class Requirements implements Supplier<Collection<Requirement>> {

	private final Registry<StringServiceId, ResolvedRequirements> registry;
	private final String feature;

	public Requirements(Registry<StringServiceId, ResolvedRequirements> registry, String feature) {
		this.registry = registry;
		this.feature = feature;
	}

	@Override
	public Collection<Requirement> get() {
		Collection<ResolvedRequirements> services = registry.services();
		if (!services.isEmpty()) {
			return services.stream() //
					.map(ResolvedRequirements.Smart::new) //
					.flatMap(service -> service.forFeature(feature).stream()) //
					.collect(Collectors.toSet());
		}
		return Collections.singleton(new UnsatisfiableRequirement(//
				BaseMessages.getString("Requirements.mandatory_requirements_resolvers_demand"), //$NON-NLS-1$
				BaseMessages.getString("Requirements.mandatory_requirements_resolvers_demand_author") //$NON-NLS-1$
		).get());
	}

}
