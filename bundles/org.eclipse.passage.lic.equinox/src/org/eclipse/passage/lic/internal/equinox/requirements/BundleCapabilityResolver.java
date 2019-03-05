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
package org.eclipse.passage.lic.internal.equinox.requirements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.passage.lic.base.LicensingNamespaces;
import org.eclipse.passage.lic.base.LicensingVersions;
import org.eclipse.passage.lic.base.requirements.BaseConfigurationRequirement;
import org.eclipse.passage.lic.base.requirements.ConfigurationRequirements;
import org.eclipse.passage.lic.equinox.requirements.EquinoxRequirements;
import org.eclipse.passage.lic.runtime.ConfigurationRequirement;
import org.eclipse.passage.lic.runtime.RequirementResolver;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.wiring.BundleCapability;
import org.osgi.framework.wiring.BundleRevision;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

@Component
public class BundleCapabilityResolver implements RequirementResolver {

	private Logger logger;
	private BundleContext bundleContext;

	@Activate
	public void activate(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

	@Deactivate
	public void deactivate() {
		this.bundleContext = null;
	}

	@Override
	public Iterable<ConfigurationRequirement> resolveConfigurationRequirements(LicensingConfiguration configuration) {
		String lmName = "License Management";
		if (bundleContext == null) {
			logger.severe("Unable to extract configuration requirements: invalid BundleContext");
			return ConfigurationRequirements.createErrorIterable(LicensingNamespaces.CAPABILITY_LICENSING_MANAGEMENT,
					LicensingVersions.VERSION_DEFAULT, lmName, this, configuration);
		}
		List<ConfigurationRequirement> result = new ArrayList<>();
		Bundle[] bundles = bundleContext.getBundles();
		for (Bundle bundle : bundles) {
			Iterable<BundleCapability> capabilities = EquinoxRequirements.extractLicensingFeatures(bundle);
			for (BundleCapability capability : capabilities) {
				Map<String, Object> attributes = capability.getAttributes();
				Map<String, String> directives = capability.getDirectives();
				BundleRevision resource = capability.getResource();
				BaseConfigurationRequirement extracted = ConfigurationRequirements.extractFromCapability(attributes,
						directives, resource, configuration);
				if (extracted != null) {
					result.add(extracted);
				} else {
					logger.severe(String.format("Unable to extract configuration requirements: %s", resource));
					result.add(
							ConfigurationRequirements.createError(LicensingNamespaces.CAPABILITY_LICENSING_MANAGEMENT,
									LicensingVersions.VERSION_DEFAULT, lmName, resource, configuration));
					return result;
				}
			}
		}
		return result;
	}

}
