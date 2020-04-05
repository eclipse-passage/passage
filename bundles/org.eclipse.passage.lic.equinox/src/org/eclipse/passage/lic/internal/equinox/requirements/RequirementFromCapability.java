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

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.base.LicensingVersions;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.RestrictionLevel;
import org.eclipse.passage.lic.internal.base.requirements.BaseFeature;
import org.eclipse.passage.lic.internal.base.requirements.BaseRequirement;
import org.eclipse.passage.lic.internal.base.requirements.UnsatisfiableRequirement;
import org.eclipse.passage.lic.internal.base.restrictions.DefaultRestrictionLevel;
import org.eclipse.passage.lic.internal.equinox.i18n.EquinoxMessages;
import org.osgi.framework.Bundle;
import org.osgi.framework.wiring.BundleCapability;

/**
 * Looks for {@linkplain Requirement} declaration in a single
 * {@code Capability}.
 * 
 * @see RequirementsFromBundle
 * @see BundleRequirements
 */
@SuppressWarnings("restriction")
final class RequirementFromCapability implements Supplier<Requirement> {

	private final Bundle bundle;
	private final BundleCapability capability;

	public RequirementFromCapability(Bundle bundle, BundleCapability capability) {
		this.bundle = bundle;
		this.capability = capability;
	}

	@Override
	public Requirement get() {
		Optional<Map<String, Object>> attributes = Optional.ofNullable(capability.getAttributes());
		if (!attributes.isPresent()) {
			return requirementForAttributes();
		}
		Optional<String> feature = new CapabilityLicFeatureId(attributes.get()).get();
		if (!feature.isPresent()) {
			return requirementForFeatureIdentifier();
		}
		return requirementFromAttributes(feature.get(), attributes.get());
	}

	private Requirement requirementFromAttributes(String feature, Map<String, Object> attributes) {
		System.out.println(">>" + attributes); //$NON-NLS-1$
		String version = new CapabilityLicFeatureVersion(attributes).get()//
				.map(LicensingVersions::toVersionValue)//
				.orElse(LicensingVersions.VERSION_DEFAULT);
		String name = new CapabilityLicFeatureName(attributes).get()//
				.orElse(feature);
		String provider = new CapabilityLicFeatureProvider(attributes).get()//
				.orElseGet(new BundleVendor(bundle));
		RestrictionLevel level = new CapabilityLicFeatureLevel(attributes).get()//
				.<RestrictionLevel>map(RestrictionLevel.Of::new) //
				.orElseGet(new DefaultRestrictionLevel());
		BaseRequirement requirement = new BaseRequirement(//
				new BaseFeature(feature, version, name, provider), //
				level, //
				capability.getResource().getBundle().getSymbolicName());
		return requirement;
	}

	private Requirement requirementForAttributes() {
		return new UnsatisfiableRequirement(//
				NLS.bind(EquinoxMessages.RequirementsFromCapability_no_attributes, //
						capability.getNamespace(), new BundleName(bundle).get()), //
				bundle//
		).get();
	}

	private Requirement requirementForFeatureIdentifier() {
		return new UnsatisfiableRequirement(//
				NLS.bind(EquinoxMessages.RequirementsFromCapability_no_feature_id, //
						capability.getNamespace(), //
						new BundleName(bundle).get()), //
				bundle //
		).get();
	}

}
