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
import java.util.Collection;
import java.util.List;

import org.eclipse.passage.lic.base.LicensingNamespaces;
import org.eclipse.passage.lic.base.LicensingVersions;
import org.eclipse.passage.lic.base.requirements.BaseConfigurationRequirement;
import org.eclipse.passage.lic.base.requirements.ConfigurationRequirements;
import org.eclipse.passage.lic.runtime.ConfigurationRequirement;
import org.eclipse.passage.lic.runtime.RequirementResolver;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
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
	public void bindLoggerFactory(LoggerFactory loggerFactory) {
		this.logger = loggerFactory.getLogger(ComponentConfigurationResolver.class);
	}

	public void unbindLoggerFactory(LoggerFactory loggerFactory) {
		this.logger = null;
	}
	
	@Reference
	public void bindScr(ServiceComponentRuntime scr) {
		this.scr = scr;
	}

	public void unbindScr(ServiceComponentRuntime scr) {
		this.scr = null;
	}
	
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
		if (scr == null) {
			logger.audit("Unable to extract configuration requirements: invalid ServiceComponentRuntime");
			return ConfigurationRequirements.createErrorIterable(LicensingNamespaces.CAPABILITY_LICENSING_MANAGEMENT, LicensingVersions.VERSION_DEFAULT, lmName, this, configuration);
		}
		if (bundleContext == null) {
			logger.audit("Unable to extract configuration requirements: invalid BundleContext");
			return ConfigurationRequirements.createErrorIterable(LicensingNamespaces.CAPABILITY_LICENSING_MANAGEMENT, LicensingVersions.VERSION_DEFAULT, lmName, this, configuration);
		}
		List<ConfigurationRequirement> result = new ArrayList<>();
		Bundle[] bundles = bundleContext.getBundles();
		Collection<ComponentDescriptionDTO> components = scr.getComponentDescriptionDTOs(bundles);
		for (ComponentDescriptionDTO component : components) {
			BaseConfigurationRequirement requirement = ConfigurationRequirements.extractFromProperties(component.properties, component, configuration);
			if (requirement != null) {
				result.add(requirement);
			}
		}
		return result;
	}

}
