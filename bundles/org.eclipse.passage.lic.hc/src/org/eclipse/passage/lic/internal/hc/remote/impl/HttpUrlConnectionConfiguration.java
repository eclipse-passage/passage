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
import java.net.ProtocolException;
import java.util.Map;
import java.util.Objects;

import org.eclipse.passage.lic.internal.hc.remote.Configuration;

final class HttpUrlConnectionConfiguration implements Configuration<HttpURLConnection> {

	private final int timeout;
	private final Map<String, String> properties;

	public HttpUrlConnectionConfiguration(int timeout, Map<String, String> properties) {
		Objects.requireNonNull(properties, "HttpUrlConnectionConfiguration::proeprties"); //$NON-NLS-1$
		this.timeout = timeout;
		this.properties = properties;
	}

	@Override
	public HttpURLConnection apply(HttpURLConnection connection) throws ProtocolException {
		installRequestDemands(connection);
		installRequestProperties(connection);
		return connection;
	}

	private void installRequestDemands(HttpURLConnection connection) throws ProtocolException {
		connection.setRequestMethod("GET"); //$NON-NLS-1$
		connection.setConnectTimeout(timeout);
		connection.setDoOutput(true);
	}

	private void installRequestProperties(HttpURLConnection connection) {
		properties.forEach((k, v) -> connection.addRequestProperty(k, v));
	}

}
