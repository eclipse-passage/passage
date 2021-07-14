/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.equinox.requirements;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.diagnostic.code.ServiceFailedOnMorsel;
import org.eclipse.passage.lic.internal.equinox.i18n.EquinoxMessages;
import org.osgi.framework.Bundle;
import org.osgi.framework.wiring.BundleCapability;

/**
 * Looks for {@linkplain Requirement} declaration in a single
 * {@code Capability}.
 * 
 * @see RequirementsFromBundle
 * @see BundleRequirements
 */
final class RequirementFromCapability implements Supplier<ServiceInvocationResult<Collection<Requirement>>> {

	private final Bundle bundle;
	private final BundleCapability capability;

	public RequirementFromCapability(Bundle bundle, BundleCapability capability) {
		this.bundle = bundle;
		this.capability = capability;
	}

	@Override
	public ServiceInvocationResult<Collection<Requirement>> get() {
		Optional<Map<String, Object>> attributes = Optional.ofNullable(capability.getAttributes());
		if (!attributes.isPresent()) {
			return new BaseServiceInvocationResult<>(new Trouble(new ServiceFailedOnMorsel(), //
					NLS.bind(EquinoxMessages.RequirementsFromCapability_no_attributes, //
							capability.getNamespace(), new BundleName(bundle).get())));
		}
		return new RequirementFromAttributes(bundle, attributes.get()).get();
	}

}
