/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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

import java.util.Collections;

import org.eclipse.passage.lic.api.requirements.LicensingRequirement;
import org.eclipse.passage.lic.base.LicensingNamespaces;
import org.osgi.framework.Bundle;
import org.osgi.framework.wiring.BundleCapability;
import org.osgi.framework.wiring.BundleRequirement;
import org.osgi.framework.wiring.BundleWiring;

public class EquinoxRequirements {

	/**
	 * @deprecated not used
	 */
	@Deprecated
	public static Iterable<BundleRequirement> extractLicensingManagementRequirements(Bundle bundle) {
		BundleWiring wiring = bundle.adapt(BundleWiring.class);
		if (wiring != null) {
			return wiring.getRequirements(LicensingNamespaces.CAPABILITY_LICENSING_MANAGEMENT);
		}
		return Collections.emptyList();
	}

	/**
	 * @deprecated Use {@linkplain LicensingFeatureCapabilitiesFromBundle}
	 */
	@Deprecated
	public static Iterable<BundleCapability> extractLicensingFeatures(Bundle bundle) {
		BundleWiring wiring = bundle.adapt(BundleWiring.class);
		if (wiring != null) {
			return wiring.getCapabilities(LicensingNamespaces.CAPABILITY_LICENSING_FEATURE);
		}
		return Collections.emptyList();
	}

	public static Iterable<LicensingRequirement> getFeatureRequirements(String... featureIds) {
		throw new UnsupportedOperationException("deprecated"); //$NON-NLS-1$
	}

}
