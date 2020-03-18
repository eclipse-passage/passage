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
package org.eclipse.passage.lic.base.internal.registry;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import org.eclipse.passage.lic.api.internal.registry.Registry;
import org.eclipse.passage.lic.api.internal.registry.RuntimeRegistry;
import org.eclipse.passage.lic.api.internal.registry.Service;
import org.eclipse.passage.lic.api.internal.registry.ServiceId;
import org.eclipse.passage.lic.internal.base.i18n.BaseMessages;

/**
 * 
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
 */
@SuppressWarnings("restriction")
public final class BaseRuntimeRegistry<S extends Service<?>> implements RuntimeRegistry<S> {

	private final Map<ServiceId, S> services;
	private final Consumer<String> handler;

	/**
	 * Primary constructor
	 * 
	 * @param init    {@linkplain Map} implementation to be used as service storing
	 *                facility
	 * @param handler error handler
	 * @since 0.6
	 */
	public BaseRuntimeRegistry(Map<ServiceId, S> init, Consumer<String> handler) {
		this.services = init;
		this.handler = handler;
	}

	/**
	 * Convenience constructor, uses {@linkplain HashMap} as a storage and prints
	 * errors into {@linkplain Stsrem.err} stream
	 * 
	 * @since 0.6
	 */
	public BaseRuntimeRegistry() {
		this(new HashMap<>(), System.err::println);
	}

	public BaseRuntimeRegistry(Map<ServiceId, S> init) {
		this(init, System.err::println);
	}

	public BaseRuntimeRegistry(Consumer<String> handler) {
		this(new HashMap<>(), handler);
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

	@Override
	public boolean hasService(ServiceId id) {
		return services.containsKey(id);
	}

	/**
	 * <p>
	 * Get the previously registered service by it's {@code id}. It's mandatory to
	 * either be sure the service has been registered or to check
	 * {@linkplain #hasService(ServiceId)} first.
	 * </p>
	 * 
	 * @throws IllegalStateException if not yet registered service is requested
	 * @since 0.6
	 */
	@Override
	public S service(ServiceId id) {
		if (!hasService(id)) {
			throw new IllegalStateException(String.format(//
					BaseMessages.getString("RuntimeRegistry.retrieve_absent_exception"), // //$NON-NLS-1$
					id));
		}
		return services.get(id);
	}

	@Override
	public Collection<S> services() {
		return services.values();
	}

	private void checkOverride(S service) {
		if (!hasService(service.id())) {
			return;
		}
		handler.accept(String.format(//
				BaseMessages.getString("RuntimeRegistry.register_override"), // //$NON-NLS-1$
				service.id(), service(service.id()), service));
	}

	private void checkAbsent(S service) {
		if (hasService(service.id())) {
			return;
		}
		handler.accept(String.format(//
				BaseMessages.getString("RuntimeRegistry.unregister_absent"), // //$NON-NLS-1$
				service));
	}

}
