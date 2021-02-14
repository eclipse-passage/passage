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
package org.eclipse.passage.lbc.internal.base;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.passage.lbc.internal.base.api.RawRequest;
import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;
import org.eclipse.passage.lic.internal.net.LicensingAction;
import org.eclipse.passage.lic.internal.net.handle.Chore;
import org.eclipse.passage.lic.internal.net.handle.Chores;
import org.eclipse.passage.lic.internal.net.handle.NetResponse;

final class FloatingCycle implements Chores<RawRequest> {

	private final Map<ConditionAction, Function<RawRequest, Chore>> chores = new HashMap<>();

	FloatingCycle() {
		chores.put(new ConditionAction.Mine(), Mine::new);
		chores.put(new ConditionAction.Acquire(), Acquire::new);
		chores.put(new ConditionAction.Release(), Release::new);
	}

	@Override
	public NetResponse workOut(RawRequest request) {
		LicensingAction action = action(request);
		return chores//
				.getOrDefault(//
						action.get().get(), //
						unknown -> new Failing(action))//
				.apply(request)//
				.getDone();
	}

	private LicensingAction action(RawRequest request) {
		return new LicensingAction(key -> new ConditionAction.Of(String.valueOf(request.parameter(key))));
	}

	private final class Failing implements Chore {
		private final LicensingAction actual;

		Failing(LicensingAction actual) {
			this.actual = actual;
		}

		@Override
		public NetResponse getDone() {
			return new Failure.BadRequestUnknownAction(actual.get().get().name());
		}

	}

}
