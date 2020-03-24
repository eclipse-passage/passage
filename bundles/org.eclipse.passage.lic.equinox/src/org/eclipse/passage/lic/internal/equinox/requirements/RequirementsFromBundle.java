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

import static org.eclipse.passage.lic.base.LicensingNamespaces.ATTRIBUTE_LEVEL;
import static org.eclipse.passage.lic.base.LicensingNamespaces.ATTRIBUTE_NAME;
import static org.eclipse.passage.lic.base.LicensingNamespaces.ATTRIBUTE_PROVIDER;
import static org.eclipse.passage.lic.base.LicensingNamespaces.ATTRIBUTE_VERSION;
import static org.eclipse.passage.lic.base.LicensingNamespaces.toLevelAttribute;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.base.LicensingVersions;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.base.requirements.UnsatisfiableRequirement;
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
		Dictionary<String, String> headers = bundle.getHeaders();
		String name = headers.get(Constants.BUNDLE_NAME);
		String vendor = headers.get(Constants.BUNDLE_VENDOR);
		List<Requirement> results = new ArrayList<>();
		for (BundleCapability capability : capabilities.get()) {
			Map<String, Object> attributes = capability.getAttributes();
			if (attributes == null) {
				results.add(new UnsatisfiableRequirement(//
						"Attributes for " + licensingFeaturesFromBundle.key() + //$NON-NLS-1$
								" capability of " + bundle.getBundleId() //$NON-NLS-1$
								+ " bundle", //$NON-NLS-1$
						bundle).get());
				continue;
			}
			Optional<String> feature = new CapabilityLicensingFeature(attributes).get();

			String version = LicensingVersions.toVersionValue(attributes.get(ATTRIBUTE_VERSION));
			String name = getStringValue(attributes, ATTRIBUTE_NAME, name);
			if (name == null) {
				name = featureId;
			}
			String provider = getStringValue(attributes, ATTRIBUTE_PROVIDER, vendor);
			String level = toLevelAttribute(attributes.get(ATTRIBUTE_LEVEL));
			results.add(new BaseLicensingRequirement(featureId, version, name, provider, level, source));
		}
		return Optional.of(results);
	}
}
