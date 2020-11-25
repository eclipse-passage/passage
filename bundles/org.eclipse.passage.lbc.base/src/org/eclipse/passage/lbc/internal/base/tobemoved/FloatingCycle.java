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
package org.eclipse.passage.lbc.internal.base.tobemoved;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.passage.lbc.internal.api.tobemoved.Chore;
import org.eclipse.passage.lbc.internal.api.tobemoved.Chores;
import org.eclipse.passage.lbc.internal.api.tobemoved.FloatingResponse;
import org.eclipse.passage.lbc.internal.api.tobemoved.RawRequest;
import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;
import org.eclipse.passage.lic.internal.net.LicensingAction;

final class FloatingCycle implements Chores {

	private final Map<ConditionAction, Function<RawRequest, Chore>> chores = new HashMap<>();

	FloatingCycle() {
		chores.put(new ConditionAction.Mine(), Mine::new);
		chores.put(new ConditionAction.Acquire(), Acquire::new);
		chores.put(new ConditionAction.Release(), Release::new);
	}

	@Override
	public FloatingResponse workOut(RawRequest request) {
		LicensingAction action = action(request);
		return chores//
				.getOrDefault(//
						action, //
						any -> new Failing(action))//
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
		public FloatingResponse getDone() {
			return new Failure.BadRequestUnknownAction(actual.get().get().name());
		}

	}

}
