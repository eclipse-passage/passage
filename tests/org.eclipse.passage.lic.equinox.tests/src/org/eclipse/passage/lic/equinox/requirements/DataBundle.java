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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Platform;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.restrictions.RestrictionLevel;
import org.eclipse.passage.lic.base.requirements.BaseFeature;
import org.eclipse.passage.lic.base.requirements.BaseRequirement;
import org.eclipse.passage.lic.internal.base.Cached;
import org.osgi.framework.Bundle;
import org.osgi.framework.wiring.BundleCapability;
import org.osgi.framework.wiring.BundleWiring;

final class DataBundle {

	private final String id = "org.eclipse.passage.lic.equinox.tests.data.requirements"; //$NON-NLS-1$
	private final Cached<String, Bundle> bundle = new Cached<String, Bundle>(id, Platform::getBundle);

	Bundle bundle() {
		return bundle.get();
	}

	List<BundleCapability> capabilities() {
		return bundle().adapt(BundleWiring.class).getCapabilities(//
				new LicensingFeatureCapabilitiesFromBundle(bundle()).key());
	}

	Set<Requirement> validRequirementsFromCapabilities() {
		return new HashSet<Requirement>(Arrays.asList(pi(), e(), incomplete()));
	}

	Set<Requirement> validRequirementsFromComponents() {
		return new HashSet<Requirement>(Arrays.asList(evilWitch(), goodWitch()));
	}

	BaseRequirement e() {
		return new BaseRequirement(//
				new BaseFeature(//
						"E", //$NON-NLS-1$
						"2.71.82", //$NON-NLS-1$
						"Euler number", //$NON-NLS-1$
						"Euler"), //$NON-NLS-1$
				new RestrictionLevel.Info(), //
				Arrays.asList(//
						new BundleResidentAgreement(bundle.get(), "Honor Euler.txt"), //$NON-NLS-1$
						new BundleResidentAgreement(bundle.get(), "comp_lics/EULERS IDENTITY")), //$NON-NLS-1$
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

	BaseRequirement incomplete() {
		return new BaseRequirement(//
				new BaseFeature(//
						"Incomplete", //$NON-NLS-1$
						"0.0.0", //$NON-NLS-1$
						"Incomplete", //$NON-NLS-1$
						"Eclipse Passage"), //$NON-NLS-1$
				new RestrictionLevel.Warning(), //
				id);
	}

	BaseRequirement evilWitch() {
		return new BaseRequirement(//
				new BaseFeature(//
						"EvilWitch", //$NON-NLS-1$
						"13.4.1", //$NON-NLS-1$
						"Evil Witch", //$NON-NLS-1$
						"Universe"), //$NON-NLS-1$
				new RestrictionLevel.Info(), //
				"org.eclipse.passage.lic.equinox.tests.data.requirements.EvilWitch" //$NON-NLS-1$
		);
	}

	BaseRequirement goodWitch() {
		return new BaseRequirement(//
				new BaseFeature(//
						"GoodWitch", //$NON-NLS-1$
						"0.0.0", //$NON-NLS-1$
						"GoodWitch", //$NON-NLS-1$
						"Eclipse Passage"), //$NON-NLS-1$
				new RestrictionLevel.Fatal(), //
				"org.eclipse.passage.lic.equinox.tests.data.requirements.GoodWitch" //$NON-NLS-1$
		);
	}

}
