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
package org.eclipse.passage.lbc.api.tests;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.passage.lbc.internal.api.BackendLicensingResponse;

public final class FakeLicensingResponse implements BackendLicensingResponse {

	private final ByteArrayOutputStream output;

	public FakeLicensingResponse() {
		this(new ByteArrayOutputStream());
	}

	public FakeLicensingResponse(ByteArrayOutputStream output) {
		this.output = output;
	}

	@Override
	public OutputStream outputStream() throws IOException {
		return output;
	}

	@Override
	public String toString() {
		return new String(output.toByteArray());
	}

}
