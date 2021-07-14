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

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.api.registry.StringServiceId;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.requirements.ResolvedRequirements;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.diagnostic.code.ServiceCannotOperate;
import org.eclipse.passage.lic.internal.equinox.i18n.EquinoxMessages;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.runtime.ServiceComponentRuntime;

/**
 * Looks for licensing {@linkplain Requirement} declarations among
 * {@code OSGi-component}s.
 * 
 * @see Requirement
 * @see ResolvedRequirements
 * @since 2.1
 */
public final class ComponentRequirements implements ResolvedRequirements {

	private final Optional<BundleContext> context;
	private final Optional<ServiceComponentRuntime> runtime;

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

	@Override
	public ServiceInvocationResult<Collection<Requirement>> all() {
		if (!runtime.isPresent()) {
			return noWay(ServiceComponentRuntime.class.getSimpleName());
		}
		if (!context.isPresent()) {
			return noWay(BundleContext.class.getSimpleName());
		}
		return new BaseServiceInvocationResult<Collection<Requirement>>(resolve());
	}

	private ServiceInvocationResult<Collection<Requirement>> noWay(String resource) {
		return new BaseServiceInvocationResult<Collection<Requirement>>(//
				new Trouble(new ServiceCannotOperate(), //
						String.format(EquinoxMessages.ComponentRequirements_requirement_for_resource, resource)));
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
