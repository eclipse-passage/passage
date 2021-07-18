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

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.hc.remote.Configuration;
import org.eclipse.passage.lic.hc.remote.Connection;

/**
 * 
 * @since 1.1
 */
public abstract class BaseConfiguration<C extends Connection> implements Configuration<C> {

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
	public final C apply(C connection) throws Exception {
		installRequestDemands(connection);
		installRequestProperties(connection);
		paveRoadForData(connection);
		return connection;
	}

	private void installRequestDemands(C connection) throws Exception {
		connection.withTimeout(timeout);
	}

	protected abstract void paveRoadForData(C connection) throws Exception;

	private void installRequestProperties(C connection) {
		properties.forEach((k, v) -> connection.withProperty(k, v));
	}

	public static final class Get<C extends Connection> extends BaseConfiguration<C> {

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
		protected void paveRoadForData(C connection) throws LicensingException {
			connection.beGet();
			connection.withProperty("Accept", new ContentType.Xml().contentType()); //$NON-NLS-1$
		}

	}

	public static final class Post<C extends Connection> extends BaseConfiguration<C> {

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
		protected void paveRoadForData(C connection) throws Exception {
			connection.bePost();
			connection.withProperty("Content-Type", new ContentType.Xml().contentType()); //$NON-NLS-1$
			connection.withPayload(payload);
		}

	}

}
