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
import java.util.Collection;
import java.util.Dictionary;
import java.util.List;

import org.eclipse.passage.lic.base.LicensingNamespaces;
import org.eclipse.passage.lic.base.LicensingVersions;
import org.eclipse.passage.lic.base.requirements.LicensingRequirements;
import org.eclipse.passage.lic.internal.equinox.EquinoxMessages;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.requirements.LicensingRequirement;
import org.eclipse.passage.lic.runtime.requirements.RequirementResolver;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.runtime.ServiceComponentRuntime;
import org.osgi.service.component.runtime.dto.ComponentDescriptionDTO;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;

@Component
public class ComponentConfigurationResolver implements RequirementResolver {

	private Logger logger;
	private BundleContext bundleContext;
	private ServiceComponentRuntime scr;

	@Reference
	public void bindLoggerFactory(LoggerFactory factory) {
		this.logger = factory.getLogger(ComponentConfigurationResolver.class);
	}

	public void unbindLoggerFactory(LoggerFactory factory) {
		if (this.logger == factory) {
			this.logger = null;
		}
	}

	@Reference
	public void bindScr(ServiceComponentRuntime runtime) {
		this.scr = runtime;
	}

	public void unbindScr(ServiceComponentRuntime runtime) {
		if (this.scr == runtime) {
			this.scr = null;
		}
	}

	@Activate
	public void activate(BundleContext context) {
		this.bundleContext = context;
	}

	@Deactivate
	public void deactivate() {
		this.bundleContext = null;
	}

	@Override
	public Iterable<LicensingRequirement> resolveLicensingRequirements(LicensingConfiguration configuration) {
		String nameLicensing = LICENSING_FEATURE_NAME_DEFAULT;
		String providerLicensing = LICENSING_FEATURE_PROVIDER_DEFAULT;
		if (scr == null) {
			logger.audit(EquinoxMessages.ComponentConfigurationResolver_error_invalid_component_rt);
			return LicensingRequirements.createErrorIterable(LicensingNamespaces.CAPABILITY_LICENSING_MANAGEMENT,
					LicensingVersions.VERSION_DEFAULT, nameLicensing, providerLicensing, configuration);
		}
		if (bundleContext == null) {
			logger.audit(EquinoxMessages.ComponentConfigurationResolver_error_invalid_bundle_context);
			return LicensingRequirements.createErrorIterable(LicensingNamespaces.CAPABILITY_LICENSING_MANAGEMENT,
					LicensingVersions.VERSION_DEFAULT, nameLicensing, providerLicensing, configuration);
		}
		List<LicensingRequirement> result = new ArrayList<>();
		Bundle[] bundles = bundleContext.getBundles();
		Collection<ComponentDescriptionDTO> components = scr.getComponentDescriptionDTOs(bundles);
		for (ComponentDescriptionDTO component : components) {
			Bundle bundle = bundleContext.getBundle(component.bundle.id);
			Dictionary<String, String> headers = bundle.getHeaders();
			String name = headers.get(Constants.BUNDLE_NAME);
			String vendor = headers.get(Constants.BUNDLE_VENDOR);
			LicensingRequirement requirement = LicensingRequirements.extractFromProperties(name, vendor,
					component.properties, component);
			if (requirement != null) {
				result.add(requirement);
			}
		}
		return result;
	}

}
