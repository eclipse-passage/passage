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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.passage.lbc.internal.api.FloatingResponse;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.internal.hc.remote.Connection;
import org.eclipse.passage.lic.internal.hc.remote.QueryParameters;

final class ShortcutConnection implements Connection {

	private final Map<String, String> params = new HashMap<>();
	private byte[] request;
	private int code;
	private String message;
	private byte[] response;

	public ShortcutConnection(QueryParameters parameters) throws LicensingException {
		Arrays.stream(parameters.query().substring(1).split("&")) //$NON-NLS-1$
				.forEach(this::withParameter);
	}

	private void withParameter(String concatenated) {
		int separator = concatenated.indexOf('=');
		params.put(concatenated.substring(0, separator), concatenated.substring(separator + 1));
	}

	@Override
	public void beGet() throws LicensingException {
		// do nothing
	}

	@Override
	public void bePost() throws LicensingException {
		// do nothing
	}

	@Override
	public void withOutput(boolean with) {
		// do nothing
	}

	@Override
	public void withInput(boolean with) {
		// do nothing
	}

	@Override
	public void withTimeout(int timeout) {
		// do nothing
	}

	@Override
	public void withProperty(String name, String value) {
		// do nothing
	}

	@Override
	public void withPayload(byte[] content) throws LicensingException {
		request = content;
	}

	@Override
	public boolean successful() throws LicensingException {
		return code() == 200;
	}

	@Override
	public int code() throws LicensingException {
		return code;
	}

	@Override
	public String message() throws LicensingException {
		return message;
	}

	@Override
	public ContentType contentType() throws LicensingException {
		return new ContentType.Xml();
	}

	@Override
	public byte[] payload() throws LicensingException {
		return response;
	}

	String param(String name) {
		return params.get(name);
	}

	byte[] requestPayload() {
		return request;
	}

	void installResponse(FloatingResponse flo) throws IOException {
		if (flo.failed()) {
			code = flo.error().code();
			message = flo.error().message();
		} else {
			code = 200;
			message = "ok"; //$NON-NLS-1$
		}
		try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
			flo.write(stream);
			stream.flush();
			response = stream.toByteArray();
		}
	}
}
