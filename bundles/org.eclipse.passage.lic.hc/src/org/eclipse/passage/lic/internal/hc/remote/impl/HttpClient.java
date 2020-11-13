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
package org.eclipse.passage.lic.internal.hc.remote.impl;

import java.net.HttpURLConnection;
import java.util.Collections;

import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.internal.base.diagnostic.code.ServiceFailedOnInfrastructureDenial;
import org.eclipse.passage.lic.internal.hc.i18n.AccessMessages;
import org.eclipse.passage.lic.internal.hc.remote.Client;
import org.eclipse.passage.lic.internal.hc.remote.Request;
import org.eclipse.passage.lic.internal.hc.remote.ResponseHandler;

public final class HttpClient<T> implements Client<HttpURLConnection, T> {

	@Override
	public ServiceInvocationResult<T> request(Request<HttpURLConnection> request, ResponseHandler<T> handler) {
		try {
			return netResults(connection(request), handler);
		} catch (Exception e) {
			return new BaseServiceInvocationResult<>(//
					new BaseDiagnostic(//
							Collections.emptyList(), //
							Collections.singletonList(//
									new Trouble(new ServiceFailedOnInfrastructureDenial(),
											AccessMessages.HttpClient_failure, e))));
		}
	}

	private HttpURLConnection connection(Request<HttpURLConnection> request) throws Exception {
		return request.config().apply((HttpURLConnection) request.url().openConnection());
	}

	private ServiceInvocationResult<T> netResults(HttpURLConnection connection, ResponseHandler<T> handler)
			throws Exception {
		// actual connection is happening on this construction of the results
		ResultsTransfered results = new ResultsTransfered(connection);
		if (!results.successful()) {
			return new BaseServiceInvocationResult<>(results.diagnose());
		}
		return new BaseServiceInvocationResult<>(handler.read(results));
	}

}
