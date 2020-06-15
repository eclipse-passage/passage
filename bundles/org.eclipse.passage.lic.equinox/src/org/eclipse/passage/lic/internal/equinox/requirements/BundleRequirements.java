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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements;
import org.eclipse.passage.lic.internal.base.requirements.UnsatisfiableRequirement;
import org.eclipse.passage.lic.internal.equinox.i18n.EquinoxMessages;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * {@linkplain ResolvedRequirements} service implementation which search for
 * {@linkplain Requirement} declarations in an OSGi-bundles meta information:
 * among <i>Provide-Capability</i> declarations.
 * </p>
 * 
 * @see Requirement
 * @see ResolvedRequirements
 */
@SuppressWarnings("restriction")
public final class BundleRequirements implements ResolvedRequirements {

	private final Logger logger = LoggerFactory.getLogger(BundleRequirements.class);
	private Optional<BundleContext> context;

	@Activate
	public void activate() {
		this.context = Optional.ofNullable(FrameworkUtil.getBundle(getClass()).getBundleContext());
	}

	@Deactivate
	public void deactivate() {
		this.context = Optional.empty();
	}

	@Override
	public StringServiceId id() {
		return new StringServiceId("manifest"); //$NON-NLS-1$
	}

	@Override
	public Collection<Requirement> all() {
		if (sabotage()) {
			return unsafisifiable();
		}
		return resolve();
	}

	private boolean sabotage() {
		return !context.isPresent();
	}

	private Collection<Requirement> unsafisifiable() {
		logger.error(EquinoxMessages.BundleRequirements_error_bundle_context);
		return Collections.singleton(//
				new UnsatisfiableRequirement(//
						NLS.bind(EquinoxMessages.BundleRequirements_no_context, getClass().getName()), //
						getClass()//
				).get());
	}

	private Collection<Requirement> resolve() {
		return Arrays.stream(context.get().getBundles())//
				.map(RequirementsFromBundle::new)//
				.map(RequirementsFromBundle::get) //
				.filter(Optional::isPresent)//
				.map(Optional::get)//
				.flatMap(List::stream) //
				.collect(Collectors.toList());
	}

}
