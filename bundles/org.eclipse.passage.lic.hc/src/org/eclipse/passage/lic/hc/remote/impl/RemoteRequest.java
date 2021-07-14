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

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.HashesRegistry;
import org.eclipse.passage.lic.hc.remote.Connection;
import org.eclipse.passage.lic.hc.remote.Request;
import org.eclipse.passage.lic.hc.remote.RequestContext;
import org.eclipse.passage.lic.internal.hc.i18n.AccessMessages;
import org.eclipse.passage.lic.internal.net.HostPort;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess;

/**
 * <p>
 * Supplies all the data we are to tell a licensing server on a request.
 * </p>
 * <ul>
 * use
 * <li>{@code url()}</li> to compose server coordinates and all the request
 * parameters
 * <li>{@code config()} to gain a proper request headers configuring unit</li>
 * </ul>
 */
public abstract class RemoteRequest<C extends Connection> implements Request<C> {

	protected final LicensedProduct product;
	protected final FloatingLicenseAccess access;
	protected final String hash;

	public RemoteRequest(LicensedProduct product, FloatingLicenseAccess access, HashesRegistry hashes) {
		this.product = product;
		this.access = access;
		this.hash = hashes.get().services().iterator().next().id().toString();
	}

	@Override
	public final URL url() throws LicensingException {
		try {
			HostPort corrdinates = new FloatingServerCoordinates(access).get();
			return new URL("http", //$NON-NLS-1$
					corrdinates.host(), //
					Integer.parseInt(corrdinates.port()), //
					parameters().query());
		} catch (LicensingException //
				| NumberFormatException //
				| MalformedURLException e) {
			throw new LicensingException(AccessMessages.Request_failed_to_compose_url, e);
		}
	}

	@Override
	public final RequestContext context() {
		return new BaseRequestContext(product, hash);
	}

}
