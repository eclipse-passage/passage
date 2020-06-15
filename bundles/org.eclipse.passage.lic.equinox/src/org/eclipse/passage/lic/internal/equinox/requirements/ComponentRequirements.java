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

import java.util.Collection;
import java.util.Collections;
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
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.runtime.ServiceComponentRuntime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Looks for licensing {@linkplain Requirement} declarations among
 * {@code OSGi-component}s.
 * 
 * @see Requirement
 * @see ResolvedRequirements
 */
@SuppressWarnings("restriction")
public final class ComponentRequirements implements ResolvedRequirements {

	private final Logger logger = LoggerFactory.getLogger(BundleRequirements.class);

	private Optional<BundleContext> context;
	private Optional<ServiceComponentRuntime> runtime;

	public ComponentRequirements() {
		context = Optional.of(FrameworkUtil.getBundle(getClass()).getBundleContext());
		runtime = retrieveRuntime();
	}

	private Optional<ServiceComponentRuntime> retrieveRuntime() {
		BundleContext bundle = context.get();
		return Optional.ofNullable(bundle.getServiceReference(ServiceComponentRuntime.class))
				.map(ref -> bundle.getService(ref));
	}

	@Override
	public StringServiceId id() {
		return new StringServiceId("OSGi component"); //$NON-NLS-1$
	}

	@Activate
	public void activate(BundleContext bundle) {
		this.context = Optional.ofNullable(bundle);
	}

	@Deactivate
	public void deactivate() {
		this.context = Optional.empty();
	}

	@Reference
	public void bindRuntime(ServiceComponentRuntime input) {
		this.runtime = Optional.ofNullable(input);
	}

	public void unbindRuntime(ServiceComponentRuntime input) {
		if (!runtime.isPresent()) {
			return;
		}
		if (runtime.get() == input) {
			runtime = Optional.empty();
		}
	}

	@Override
	public Collection<Requirement> all() {
		if (!runtime.isPresent()) {
			return unsafisifiable(ServiceComponentRuntime.class.getSimpleName());
		}
		if (!context.isPresent()) {
			return unsafisifiable(BundleContext.class.getSimpleName());
		}
		return resolve();
	}

	private Collection<Requirement> unsafisifiable(String resource) {
		logger.error(NLS.bind(EquinoxMessages.ComponentRequirements_error_no_resource, resource));
		return Collections.singleton(//
				new UnsatisfiableRequirement(//
						NLS.bind(//
								EquinoxMessages.ComponentRequirements_requirement_for_resource, //
								resource, //
								getClass().getName()), //
						getClass()//
				).get());
	}

	private Collection<Requirement> resolve() {
		return runtime.get().getComponentDescriptionDTOs(context.get().getBundles()).stream()//
				.map(component -> new RequirementFromComponent(component, context.get())) //
				.map(RequirementFromComponent::get) //
				.filter(Optional::isPresent) //
				.map(Optional::get) //
				.collect(Collectors.toList());
	}

}
