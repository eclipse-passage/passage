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
package org.eclipse.passage.lic.equinox;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.Framework;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.FrameworkAware;
import org.eclipse.passage.lic.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.base.diagnostic.code.NoFramework;
import org.eclipse.passage.lic.base.diagnostic.code.SeveralFrameworks;
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
 * 
 * @since 2.1
 */
public abstract class EquinoxFrameworkAware<S> implements FrameworkAware {

	private final BundleContext context;
	private final Class<S> component;
	private final Function<S, Optional<Framework>> constructor;

	protected EquinoxFrameworkAware(Class<S> cls, Function<S, Optional<Framework>> constructor) {
		// get this exact bundle, not a bundle of an ancestor class
		this.context = FrameworkUtil.getBundle(EquinoxFrameworkAware.class).getBundleContext();
		this.component = cls;
		this.constructor = constructor;
	}

	@Override
	public final <T> ServiceInvocationResult<T> withFrameworkService(
			Function<Framework, ServiceInvocationResult<T>> invoke) {
		return withReference(//
				reference -> //
				Optional.ofNullable(context.getService(reference))//
						.flatMap(constructor::apply)//
						.map(invoke::apply)//
						.orElseGet(this::noFramework), //
				this::noFramework, //
				this::severalFrameworks);
	}

	@Override
	public final <T> Optional<T> withFramework(Function<Framework, T> invoke) {
		return withReference(//
				reference -> //
				Optional.ofNullable(context.getService(reference))//
						.flatMap(constructor::apply)//
						.map(invoke::apply), //
				Optional::empty, //
				any -> Optional.empty());
	}

	private <T> ServiceInvocationResult<T> noFramework() {
		return new BaseServiceInvocationResult<T>(//
				new Trouble(//
						new NoFramework(), //
						String.format(AccessMessages.EquinoxPassage_no_framework))//
		);
	}

	private <T> ServiceInvocationResult<T> severalFrameworks(Collection<ServiceReference<S>> findings) {
		return new BaseServiceInvocationResult<T>(//
				new BaseDiagnostic(//
						findings.stream()//
								.map(foreign -> foreignFramework(findings.size(), foreign)) //
								.collect(Collectors.toList()), //
						Collections.emptyList()//
				));
	}

	private Trouble foreignFramework(int singlings, ServiceReference<S> foreign) {
		return new Trouble(//
				new SeveralFrameworks(singlings), //
				String.format(AccessMessages.EquinoxPassage_foreign_framework, foreign.getBundle().getSymbolicName()));
	}

	private <K> K withReference(//
			Function<ServiceReference<S>, K> onFramework, //
			Supplier<K> onNoFramework, //
			Function<Collection<ServiceReference<S>>, K> onSeveralFrameworks) {
		Collection<ServiceReference<S>> candidates = frameworks();
		if (candidates.isEmpty()) {
			return onNoFramework.get();
		}
		if (candidates.size() > 1) {
			// we have alien framework, the occurrence is treated as sabotage
			// here we can check signature of the seal:
			// ref.getBundle().getSignerCertificates(Bundle.SIGNERS_TRUSTED);
			return onSeveralFrameworks.apply(candidates);
		}
		ServiceReference<S> reference = candidates.iterator().next(); // size is precisely 1
		try {
			return onFramework.apply(reference);
		} finally {
			context.ungetService(reference);
		}
	}

	private Collection<ServiceReference<S>> frameworks() {
		try {
			// DI is used only to get rid of overwhelming dependencies here
			return context.getServiceReferences(component, null);
		} catch (InvalidSyntaxException e) {
			return Collections.emptyList();
		}
	}

}
