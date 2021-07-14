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

import java.util.Map;
import java.util.function.Function;

import org.eclipse.passage.lbc.internal.base.api.RawRequest;
import org.eclipse.passage.lic.api.PassageAction;
import org.eclipse.passage.lic.internal.net.api.handle.Chore;
import org.eclipse.passage.lic.internal.net.handle.NetServices;

final class FloatingCycle extends NetServices<RawRequest> {

	@Override
	protected void defineChores(Map<PassageAction, Function<RawRequest, Chore>> chores) {
		chores.put(new PassageAction.Mine(), Mine::new);
		chores.put(new PassageAction.Acquire(), Acquire::new);
		chores.put(new PassageAction.Release(), Release::new);
	}

}
