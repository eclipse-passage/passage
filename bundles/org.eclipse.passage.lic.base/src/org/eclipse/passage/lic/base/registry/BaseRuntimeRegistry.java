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
package org.eclipse.passage.lic.base.registry;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Consumer;

import org.eclipse.passage.lic.api.registry.Registry;
import org.eclipse.passage.lic.api.registry.RuntimeRegistry;
import org.eclipse.passage.lic.api.registry.Service;
import org.eclipse.passage.lic.api.registry.ServiceId;
import org.eclipse.passage.lic.internal.base.i18n.BaseMessages;

/**
 * <p>
 * {@linkplain Registry} implementation that is filled and managed at runtime
 * programmatically.
 * </p>
 * 
 * <p>
 * Not thread safe (yet)
 * </p>
 * <p>
 * Null free zone.
 * </p>
 * 
 * @param <S> type of {@linkplain Service} to keep
 * @since 2.1
 */
public final class BaseRuntimeRegistry<I extends ServiceId, S extends Service<I>> //
		extends BaseRegistry<I, S> //
		implements RuntimeRegistry<I, S> {

	private final Consumer<String> handler;

	public BaseRuntimeRegistry(Collection<S> services, Consumer<String> handler) {
		super(services);
		this.handler = handler;
	}

	public BaseRuntimeRegistry(Consumer<String> handler) {
		this(Collections.emptyList(), handler);
	}

	public BaseRuntimeRegistry() {
		this(System.err::println);
	}

	@Override
	public void register(S service) {
		Objects.requireNonNull(service);
		checkOverride(service);
		services.put(service.id(), service);
	}

	@Override
	public void unregister(S service) {
		checkAbsent(service);
		services.remove(service.id());
	}

	private void checkOverride(S service) {
		if (!hasService(service.id())) {
			return;
		}
		handler.accept(String.format(//
				BaseMessages.getString("RuntimeRegistry.register_override"), //$NON-NLS-1$
				service.id(), service(service.id()), service));
	}

	private void checkAbsent(S service) {
		if (hasService(service.id())) {
			return;
		}
		handler.accept(String.format(//
				BaseMessages.getString("RuntimeRegistry.unregister_absent"), //$NON-NLS-1$
				service));
	}

}
