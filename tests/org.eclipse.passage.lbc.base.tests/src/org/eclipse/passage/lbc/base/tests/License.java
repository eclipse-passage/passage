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
package org.eclipse.passage.lbc.base.tests;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.passage.lbc.internal.api.FloatingResponse;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.emf.EObjectFromBytes;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;

final class License {
	private final FloatingResponse response;

	License(FloatingResponse response) {
		this.response = response;
	}

	LicensePack get() throws IOException, LicensingException {
		try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
			response.write(stream);
			stream.flush();
			return new EObjectFromBytes<>(stream.toByteArray(), LicensePack.class).get();
		}

	}
}
