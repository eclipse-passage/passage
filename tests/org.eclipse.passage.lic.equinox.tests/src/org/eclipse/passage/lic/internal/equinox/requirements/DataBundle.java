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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import org.eclipse.core.runtime.Platform;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.RestrictionLevel;
import org.eclipse.passage.lic.internal.base.Cached;
import org.eclipse.passage.lic.internal.base.requirements.BaseFeature;
import org.eclipse.passage.lic.internal.base.requirements.BaseRequirement;
import org.osgi.framework.Bundle;
import org.osgi.framework.wiring.BundleCapability;
import org.osgi.framework.wiring.BundleWiring;

@SuppressWarnings("restriction")
final class DataBundle implements Supplier<Bundle> {

	private final String id = "org.eclipse.passage.lic.equinox.tests.data.requirements"; //$NON-NLS-1$
	private final Cached<String, Bundle> bundle = new Cached<String, Bundle>(id, Platform::getBundle);

	@Override
	public Bundle get() {
		return bundle.get();
	}

	Set<Requirement> requirements() {
		return new HashSet<Requirement>(Arrays.asList(pi(), e()));
	}

	BaseRequirement e() {
		return new BaseRequirement(//
				new BaseFeature(//
						"E", //$NON-NLS-1$
						"2.71.82", //$NON-NLS-1$
						"Euler number", //$NON-NLS-1$
						"Euler"), //$NON-NLS-1$
				new RestrictionLevel.Info(), //
				id);
	}

	BaseRequirement pi() {
		return new BaseRequirement(//
				new BaseFeature(//
						"PI", //$NON-NLS-1$
						"3.14.15", //$NON-NLS-1$
						"PI of version PI", //$NON-NLS-1$
						"Eclipse Passage"), //$NON-NLS-1$
				new RestrictionLevel.Error(), //
				id);
	}

	List<BundleCapability> capabilities() {
		return get().adapt(BundleWiring.class).getCapabilities(//
				new LicensingFeatureCapabilitiesFromBundle(get()).key());
	}
}
