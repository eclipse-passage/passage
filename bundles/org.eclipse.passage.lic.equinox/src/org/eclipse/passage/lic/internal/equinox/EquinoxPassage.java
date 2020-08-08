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
package org.eclipse.passage.lic.internal.equinox;

import java.util.Optional;
import java.util.function.Function;

import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.FrameworkSupplier;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.Passage;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.access.Access;
import org.eclipse.passage.lic.internal.base.diagnostic.code.NoFramework;
import org.eclipse.passage.lic.internal.equinox.i18n.AccessMessages;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

@SuppressWarnings("restriction")
public final class EquinoxPassage implements Passage {

	private final String friend = "org.eclipse.passage.seal.demo"; //$NON-NLS-1$

	private final BundleContext context;

	public EquinoxPassage() {
		this.context = FrameworkUtil.getBundle(getClass()).getBundleContext();
	}

	@Override
	public boolean canUse(String feature) {
		return invokeAndUnget(frameworkIfAny(), //
				f -> new BaseServiceInvocationResult<>(new Access(f).canUse(feature)))//
						.data().orElse(Boolean.FALSE);
	}

	@Override
	public ServiceInvocationResult<ExaminationCertificate> checkLicense(String feature) {
		return invokeAndUnget(frameworkIfAny(), f -> new Access(f).check(feature));
	}

	@Override
	public ServiceInvocationResult<LicensedProduct> product() {
		Optional<ServiceReference<FrameworkSupplier>> framework = frameworkIfAny();
		if (!framework.isPresent()) {
			return noFramework();
		}
		return invokeAndUnget(frameworkIfAny(), f -> new BaseServiceInvocationResult<>(f.product()));
	}

	private Optional<ServiceReference<FrameworkSupplier>> frameworkIfAny() {
		try {
			return context.getServiceReferences(FrameworkSupplier.class, null).stream() //
					// DI is used only to get rid of overwhelming dependencies here
					// here we can check signature of the seal
					.filter(supplier -> supplier.getBundle().getSymbolicName().equals(friend))//
					.findAny();
		} catch (InvalidSyntaxException e) {
			return Optional.empty();
		}
	}

	private <T> ServiceInvocationResult<T> noFramework() {
		return new BaseServiceInvocationResult<T>(//
				new Trouble(//
						new NoFramework(), //
						String.format(AccessMessages.EquinoxPassage_no_framewrok, friend))//
		);
	}

	private <T> ServiceInvocationResult<T> invokeAndUnget(Optional<ServiceReference<FrameworkSupplier>> candidate,
			Function<Framework, ServiceInvocationResult<T>> invoke) {
		if (candidate.isEmpty()) {
			return noFramework();
		}
		ServiceReference<FrameworkSupplier> reference = candidate.get();
		try {
			Optional<Framework> framework = Optional.ofNullable(context.getService(reference))//
					.flatMap(FrameworkSupplier::get);
			if (framework.isEmpty()) {
				return noFramework();
			}
			return invoke.apply(framework.get());
		} finally {
			context.ungetService(reference);
		}
	}

}
