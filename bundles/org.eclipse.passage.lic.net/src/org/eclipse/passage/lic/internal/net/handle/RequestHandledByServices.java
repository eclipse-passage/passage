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
package org.eclipse.passage.lic.internal.net.handle;

import java.util.Objects;

import org.eclipse.passage.lic.internal.net.api.handle.Chores;
import org.eclipse.passage.lic.internal.net.api.handle.NetRequest;
import org.eclipse.passage.lic.internal.net.api.handle.NetRequestHandled;
import org.eclipse.passage.lic.internal.net.api.handle.NetResponse;

public abstract class RequestHandledByServices<R extends NetRequest> implements NetRequestHandled {

	private final R request;
	private final Chores<R> chores;

	protected RequestHandledByServices(R request, Chores<R> chores) {
		Objects.requireNonNull(request, "BaseFlotingRequestHandled:request");//$NON-NLS-1$
		Objects.requireNonNull(chores, "BaseFlotingRequestHandled:chores");//$NON-NLS-1$
		this.request = request;
		this.chores = chores;
	}

	@Override
	public final NetResponse get() {
		return chores.workOut(request);
	}

}
