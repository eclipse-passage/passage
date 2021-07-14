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
package org.eclipse.passage.lic.internal.net.handle;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.passage.lic.api.PassageAction;
import org.eclipse.passage.lic.internal.net.LicensingAction;
import org.eclipse.passage.lic.internal.net.api.handle.Chore;
import org.eclipse.passage.lic.internal.net.api.handle.Chores;
import org.eclipse.passage.lic.internal.net.api.handle.NetRequest;
import org.eclipse.passage.lic.internal.net.api.handle.NetResponse;

public abstract class NetServices<R extends NetRequest> implements Chores<R> {

	private final Map<PassageAction, Function<R, Chore>> chores = new HashMap<>();

	protected NetServices() {
		defineChores(chores);
	}

	protected abstract void defineChores(Map<PassageAction, Function<R, Chore>> services);

	@Override
	public NetResponse workOut(R request) {
		LicensingAction action = action(request);
		return chores//
				.getOrDefault(//
						action.get().get(), //
						unknown -> new Failing(action))//
				.apply(request)//
				.getDone();
	}

	private LicensingAction action(R request) {
		return new LicensingAction(key -> new PassageAction.Of(String.valueOf(request.parameter(key))));
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
