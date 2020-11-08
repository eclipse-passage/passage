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

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.hc.i18n.HcMessages;
import org.eclipse.passage.lic.internal.hc.remote.Configuration;
import org.eclipse.passage.lic.internal.hc.remote.Request;
import org.eclipse.passage.lic.internal.net.HostPort;

/**
 * <p>
 * Supplies all the data we are to tell a licensing server on mining request.
 * </p>
 * <ul>
 * use
 * <li>{@code url()}</li> to compose server coordinates and all the request
 * parameters
 * <li>{@code config()} to gain a proper request headers configuring unit</li>
 * </ul>
 */
public final class RemoteConditionsRequest implements Request<HttpURLConnection> {

	private final LicensedProduct product;
	private final FloatingLicenseAccess access;

	public RemoteConditionsRequest(LicensedProduct product, FloatingLicenseAccess access) {
		this.product = product;
		this.access = access;
	}

	@Override
	public URL url() throws LicensingException {
		try {
			HostPort corrdinates = new FloatingServerCoordinates(access).get();
			return new URL("http", //$NON-NLS-1$
					corrdinates.host(), //
					Integer.parseInt(corrdinates.port()), //
					new RequestParameters(product, access).query());
		} catch (LicensingException //
				| NumberFormatException //
				| MalformedURLException //
				| UnsupportedEncodingException e) {
			throw new LicensingException(HcMessages.RemoteConditionsRequest_failed_to_compose_url, e);
		}
	}

	@Override
	public Configuration<HttpURLConnection> config() {
		return new HttpUrlConnectionConfiguration(1000, new HashMap<>());
	}

}
