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
package org.eclipse.passage.lic.internal.equinox.requirements;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.base.BaseNamedData;
import org.osgi.framework.Bundle;
import org.osgi.framework.wiring.BundleWiring;

/**
 * Looks for {@linkplain Requirement} declarations among all the
 * licensing-namespaced {@code Capabilities} in the given {@code Bundle}
 * 
 * @see RequirementFromCapability
 * @see BundleRequirements
 */
@SuppressWarnings("restriction")
final class RequirementsFromBundle extends BaseNamedData<List<Requirement>> {

	public RequirementsFromBundle(Bundle bundle) {
		super(key -> //
		Optional.ofNullable(bundle.adapt(BundleWiring.class))//
				.map(wiring -> wiring.getCapabilities(key))//
				.orElseGet(Collections::emptyList)//
				.stream()//
				.map(capability -> new RequirementFromCapability(bundle, capability))//
				.map(RequirementFromCapability::get) //
				.collect(Collectors.toList()));
	}

	@Override
	public String key() {
		return new LicCapabilityNamespace().get();
	}

}
