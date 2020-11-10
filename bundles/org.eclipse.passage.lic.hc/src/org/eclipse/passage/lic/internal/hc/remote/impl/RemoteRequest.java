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
public abstract class RemoteRequest implements Request<HttpURLConnection> {

	protected final LicensedProduct product;
	protected final FloatingLicenseAccess access;

	public RemoteRequest(LicensedProduct product, FloatingLicenseAccess access) {
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
					parameters().query());
		} catch (LicensingException //
				| NumberFormatException //
				| MalformedURLException e) {
			throw new LicensingException(HcMessages.RemoteConditionsRequest_failed_to_compose_url, e);
		}
	}

	protected abstract RequestParameters parameters();

	@Override
	public Configuration<HttpURLConnection> config() {
		return new HttpUrlConnectionConfiguration(1000, new HashMap<>());
	}

}
