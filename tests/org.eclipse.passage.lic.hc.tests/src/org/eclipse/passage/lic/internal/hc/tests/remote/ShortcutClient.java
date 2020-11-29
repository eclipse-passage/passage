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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.passage.lbc.internal.api.FloatingResponse;
import org.eclipse.passage.lbc.internal.api.RawRequest;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.hc.remote.Client;
import org.eclipse.passage.lic.internal.hc.remote.Request;
import org.eclipse.passage.lic.internal.hc.remote.ResponseHandler;
import org.eclipse.passage.lic.internal.hc.remote.impl.ResultsTransfered;

final class ShortcutClient<T> implements Client<ShortcutConnection, T> {

	private final Supplier<Path> source;
	private final Remote remote;

	ShortcutClient(Supplier<Path> source, Remote remote) {
		this.source = source;
		this.remote = remote;
	}

	@Override
	public ServiceInvocationResult<T> request(Request<ShortcutConnection> request, ResponseHandler<T> handler) {
		try {
			ShortcutConnection connection = request.config().apply(new ShortcutConnection(request.parameters()));
			RawRequest raw = new RawRequestFromConnection(connection, source);
			FloatingResponse response = remote.invoke(raw);
			assertFalse(response.failed());
			connection.installResponse(response);
			return new BaseServiceInvocationResult<>(handler.read(new ResultsTransfered(connection)));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
			return null; // unreachable
		}
	}

	static interface Remote {

		FloatingResponse invoke(RawRequest raw) throws LicensingException;

	}

}
