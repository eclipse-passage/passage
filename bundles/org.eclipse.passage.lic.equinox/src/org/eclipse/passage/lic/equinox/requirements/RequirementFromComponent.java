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

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.agreements.ResolvedAgreement;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.restrictions.RestrictionLevel;
import org.eclipse.passage.lic.base.requirements.BaseFeature;
import org.eclipse.passage.lic.base.requirements.BaseRequirement;
import org.eclipse.passage.lic.base.restrictions.DefaultRestrictionLevel;
import org.eclipse.passage.lic.base.version.DefaultVersion;
import org.eclipse.passage.lic.base.version.SafeVersion;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.runtime.dto.ComponentDescriptionDTO;

/**
 * Looks for {@linkplain Requirement} declarations in the given
 * {@code OSGi component} ({@linkplain ComponentDescriptionDTO}).
 * 
 * @see RequirementFromCapability
 * @see BundleRequirements
 */
final class RequirementFromComponent implements Supplier<Optional<Requirement>> {

	private final ComponentDescriptionDTO component;
	private final BundleContext context;

	public RequirementFromComponent(ComponentDescriptionDTO component, BundleContext context) {
		this.component = component;
		this.context = context;
	}

	@Override
	public Optional<Requirement> get() {
		Optional<Map<String, Object>> properties = Optional.ofNullable(component.properties);
		if (!properties.isPresent()) { // it's just a foreign component without licensing requirement declared
			return Optional.empty();
		}
		Optional<String> feature = new ComponentLicFeatureId(properties.get()).get();
		if (!feature.isPresent()) {
			// it's a component without licensing requirement declared too
			return Optional.empty();
		}
		return Optional.of(requirementFromProperties(feature.get(), properties.get()));
	}

	private Requirement requirementFromProperties(String feature, Map<String, Object> properties) {
		String version = new ComponentLicFeatureVersion(properties).get()//
				.map(raw -> new SafeVersion(raw).value())//
				.orElse(new DefaultVersion().value());
		String name = new ComponentLicFeatureName(properties).get()//
				.orElse(feature);
		Bundle bundle = context.getBundle(component.bundle.id);
		String provider = new ComponentLicFeatureProvider(properties).get()//
				.orElseGet(new BundleVendor(bundle));
		RestrictionLevel level = new ComponentLicFeatureLevel(properties).get()//
				.<RestrictionLevel>map(RestrictionLevel.Of::new) //
				.orElseGet(new DefaultRestrictionLevel());
		List<ResolvedAgreement> agreements = new BundleResidentAgreement.Pack(//
				bundle, //
				new ListOfAgreements().fromSource(new ComponentLicFeatureAgreements(properties))//
		).get();
		return new BaseRequirement(//
				new BaseFeature(feature, version, name, provider), //
				level, //
				agreements, //
				component.name);
	}

}
