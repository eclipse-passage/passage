/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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

import org.eclipse.passage.lic.api.registry.Registry;
import org.eclipse.passage.lic.api.registry.Service;
import org.eclipse.passage.lic.api.registry.ServiceId;

/**
 * Begets new instance of a service for each {@code service} request.
 * 
 * @since 2.1
 */
public abstract class ProducingRegistry<I extends ServiceId, S extends Service<I>> implements Registry<I, S> {

	@Override
	public boolean hasService(I id) {
		return true;
	}

	@Override
	public S service(I id) {
		return brandNew(id);
	}

	@Override
	public Collection<S> services() {
		return Collections.emptyList();
	}

	protected abstract S brandNew(I id);

}
