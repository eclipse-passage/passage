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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.diagnostic.code.ServiceFailedOnMorsel;
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
@SuppressWarnings("restriction")
final class RequirementsFromManifest implements Supplier<ServiceInvocationResult<Collection<Requirement>>> {

	private final Bundle bundle;
	private final Trouble hiring;
	private final String target;

	public RequirementsFromManifest(Bundle bundle, Trouble hiring, String target) {
		this.bundle = bundle;
		this.hiring = hiring;
		this.target = target;
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
		Optional<String> provided = new ProvidedCapabilitiesFromManifest(manifest).get();
		if (!provided.isPresent()) {
			return none();
		}
		return null;// FIXME: implement
	}

	private ServiceInvocationResult<Collection<Requirement>> none() {
		return new BaseServiceInvocationResult<>(new ArrayList<Requirement>());
	}

}
