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
import java.util.Collections;

import org.eclipse.passage.lic.internal.api.registry.Service;
import org.eclipse.passage.lic.internal.api.registry.ServiceId;

public final class ReadOnlyRegistry<I extends ServiceId, S extends Service<I>> extends BaseRegistry<I, S> {

	public ReadOnlyRegistry(Collection<S> services) {
		super(services);
	}

	public ReadOnlyRegistry(S service) {
		super(Collections.singleton(service));
	}

	public ReadOnlyRegistry() {
		super(Collections.emptyList());
	}

}
