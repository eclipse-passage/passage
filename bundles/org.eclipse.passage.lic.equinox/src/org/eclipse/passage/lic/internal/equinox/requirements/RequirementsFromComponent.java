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
import org.osgi.framework.BundleContext;
import org.osgi.service.component.runtime.dto.ComponentDescriptionDTO;

/**
 * Looks for {@linkplain Requirement} declarations in the given
 * {@code OSGi component} ({@linkplain ComponentDescriptionDTO}).
 * 
 * @see RequirementFromCapability
 * @see BundleRequirements
 */
@SuppressWarnings("restriction")
final class RequirementsFromComponent implements Supplier<Requirement> {

	private final ComponentDescriptionDTO component;
	private final BundleContext context;

	public RequirementsFromComponent(ComponentDescriptionDTO component, BundleContext context) {
		this.component = component;
		this.context = context;
	}

	@Override
	public Requirement get() {
		Optional<Map<String, Object>> properties = Optional.ofNullable(component.properties);
		if (!properties.isPresent()) {
			return unsatisfiableForProperties();
		}
		Optional<String> feature = new ComponentLicFeatureId(properties.get()).get();
		if (!feature.isPresent()) {
			return unsatisfiableForFeatureIdentifier();
		}
		return requirementFromProperties(feature.get(), properties.get());
	}

	private Requirement unsatisfiableForProperties() {
		return new UnsatisfiableRequirement(//
				NLS.bind(EquinoxMessages.RequirementsFromComponent_no_properties, component.name), //
				getClass()//
		).get();
	}

	private Requirement unsatisfiableForFeatureIdentifier() {
		return new UnsatisfiableRequirement(//
				NLS.bind(EquinoxMessages.RequirementsFromComponent_no_feature_id, component.name), //
				getClass()//
		).get();
	}

	private Requirement requirementFromProperties(String feature, Map<String, Object> properties) {
		String version = new ComponentLicFeatureVersion(properties).get()//
				.map(LicensingVersions::toVersionValue)//
				.orElse(LicensingVersions.VERSION_DEFAULT);
		String name = new ComponentLicFeatureName(properties).get()//
				.orElse(feature);
		String provider = new ComponentLicFeatureProvider(properties).get()//
				.orElseGet(new BundleVendor(context.getBundle(component.bundle.id)));
		RestrictionLevel level = new ComponentLicFeatureLevel(properties).get()//
				.<RestrictionLevel>map(RestrictionLevel.Of::new) //
				.orElseGet(new DefaultRestrictionLevel());
		return new BaseRequirement(//
				new BaseFeature(feature, version, name, provider), //
				level, //
				component);
	}

}
