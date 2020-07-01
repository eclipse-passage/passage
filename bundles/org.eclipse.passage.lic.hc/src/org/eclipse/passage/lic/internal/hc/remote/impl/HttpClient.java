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

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Collection;

import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionMiningException;
import org.eclipse.passage.lic.internal.hc.i18n.HcMessages;
import org.eclipse.passage.lic.internal.hc.remote.Client;
import org.eclipse.passage.lic.internal.hc.remote.Request;
import org.eclipse.passage.lic.internal.hc.remote.ResponseHandler;

@SuppressWarnings("restriction")
public final class HttpClient implements Client<HttpURLConnection> {

	@Override
	public Collection<Condition> remoteConditions(Request<HttpURLConnection> request, ResponseHandler miner)
			throws ConditionMiningException {
		try {
			return netConditions(connection(request), miner);
		} catch (Exception e) {
			throw new ConditionMiningException(HcMessages.HttpClient_final_error_message, e);
		}
	}

	private HttpURLConnection connection(Request<HttpURLConnection> request) throws Exception {
		return request.config().apply((HttpURLConnection) request.url().openConnection());
	}

	private Collection<Condition> netConditions(HttpURLConnection connection, ResponseHandler miner) throws Exception {
		// actual connection is happening on the first 'get' (get response code)
		if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
			connection.getInputStream().close(); // close the connection
			throw new RuntimeException(String.format(HcMessages.HttpClient_not_ok_response, //
					connection.getResponseCode(), //
					connection.getResponseMessage()));
		}
		return read(connection, miner);
	}

	private Collection<Condition> read(HttpURLConnection connection, ResponseHandler miner) throws Exception {
		byte[] content = new byte[connection.getContentLength()];
		try (InputStream source = connection.getInputStream()) {
			source.read(content); // read all and close the connection briefly
		}
		return miner.read(content, connection.getHeaderField("Content-Type")); //$NON-NLS-1$
	}

}
