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

import java.util.Objects;

import org.eclipse.passage.lbc.internal.base.api.RawRequest;
import org.eclipse.passage.lic.internal.net.handle.Chores;
import org.eclipse.passage.lic.internal.net.handle.NetRequestHandled;
import org.eclipse.passage.lic.internal.net.handle.NetResponse;

public final class BaseFlotingRequestHandled implements NetRequestHandled {

	private final RawRequest request;
	private final Chores<RawRequest> chores;

	public BaseFlotingRequestHandled(RawRequest request, Chores<RawRequest> chores) {
		Objects.requireNonNull(request, "BaseFlotingRequestHandled:request");//$NON-NLS-1$
		Objects.requireNonNull(chores, "BaseFlotingRequestHandled:chores");//$NON-NLS-1$
		this.request = request;
		this.chores = chores;
	}

	public BaseFlotingRequestHandled(RawRequest request) {
		this(request, new FloatingCycle());
	}

	@Override
	public NetResponse get() {
		return chores.workOut(request);
	}

}
