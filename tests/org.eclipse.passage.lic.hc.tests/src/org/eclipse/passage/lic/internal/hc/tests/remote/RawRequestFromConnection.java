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
package org.eclipse.passage.lic.internal.hc.tests.remote;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.passage.lbc.internal.api.FloatingState;
import org.eclipse.passage.lbc.internal.api.RawRequest;
import org.eclipse.passage.lbc.internal.base.EagerFloatingState;

final class RawRequestFromConnection implements RawRequest {

	private final ShortcutConnection connection;
	private final Supplier<Path> source;

	RawRequestFromConnection(ShortcutConnection connection, Supplier<Path> source) {
		this.connection = connection;
		this.source = source;
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
		return new EagerFloatingState(source);
	}

}
