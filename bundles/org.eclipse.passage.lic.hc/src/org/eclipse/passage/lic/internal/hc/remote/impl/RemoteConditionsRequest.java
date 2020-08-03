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
import java.net.URLEncoder;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;
import org.eclipse.passage.lic.internal.api.conditions.UserRole;
import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.internal.base.NamedData;
import org.eclipse.passage.lic.internal.base.ProductIdentifier;
import org.eclipse.passage.lic.internal.base.ProductVersion;
import org.eclipse.passage.lic.internal.base.conditions.mining.LicensingContentType;
import org.eclipse.passage.lic.internal.equinox.io.InstallationPath;
import org.eclipse.passage.lic.internal.hc.i18n.HcMessages;
import org.eclipse.passage.lic.internal.hc.remote.Configuration;
import org.eclipse.passage.lic.internal.hc.remote.Request;
import org.eclipse.passage.lic.internal.net.LicensingAction;
import org.eclipse.passage.lic.internal.net.LicensingRole;
import org.eclipse.passage.lic.internal.net.LicensingServerCoordinates;
import org.eclipse.passage.lic.internal.net.LicensingServerCoordinates.HostPort;

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
@SuppressWarnings("restriction")
public final class RemoteConditionsRequest implements Request<HttpURLConnection> {

	private final LicensedProduct product;
	private final Supplier<Path> settings;

	public RemoteConditionsRequest(LicensedProduct product, Supplier<Path> settings) {
		this.product = product;
		this.settings = settings;
	}

	public RemoteConditionsRequest(LicensedProduct product) {
		this(product, new InstallationPath());
	}

	@Override
	public URL url() throws LicensingException {
		try {
			HostPort corrdinates = new LicensingServerCoordinates(settings).get();
			return new URL("http", //$NON-NLS-1$
					corrdinates.host(), //
					Integer.parseInt(corrdinates.port()), //
					query());
		} catch (LicensingException //
				| NumberFormatException //
				| MalformedURLException //
				| UnsupportedEncodingException e) {
			throw new LicensingException(HcMessages.RemoteConditionsRequest_failed_to_compose_url, e);
		}
	}

	private String query() throws UnsupportedEncodingException {
		StringBuilder params = new StringBuilder();
		Arrays.stream(//
				new NamedData[] { //
						new ProductIdentifier(encode(product.identifier())), //
						new ProductVersion(encode(product.version())), //
						new LicensingAction(new ConditionAction.Aquire()), //
						new LicensingRole(new UserRole.Admin()), //
						new LicensingContentType(new ContentType.Xml()), //
						new TemporaryUser("12345678") }) //$NON-NLS-1$ FIXME: for development: #564815
				.map(NamedData.Writable<String>::new)//
				.forEach(writable -> writable.write(params, "=", "&")); //$NON-NLS-1$ //$NON-NLS-2$
		return '?' + params.toString();

	}

	private String encode(String value) throws UnsupportedEncodingException {
		return URLEncoder.encode(value, "UTF-8"); //$NON-NLS-1$
	}

	@Override
	public Configuration<HttpURLConnection> config() {
		return new HttpUrlConnectionConfiguration(1000, new HashMap<>());
	}

}
