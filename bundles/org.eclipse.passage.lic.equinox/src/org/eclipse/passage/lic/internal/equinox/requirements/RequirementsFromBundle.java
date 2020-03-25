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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.base.LicensingVersions;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.RestrictionLevel;
import org.eclipse.passage.lic.internal.base.requirements.BaseFeature;
import org.eclipse.passage.lic.internal.base.requirements.BaseRequirement;
import org.eclipse.passage.lic.internal.base.requirements.UnsatisfiableRequirement;
import org.eclipse.passage.lic.internal.base.restrictions.DefaultRestrictionLevel;
import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;
import org.osgi.framework.wiring.BundleCapability;

@SuppressWarnings("restriction")
final class RequirementsFromBundle implements Supplier<Optional<List<Requirement>>> {
	private final Bundle bundle;

	public RequirementsFromBundle(Bundle bundle) {
		this.bundle = bundle;
	}

	@Override
	public Optional<List<Requirement>> get() {
		LicensingFeaturesFromBundle licensingFeaturesFromBundle = new LicensingFeaturesFromBundle(bundle);
		Optional<List<BundleCapability>> capabilities = licensingFeaturesFromBundle.get();
		if (capabilities.isPresent()) {
			return Optional.empty();
		}
		String name = bundleName();
		String vendor = bundleVendor();
		List<Requirement> results = new ArrayList<>();
		for (BundleCapability capability : capabilities.get()) {
			Map<String, Object> attributes = capability.getAttributes();
			if (attributes == null) {
				results.add(new UnsatisfiableRequirement(//
						"Attributes for " + licensingFeaturesFromBundle.key() + //$NON-NLS-1$
								" capability of " + name //$NON-NLS-1$
								+ " bundle", //$NON-NLS-1$
						bundle).get());
				continue;
			}
			CapabilityLicFeatureId capabilityLicFeatureId = new CapabilityLicFeatureId(attributes);
			Optional<String> feature = capabilityLicFeatureId.get();
			if (!feature.isPresent()) {
				results.add(new UnsatisfiableRequirement(//
						licensingFeaturesFromBundle.key() + " capability attribute " + capabilityLicFeatureId.key() //$NON-NLS-1$
								+ " for " + name //$NON-NLS-1$
								+ " bundle", //$NON-NLS-1$
						bundle).get());
				continue;
			}
			String version = new CapabilityLicFeatureVersion(attributes).get()//
					.map(LicensingVersions::toVersionValue)//
					.orElse(LicensingVersions.VERSION_DEFAULT);

			String featureName = new CapabilityLicFeatureName(attributes).get().orElse(feature.get());
			String provider = new CapabilityLicFeatureProvider(attributes).get().orElse(vendor);
			RestrictionLevel level = new CapabilityLicFeatureLevel(attributes).get()//
					.<RestrictionLevel>map(RestrictionLevel.Of::new) //
					.orElseGet(new DefaultRestrictionLevel());

			results.add(new BaseRequirement(//
					new BaseFeature(feature.get(), version, featureName, provider), //
					level, //
					capability.getResource()));
		}
		return Optional.of(results);
	}

	private String bundleName() {
		return bundle.getHeaders().get(Constants.BUNDLE_NAME);
	}

	private String bundleVendor() {
		return bundle.getHeaders().get(Constants.BUNDLE_VENDOR);
	}

}
