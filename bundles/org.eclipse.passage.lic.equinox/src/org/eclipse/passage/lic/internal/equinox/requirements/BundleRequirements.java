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

import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_FEATURE_NAME_DEFAULT;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_FEATURE_PROVIDER_DEFAULT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Dictionary;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.api.requirements.LicensingRequirement;
import org.eclipse.passage.lic.base.LicensingNamespaces;
import org.eclipse.passage.lic.base.LicensingVersions;
import org.eclipse.passage.lic.base.requirements.LicensingRequirements;
import org.eclipse.passage.lic.equinox.requirements.EquinoxRequirements;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements;
import org.eclipse.passage.lic.internal.base.requirements.UnsatisfiableRequirement;
import org.eclipse.passage.lic.internal.equinox.i18n.EquinoxMessages;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.wiring.BundleCapability;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("restriction")
@Component
public class BundleRequirements implements ResolvedRequirements {

	private final Logger logger = LoggerFactory.getLogger(BundleRequirements.class);
	private BundleContext context;

	@Activate
	public void activate(BundleContext bundle) {
		this.context = bundle;
	}

	@Deactivate
	public void deactivate() {
		this.context = null;
	}

	@Override
	public StringServiceId id() {
		return new StringServiceId("manifest"); //$NON-NLS-1$
	}

	@Override
	public Collection<Requirement> all(LicensingConfiguration configuration) {
		if (sabotage()) {
			return unsafisifiable();
		}
		return resolve();
	}

	private boolean sabotage() {
		return context == null;
	}

	private Collection<Requirement> unsafisifiable() {
		return Collections.singleton(//
				new UnsatisfiableRequirement(//
						"Bundle context for " + getClass().getName() + " OSGi-component", //$NON-NLS-1$ //$NON-NLS-2$
						getClass()//
				).get());
	}

	private Collection<Requirement> resolve() {
		return Arrays.stream(context.getBundles())//
				.map(RequirementsFromBundle::new)//
				.map(RequirementsFromBundle::get) //
				.filter(Optional::isPresent) //
				.map(Optional<List<Requirement>>::get) //
				.flatMap(List::stream) //
				.collect(Collectors.toList());

	}

}
