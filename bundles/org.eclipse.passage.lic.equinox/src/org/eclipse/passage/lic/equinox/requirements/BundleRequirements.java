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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.api.registry.StringServiceId;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.requirements.ResolvedRequirements;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.SumOfCollections;
import org.eclipse.passage.lic.base.diagnostic.code.ServiceCannotOperate;
import org.eclipse.passage.lic.internal.equinox.i18n.EquinoxMessages;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * <p>
 * {@linkplain ResolvedRequirements} service implementation which search for
 * {@linkplain Requirement} declarations in an OSGi-bundles meta information:
 * among <i>Provide-Capability</i> declarations.
 * </p>
 * 
 * @see Requirement
 * @see ResolvedRequirements
 * @since 2.1
 */
public final class BundleRequirements implements ResolvedRequirements {

	private final Optional<BundleContext> context;

	public BundleRequirements() {
		context = Optional.of(FrameworkUtil.getBundle(getClass()).getBundleContext());
	}

	@Override
	public StringServiceId id() {
		return new StringServiceId("manifest"); //$NON-NLS-1$
	}

	@Override
	public ServiceInvocationResult<Collection<Requirement>> all() {
		if (sabotage()) {
			return new BaseServiceInvocationResult<Collection<Requirement>>(//
					new Trouble(new ServiceCannotOperate(), EquinoxMessages.BundleRequirements_no_context));
		}
		return resolve();
	}

	private boolean sabotage() {
		return !context.isPresent();
	}

	private ServiceInvocationResult<Collection<Requirement>> resolve() {
		return Arrays.stream(context.get().getBundles())//
				.map(RequirementsFromBundle::new)//
				.map(RequirementsFromBundle::get) //
				.filter(Optional::isPresent)//
				.map(Optional::get)//
				.reduce(new BaseServiceInvocationResult.Sum<>(new SumOfCollections<Requirement>()))//
				.orElse(new BaseServiceInvocationResult<>(Collections.emptyList()));
	}

}
