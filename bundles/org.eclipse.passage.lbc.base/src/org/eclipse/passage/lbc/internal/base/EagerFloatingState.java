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

import org.eclipse.passage.lbc.internal.api.FloatingState;
import org.eclipse.passage.lbc.internal.api.Grants;
import org.eclipse.passage.lbc.internal.base.acquire.AcquiredGrants;

public final class EagerFloatingState implements FloatingState {

	private final Grants grants = new AcquiredGrants();

	@Override
	public Grants grants() {
		return grants;
	}

}
