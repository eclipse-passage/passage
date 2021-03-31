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
package org.eclipse.passage.lic.internal.hc.tests.remote;

import java.io.IOException;

import org.eclipse.passage.lbc.internal.base.api.FloatingState;
import org.eclipse.passage.lbc.internal.base.api.RawRequest;

@SuppressWarnings("restriction")
final class RawRequestFromConnection implements RawRequest {

	private final ShortcutConnection connection;
	private final FloatingState state;

	RawRequestFromConnection(ShortcutConnection connection, FloatingState state) {
		this.connection = connection;
		this.state = state;
	}

	@Override
	public String parameter(String name) {
		return connection.param(name);
	}

	@Override
	public byte[] content() throws IOException {
		return connection.requestPayload();
	}

	@Override
	public FloatingState state() {
		return state;
	}

}
