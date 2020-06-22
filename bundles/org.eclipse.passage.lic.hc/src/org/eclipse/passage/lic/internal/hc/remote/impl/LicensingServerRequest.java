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
import java.net.URL;

import org.eclipse.passage.lic.internal.hc.remote.Configuration;
import org.eclipse.passage.lic.internal.hc.remote.Request;

// read settings and know all the params and properties
final class LicensingServerRequest implements Request<HttpURLConnection> {

	@Override
	public URL url() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Configuration<HttpURLConnection> config() {
		throw new UnsupportedOperationException();
	}

}
