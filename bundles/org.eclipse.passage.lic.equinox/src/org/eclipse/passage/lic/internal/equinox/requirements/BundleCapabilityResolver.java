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

import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_FEATURE_NAME_DEFAULT;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_FEATURE_PROVIDER_DEFAULT;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.passage.lic.base.LicensingNamespaces;
import org.eclipse.passage.lic.base.LicensingVersions;
import org.eclipse.passage.lic.base.requirements.BaseConfigurationRequirement;
import org.eclipse.passage.lic.base.requirements.ConfigurationRequirements;
import org.eclipse.passage.lic.equinox.requirements.EquinoxRequirements;
import org.eclipse.passage.lic.runtime.LicensingRequirement;
import org.eclipse.passage.lic.runtime.RequirementResolver;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
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
	public Iterable<LicensingRequirement> resolveConfigurationRequirements(LicensingConfiguration configuration) {
		String nameLicensing = LICENSING_FEATURE_NAME_DEFAULT;
		String providerLicensing = LICENSING_FEATURE_PROVIDER_DEFAULT;
		if (bundleContext == null) {
			logger.severe("Unable to extract configuration requirements: invalid BundleContext");
			return ConfigurationRequirements.createErrorIterable(LicensingNamespaces.CAPABILITY_LICENSING_MANAGEMENT,
					LicensingVersions.VERSION_DEFAULT, nameLicensing, providerLicensing, configuration);
		}
		List<LicensingRequirement> result = new ArrayList<>();
		Bundle[] bundles = bundleContext.getBundles();
		for (Bundle bundle : bundles) {
			Iterable<BundleCapability> capabilities = EquinoxRequirements.extractLicensingFeatures(bundle);
			Dictionary<String, String> headers = bundle.getHeaders();
			String name = headers.get(Constants.BUNDLE_NAME);
			String vendor = headers.get(Constants.BUNDLE_VENDOR);
			for (BundleCapability capability : capabilities) {
				Map<String, Object> attributes = capability.getAttributes();
				Map<String, String> directives = capability.getDirectives();
				BundleRevision resource = capability.getResource();
				BaseConfigurationRequirement extracted = ConfigurationRequirements.extractFromCapability(name, vendor,
						attributes, directives, resource, configuration);
				if (extracted != null) {
					result.add(extracted);
				} else {
					logger.severe(String.format("Unable to extract configuration requirements: %s", resource));
					result.add(
							ConfigurationRequirements.createError(LicensingNamespaces.CAPABILITY_LICENSING_MANAGEMENT,
									LicensingVersions.VERSION_DEFAULT, name, providerLicensing, resource));
					return result;
				}
			}
		}
		return result;
	}

}
