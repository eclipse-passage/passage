/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.equinox;

import java.util.Collections;

import org.eclipse.passage.lic.base.LicensingNamespaces;
import org.osgi.framework.Bundle;
import org.osgi.framework.wiring.BundleCapability;
import org.osgi.framework.wiring.BundleRequirement;
import org.osgi.framework.wiring.BundleWiring;

public class LicensingBundles {

	public static Iterable<BundleRequirement> extractLicensingManagementRequirements(Bundle bundle) {
		BundleWiring wiring = bundle.adapt(BundleWiring.class);
		if (wiring != null) {
			return wiring.getRequirements(LicensingNamespaces.CAPABILITY_LICENSING_MANAGEMENT);
		}
		return Collections.emptyList();
	}

	public static Iterable<BundleCapability> extractLicensingFeatures(Bundle bundle) {
		BundleWiring wiring = bundle.adapt(BundleWiring.class);
		if (wiring != null) {
			return wiring.getCapabilities(LicensingNamespaces.CAPABILITY_LICENSING_FEATURE);
		}
		return Collections.emptyList();
	}
	
}
