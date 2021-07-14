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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.eclipse.passage.lbc.base.tests.TestData;
import org.eclipse.passage.lbc.internal.base.EagerFloatingState;
import org.eclipse.passage.lbc.internal.base.api.FloatingState;
import org.eclipse.passage.lbc.internal.base.api.RawRequest;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.hc.remote.Client;
import org.eclipse.passage.lic.hc.remote.Request;
import org.eclipse.passage.lic.hc.remote.ResponseHandler;
import org.eclipse.passage.lic.hc.remote.impl.ResultsTransfered;
import org.eclipse.passage.lic.internal.net.api.handle.NetResponse;

@SuppressWarnings("restriction")
final class ShortcutClient<T> implements Client<ShortcutConnection, T> {

	private final Remote remote;
	private final TestData data;

	ShortcutClient(Remote remote, TestData data) {
		this.remote = remote;
		this.data = data;
	}

	@Override
	public ServiceInvocationResult<T> request(Request<ShortcutConnection> request, ResponseHandler<T> handler) {
		try {
			ShortcutConnection connection = request.config().apply(new ShortcutConnection(request.parameters()));
			RawRequest raw = new RawRequestFromConnection(connection, state());
			NetResponse response = remote.invoke(raw);
			assertFalse(response.failed());
			connection.installResponse(response);
			return new BaseServiceInvocationResult<>(
					handler.read(new ResultsTransfered(connection), new TestRequestContext(data).get()));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
			return null; // unreachable
		}
	}

	private EagerFloatingState state() {
		return new EagerFloatingState(remote.state().grants(), new TestLicFolder().get());
	}

	static interface Remote {

		FloatingState state();

		NetResponse invoke(RawRequest raw) throws LicensingException;

	}

}
