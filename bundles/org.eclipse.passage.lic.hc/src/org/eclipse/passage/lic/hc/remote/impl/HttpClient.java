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
package org.eclipse.passage.lic.hc.remote.impl;

import java.net.HttpURLConnection;
import java.util.Collections;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.base.diagnostic.code.ServiceFailedOnInfrastructureDenial;
import org.eclipse.passage.lic.hc.remote.Client;
import org.eclipse.passage.lic.hc.remote.Request;
import org.eclipse.passage.lic.hc.remote.RequestContext;
import org.eclipse.passage.lic.hc.remote.ResponseHandler;
import org.eclipse.passage.lic.internal.hc.i18n.AccessMessages;

/**
 * 
 * @since 1.1
 */
public final class HttpClient<T> implements Client<NetConnection, T> {

	@Override
	public ServiceInvocationResult<T> request(Request<NetConnection> request, ResponseHandler<T> handler) {
		try {
			return netResults(connection(request), handler, request.context());
		} catch (Exception e) {
			return new BaseServiceInvocationResult<>(//
					new BaseDiagnostic(//
							Collections.emptyList(), //
							Collections.singletonList(//
									new Trouble(new ServiceFailedOnInfrastructureDenial(),
											AccessMessages.HttpClient_failure, e))));
		}
	}

	private NetConnection connection(Request<NetConnection> request) throws Exception {
		return request.config().apply(new NetConnection((HttpURLConnection) request.url().openConnection()));
	}

	private ServiceInvocationResult<T> netResults(NetConnection connection, ResponseHandler<T> handler,
			RequestContext context) throws Exception {
		// actual connection is happening on this construction of the results
		ResultsTransfered results = new ResultsTransfered(connection);
		if (!results.successful()) {
			return new BaseServiceInvocationResult<>(results.diagnose());
		}
		return new BaseServiceInvocationResult<>(handler.read(results, context));
	}

}
