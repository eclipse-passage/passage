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

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Optional;

import org.eclipse.passage.lic.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.hc.remote.Connection;
import org.eclipse.passage.lic.internal.api.LicensingException;

public final class NetConnection implements Connection {

	private final HttpURLConnection connection;

	NetConnection(HttpURLConnection connection) {
		this.connection = connection;
	}

	@Override
	public void beGet() throws LicensingException {
		invoke(() -> connection.setRequestMethod("GET")); //$NON-NLS-1$
		connection.setDoOutput(false);
		connection.setDoInput(true);
	}

	@Override
	public void bePost() throws LicensingException {
		invoke(() -> connection.setRequestMethod("POST")); //$NON-NLS-1$
		connection.setDoOutput(true);
		connection.setDoInput(true);
	}

	@Override
	public void withTimeout(int timeout) {
		connection.setConnectTimeout(timeout);
	}

	@Override
	public void withProperty(String name, String value) {
		connection.addRequestProperty(name, value);
	}

	@Override
	public void withPayload(byte[] payload) throws LicensingException {
		invoke(() -> write(payload));
	}

	@Override
	public boolean successful() throws LicensingException {
		return code() == HttpURLConnection.HTTP_OK;
	}

	@Override
	public int code() throws LicensingException {
		return invoke(connection::getResponseCode);
	}

	@Override
	public String message() throws LicensingException {
		return invoke(connection::getResponseMessage);
	}

	@Override
	public ContentType contentType() throws LicensingException {
		return new ContentType.Of(Optional.ofNullable(connection.getContentType()).orElse("none")); //$NON-NLS-1$
	}

	@Override
	public byte[] payload() throws LicensingException {
		return invoke(this::read);
	}

	private void write(byte[] payload) throws Exception {
		try (OutputStream output = connection.getOutputStream()) {
			output.write(payload);
		}
	}

	private byte[] read() throws Exception {
		byte[] content = new byte[connection.getContentLength()];
		try (InputStream source = connection.getInputStream()) {
			source.read(content); // read all and close the connection briefly
		}
		return content;
	}

	private void invoke(Failable method) throws LicensingException {
		try {
			method.invoke();
		} catch (Exception e) {
			throw new LicensingException(e);
		}
	}

	private <T> T invoke(FailableWithResult<T> action) throws LicensingException {
		try {
			return action.invoke();
		} catch (Exception e) {
			throw new LicensingException(e);
		}
	}

	private static interface Failable {

		void invoke() throws Exception;

	}

	private static interface FailableWithResult<T> {

		T invoke() throws Exception;

	}
}
