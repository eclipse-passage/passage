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
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.SumOfCollections;
import org.eclipse.passage.lic.internal.base.diagnostic.code.NoServicesOfType;
import org.eclipse.passage.lic.internal.base.i18n.BaseMessages;
import org.eclipse.passage.lic.internal.base.requirements.RequirementsFeatureFilter;

/**
 * FIXME: Has public visibility only for testing.
 */
@SuppressWarnings("restriction")
public final class Requirements implements Supplier<ServiceInvocationResult<Collection<Requirement>>> {

	private final Registry<StringServiceId, ResolvedRequirements> registry;
	private final String feature;

	public Requirements(Registry<StringServiceId, ResolvedRequirements> registry, String feature) {
		this.registry = registry;
		this.feature = feature;
	}

	@Override
	public ServiceInvocationResult<Collection<Requirement>> get() {
		if (registry.services().isEmpty()) {
			return new BaseServiceInvocationResult<Collection<Requirement>>(//
					new Trouble(//
							new NoServicesOfType("requirement resolution"), //$NON-NLS-1$
							BaseMessages.getString("Requirements.failed"))); //$NON-NLS-1$
		}
		return registry.services().stream() //
				.map(ResolvedRequirements::all) //
				.reduce(new BaseServiceInvocationResult.Sum<>(new SumOfCollections<Requirement>()))//
				.map(new RequirementsFeatureFilter(feature).get())//
				.get(); // always exists
	}

}
