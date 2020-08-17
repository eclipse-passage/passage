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
package org.eclipse.passage.lbc.internal.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lbc.internal.api.BackendRequestDispatcher;
import org.eclipse.passage.lbc.internal.api.Chain;
import org.eclipse.passage.lbc.internal.api.LicensingRequest;
import org.eclipse.passage.lbc.internal.api.LicensingResponse;
import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;

/**
 * @since 1.0
 */

public class BaseRequestDispatcher implements BackendRequestDispatcher {

	private final Map<Supplier<Optional<ConditionAction>>, Chain> toolChains = new HashMap<Supplier<Optional<ConditionAction>>, Chain>();

	@Override
	public void dispatch(LicensingRequest request, LicensingResponse result) throws IOException {
		if (toolChains.containsKey(request.action())) {
			find(request.action()).execute(request, result);
		}
	}

	@Override
	public void addChain(Supplier<Optional<ConditionAction>> action, Chain chain) {
		toolChains.put(action, chain);
	}

	private Chain find(Supplier<Optional<ConditionAction>> action) {
		return toolChains.get(action);
	}
}
