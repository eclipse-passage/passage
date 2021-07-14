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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.SumOfCollections;
import org.eclipse.passage.lic.base.diagnostic.code.ServiceFailedOnMorsel;
import org.eclipse.passage.lic.internal.equinox.i18n.AccessMessages;
import org.osgi.framework.Bundle;

/**
 * Some bundles come to runtime in invalid state. Nevertheless we must look for
 * {@linkplain Requirement}s in these bundles too. Thus, we try to read
 * MANIFEST.MF manually and scan the content for licensing requirement
 * declarations.
 * 
 * @see RequirementsFromBundle
 */
final class RequirementsFromManifest implements Supplier<ServiceInvocationResult<Collection<Requirement>>> {

	private final Bundle bundle;

	public RequirementsFromManifest(Bundle bundle) {
		Objects.requireNonNull(bundle, "RequirementsFromManifest::bundle"); //$NON-NLS-1$
		this.bundle = bundle;
	}

	@Override
	public ServiceInvocationResult<Collection<Requirement>> get() {
		try {
			return scan();
		} catch (LicensingException e) {
			return new BaseServiceInvocationResult<>(new Trouble(//
					new ServiceFailedOnMorsel(), //
					AccessMessages.RequirementsFromManifest_failure));
		}
	}

	private ServiceInvocationResult<Collection<Requirement>> scan() throws LicensingException {
		String manifest = new BundleManifest(bundle).get();
		Optional<String> declaration = new ProvidedCapabilitiesFromManifest(manifest).get();
		if (!declaration.isPresent()) {
			return none();
		}
		return new LicCapabilityAttributesFromDeclaration(declaration.get()).get().stream()//
				.map(pack -> new RequirementFromAttributes(bundle, pack)) //
				.map(RequirementFromAttributes::get)//
				.reduce(new BaseServiceInvocationResult.Sum<>(new SumOfCollections<Requirement>()))//
				.orElseGet(() -> new BaseServiceInvocationResult<>(new ArrayList<Requirement>()));
	}

	private ServiceInvocationResult<Collection<Requirement>> none() {
		return new BaseServiceInvocationResult<>(new ArrayList<Requirement>());
	}

}
