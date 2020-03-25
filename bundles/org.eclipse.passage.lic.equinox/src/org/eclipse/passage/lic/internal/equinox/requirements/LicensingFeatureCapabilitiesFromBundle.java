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

/**
 * <p>
 * Covers special <i>licensing</i> {@code capability} reading from the given
 * {@code Bundle}'s manifest, where the bundle developers place inion for the
 * feature under licensing.
 * </p>
 * <p>
 * Never supplies empty {@code Optional}: it always contain either actual list
 * of {@code capabilities} or empty list, if there is none declared in the given
 * {@code bundle}.
 * </p>
 */
@SuppressWarnings("restriction")
final class LicensingFeatureCapabilitiesFromBundle extends BaseNamedData<List<BundleCapability>> {

	protected LicensingFeatureCapabilitiesFromBundle(Bundle bundle) {
		super(key -> //
		Optional.ofNullable(bundle.adapt(BundleWiring.class))//
				.map(wiring -> wiring.getCapabilities(key))//
				.orElseGet(Collections::emptyList));
	}

	@Override
	public String key() {
		return "licensing.feature"; //$NON-NLS-1$
	}

}
