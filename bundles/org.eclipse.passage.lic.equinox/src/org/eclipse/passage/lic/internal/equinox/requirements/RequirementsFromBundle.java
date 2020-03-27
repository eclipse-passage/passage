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

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.osgi.framework.Bundle;

/**
 * Looks for {@linkplain Requirement} declarations among all the
 * licensing-namespaced {@code Capabilities} in the given {@code Bundle}
 * 
 * @see RequirementsFromCapability
 * @see BundleRequirements
 */
@SuppressWarnings("restriction")
final class RequirementsFromBundle implements Supplier<List<Requirement>> {

	private final Bundle bundle;

	public RequirementsFromBundle(Bundle bundle) {
		this.bundle = bundle;
	}

	@Override
	public List<Requirement> get() {
		return new LicensingFeatureCapabilitiesFromBundle(bundle).get().get().stream()//
				.map(capability -> new RequirementsFromCapability(bundle, capability))//
				.map(RequirementsFromCapability::get) //
				.collect(Collectors.toList());
	}

}
