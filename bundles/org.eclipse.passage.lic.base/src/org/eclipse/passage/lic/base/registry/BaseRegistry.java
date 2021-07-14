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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.registry.Registry;
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
public abstract class BaseRegistry<I extends ServiceId, S extends Service<I>> implements Registry<I, S> {

	protected final Map<I, S> services;

	/**
	 * Convenience constructor
	 * 
	 * @param service {@linkplain Collection} list of actual services to be owned by
	 *                the registry
	 */
	public BaseRegistry(Collection<S> service) {
		this.services = service.stream().collect(Collectors.toMap(Service::id, Function.identity()));
	}

	@Override
	public final boolean hasService(I id) {
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
	 */
	@Override
	public final S service(I id) {
		if (!hasService(id)) {
			throw new IllegalStateException(String.format(//
					BaseMessages.getString("Registry.retrieve_absent_exception"), //$NON-NLS-1$
					id));
		}
		return services.get(id);
	}

	@Override
	public final Collection<S> services() {
		return new ArrayList<>(services.values());
	}

}
