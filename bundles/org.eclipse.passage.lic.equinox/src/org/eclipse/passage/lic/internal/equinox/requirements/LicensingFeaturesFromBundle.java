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

import org.eclipse.passage.lic.internal.base.BaseNamedData;
import org.osgi.framework.Bundle;
import org.osgi.framework.wiring.BundleCapability;
import org.osgi.framework.wiring.BundleWiring;

@SuppressWarnings("restriction")
final class LicensingFeaturesFromBundle extends BaseNamedData<List<BundleCapability>> {

	protected LicensingFeaturesFromBundle(Bundle bundle) {
		super(key -> //
		Optional.ofNullable(bundle.adapt(BundleWiring.class))//
				.map(wiring -> wiring.getCapabilities(key))//
				.orElseGet(Collections::emptyList));
	}

	@Override
	public String key() {
		return "licensing.feature"; //$NON-NLS-1$
	}

	@Override
	public String printed(List<BundleCapability> value) {
		throw new UnsupportedOperationException();
	}

}
