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

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import org.eclipse.passage.lic.internal.hc.remote.Configuration;

public abstract class BaseConfiguration implements Configuration<HttpURLConnection> {

	private final int timeout;
	private final Map<String, String> properties;

	protected BaseConfiguration(int timeout, Map<String, String> properties) {
		Objects.requireNonNull(properties, "BaseConfiguration::properties"); //$NON-NLS-1$
		this.timeout = timeout;
		this.properties = properties;
	}

	protected BaseConfiguration(Map<String, String> properties) {
		this(1000, properties);
	}

	protected BaseConfiguration() {
		this(Collections.emptyMap());
	}

	@Override
	public final HttpURLConnection apply(HttpURLConnection connection) throws Exception {
		installRequestDemands(connection);
		installRequestProperties(connection);
		paveRoadForData(connection);
		return connection;
	}

	private void installRequestDemands(HttpURLConnection connection) throws Exception {
		connection.setConnectTimeout(timeout);
	}

	protected abstract void paveRoadForData(HttpURLConnection connection) throws Exception;

	private void installRequestProperties(HttpURLConnection connection) {
		properties.forEach((k, v) -> connection.addRequestProperty(k, v));
	}

	public static final class Get extends BaseConfiguration {

		public Get(int timeout, Map<String, String> properties) {
			super(timeout, properties);
		}

		public Get(Map<String, String> properties) {
			super(properties);
		}

		public Get() {
			super();
		}

		@Override
		protected void paveRoadForData(HttpURLConnection connection) throws ProtocolException {
			connection.setRequestMethod("GET"); //$NON-NLS-1$
			connection.setDoOutput(true);
		}

	}

	public static final class Post extends BaseConfiguration {

		private final byte[] payload;

		public Post(int timeout, Map<String, String> properties, byte[] payload) {
			super(timeout, properties);
			this.payload = payload;
		}

		public Post(Map<String, String> properties, byte[] payload) {
			super(properties);
			this.payload = payload;
		}

		public Post(byte[] payload) {
			this.payload = payload;
		}

		@Override
		protected void paveRoadForData(HttpURLConnection connection) throws Exception {
			connection.setRequestMethod("PUT"); //$NON-NLS-1$
			connection.setDoOutput(false);
			connection.setDoInput(true);
			try (OutputStream output = connection.getOutputStream()) {
				output.write(payload);
			}
		}

	}

}
