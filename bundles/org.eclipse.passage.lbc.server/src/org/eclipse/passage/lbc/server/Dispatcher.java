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
package org.eclipse.passage.lbc.server;

import java.util.Map;
import java.util.function.Function;

import org.eclipse.passage.lbc.internal.api.BackendRequestDispatcher;
import org.eclipse.passage.lbc.internal.base.BaseRequestDispatcher;
import org.eclipse.passage.lic.internal.base.BaseNamedData;

@SuppressWarnings("restriction")
public final class Dispatcher extends BaseNamedData<BackendRequestDispatcher> {

	protected Dispatcher(Function<String, BackendRequestDispatcher> retrieve) {
		super(retrieve);
	}

	public Dispatcher(Map<String, Object> arguments) {
		super(key -> BackendRequestDispatcher.class.isInstance(arguments.get(key))
				? (BackendRequestDispatcher) arguments.get(key)
				: new BaseRequestDispatcher());
	}

	@Override
	public String key() {
		return "dispatcher"; //$NON-NLS-1$
	}

}
