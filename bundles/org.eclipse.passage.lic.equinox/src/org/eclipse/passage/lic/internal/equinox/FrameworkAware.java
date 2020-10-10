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

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.FrameworkSupplier;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.internal.base.diagnostic.code.NoFramework;
import org.eclipse.passage.lic.internal.base.diagnostic.code.SeveralFrameworks;
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
public abstract class FrameworkAware {

	private final BundleContext context;

	protected FrameworkAware() {
		this.context = FrameworkUtil.getBundle(getClass()).getBundleContext();
	}

	private Collection<ServiceReference<FrameworkSupplier>> frameworks() {
		try {
			// DI is used only to get rid of overwhelming dependencies here
			return context.getServiceReferences(FrameworkSupplier.class, null);
		} catch (InvalidSyntaxException e) {
			return Collections.emptyList();
		}
	}

	private <T> ServiceInvocationResult<T> noFramework() {
		return new BaseServiceInvocationResult<T>(//
				new Trouble(//
						new NoFramework(), //
						String.format(AccessMessages.EquinoxPassage_no_framewrok))//
		);
	}

	private <T> ServiceInvocationResult<T> severalFrameworks(Collection<ServiceReference<FrameworkSupplier>> findings) {
		return new BaseServiceInvocationResult<T>(//
				new BaseDiagnostic(//
						findings.stream()//
								.map(foreign -> foreignFramework(findings.size(), foreign)) //
								.collect(Collectors.toList()), //
						Collections.emptyList()//
				));
	}

	private Trouble foreignFramework(int singlings, ServiceReference<FrameworkSupplier> foreign) {
		return new Trouble(//
				new SeveralFrameworks(singlings), //
				String.format(AccessMessages.EquinoxPassage_foreign_framework, foreign.getBundle().getSymbolicName()));
	}

	protected <T> ServiceInvocationResult<T> withFrameworkService(
			Function<Framework, ServiceInvocationResult<T>> invoke) {
		return withReference(//
				reference -> //
				Optional.ofNullable(context.getService(reference))//
						.flatMap(FrameworkSupplier::get)//
						.map(invoke::apply)//
						.orElseGet(this::noFramework), //
				this::noFramework, //
				this::severalFrameworks);
	}

	protected <T> Optional<T> withFramework(Function<Framework, T> invoke) {
		return withReference(//
				reference -> //
				Optional.ofNullable(context.getService(reference))//
						.flatMap(FrameworkSupplier::get)//
						.map(invoke::apply), //
				Optional::empty, //
				any -> Optional.empty());
	}

	private <K> K withReference(//
			Function<ServiceReference<FrameworkSupplier>, K> onFramework, //
			Supplier<K> onNoFramework, //
			Function<Collection<ServiceReference<FrameworkSupplier>>, K> onSeveralFrameworks) {
		Collection<ServiceReference<FrameworkSupplier>> candidates = frameworks();
		if (candidates.isEmpty()) {
			return onNoFramework.get();
		}
		if (candidates.size() > 1) {
			// we have alien framework, the occurrence is treated as sabotage
			// here we can check signature of the seal:
			// ref.getBundle().getSignerCertificates(Bundle.SIGNERS_TRUSTED);
			return onSeveralFrameworks.apply(candidates);
		}
		ServiceReference<FrameworkSupplier> reference = candidates.iterator().next(); // size is precisely 1

		try {
			return onFramework.apply(reference);
		} finally {
			context.ungetService(reference);
		}
	}

}
