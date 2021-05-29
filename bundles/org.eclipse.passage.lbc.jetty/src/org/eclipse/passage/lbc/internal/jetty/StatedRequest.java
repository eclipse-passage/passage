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
package org.eclipse.passage.lbc.internal.jetty;

import java.io.IOException;

import org.eclipse.passage.lbc.internal.base.api.FloatingState;
import org.eclipse.passage.lbc.internal.base.api.RawRequest;
import org.eclipse.passage.lic.internal.net.api.handle.NetRequest;

@SuppressWarnings("restriction")
final class StatedRequest implements RawRequest {

	private final NetRequest delegate;
	private final FloatingState state;

	StatedRequest(NetRequest delegate, FloatingState state) {
		this.delegate = delegate;
		this.state = state;
	}

	@Override
	public String parameter(String name) {
		return delegate.parameter(name);
	}

	@Override
	public byte[] content() throws IOException {
		return delegate.content();
	}

	@Override
	public FloatingState state() {
		return state;
	}

}
