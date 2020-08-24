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
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.FrameworkSupplier;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.diagnostic.code.NoFramework;
import org.eclipse.passage.lic.internal.equinox.i18n.AccessMessages;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

/**
 * <p>
 * Base for services relying in a framework facilities.
 * </p>
 * <p>
 * Use {@code withFrameworkService} to implement client level secondary services
 * or {@code withFramework} to retrieve parts of configuration directly.
 */
@SuppressWarnings("restriction")
public abstract class FrameworkAware {

	private final String friend = "org.eclipse.passage.seal.demo"; //$NON-NLS-1$

	private final BundleContext context;

	protected FrameworkAware() {
		this.context = FrameworkUtil.getBundle(getClass()).getBundleContext();
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

	protected <T> ServiceInvocationResult<T> withFrameworkService(
			Function<Framework, ServiceInvocationResult<T>> invoke) {
		return withReference(//
				reference -> //
				Optional.ofNullable(context.getService(reference))//
						.flatMap(FrameworkSupplier::get)//
						.map(invoke::apply)//
						.orElseGet(this::noFramework), //
				this::noFramework);
	}

	protected <T> Optional<T> withFramework(Function<Framework, T> invoke) {
		return withReference(//
				reference -> //
				Optional.ofNullable(context.getService(reference))//
						.flatMap(FrameworkSupplier::get)//
						.map(invoke::apply), //
				Optional::empty);
	}

	protected <K> K withReference(Function<ServiceReference<FrameworkSupplier>, K> action, Supplier<K> noAction) {
		Optional<ServiceReference<FrameworkSupplier>> candidate = frameworkIfAny();
		if (!candidate.isPresent()) {
			return noAction.get();
		}
		ServiceReference<FrameworkSupplier> reference = candidate.get();
		try {
			return action.apply(reference);
		} finally {
			context.ungetService(reference);
		}
	}

}
