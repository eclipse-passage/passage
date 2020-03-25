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
package org.eclipse.passage.lic.internal.base.registry;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.api.registry.Service;
import org.eclipse.passage.lic.internal.api.registry.ServiceId;
import org.eclipse.passage.lic.internal.base.i18n.BaseMessages;

/**
 * <p>
 * {@linkplain Registry} implementation that delegates actual service owning to
 * the incoming {@linkplain Supplier}
 * </p>
 * 
 * @param <S> type of {@linkplain Service} to spread
 */
@SuppressWarnings("restriction")
public final class BaseRegistry<I extends ServiceId, S extends Service<I>> implements Registry<I, S> {

	private final Supplier<Collection<S>> services;

	/**
	 * Primary constructor
	 * 
	 * @param init    {@linkplain Map} implementation to be used as service storing
	 *                facility
	 * @param handler error handler
	 */
	public BaseRegistry(Supplier<Collection<S>> services) {
		this.services = services;
	}

	@Override
	public boolean hasService(I id) {
		return services.get().stream().anyMatch(s -> id.equals(s.id()));
	}

	/**
	 * <p>
	 * Get the previously registered service by it's {@code id}. It's mandatory to
	 * either be sure the service has been registered or to check
	 * {@linkplain #hasService(ServiceId)} first.
	 * </p>
	 * 
	 * @throws IllegalStateException if not yet registered service is requested
	 */
	@Override
	public S service(I id) {
		return services.get().stream()//
				.filter(s -> id.equals(s.id()))//
				.findFirst().orElseThrow(() -> new IllegalStateException(String.format(//
						BaseMessages.getString("Registry.retrieve_absent_exception"), //$NON-NLS-1$
						id)));
	}

	@Override
	public Collection<S> services() {
		return services.get();
	}

}
