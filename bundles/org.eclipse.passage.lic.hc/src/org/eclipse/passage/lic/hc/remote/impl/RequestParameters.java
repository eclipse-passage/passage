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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.PassageAction;
import org.eclipse.passage.lic.base.NamedData;
import org.eclipse.passage.lic.base.ProductIdentifier;
import org.eclipse.passage.lic.base.ProductVersion;
import org.eclipse.passage.lic.hc.remote.QueryParameters;
import org.eclipse.passage.lic.internal.hc.i18n.AccessMessages;
import org.eclipse.passage.lic.internal.net.EncodingAlgorithm;
import org.eclipse.passage.lic.internal.net.LicenseUser;
import org.eclipse.passage.lic.internal.net.LicensingAction;
import org.eclipse.passage.lic.internal.net.ServerAuthenticationExpression;
import org.eclipse.passage.lic.internal.net.ServerAuthenticationType;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess;

/**
 * 
 * @since 1.1
 */
public abstract class RequestParameters implements QueryParameters {

	private final LicensedProduct product;
	private final FloatingLicenseAccess access;
	private final String hash;

	protected RequestParameters(LicensedProduct product, FloatingLicenseAccess access, String hash) {
		this.product = product;
		this.access = access;
		this.hash = hash;
	}

	@Override
	public final String query() throws LicensingException {
		StringBuilder params = new StringBuilder();
		parameters().stream()//
				.map(NamedData.Writable<String>::new)//
				.forEach(writable -> writable.write(params, "=", "&")); //$NON-NLS-1$ //$NON-NLS-2$
		return '?' + params.toString();
	}

	@SuppressWarnings("rawtypes")
	private List<NamedData> parameters() throws LicensingException {
		return Arrays.asList(//
				generalParameters(), //
				actionParameters()).stream() //
				.flatMap(List::stream)//
				.collect(Collectors.toList());
	}

	@SuppressWarnings("rawtypes")
	private List<NamedData> generalParameters() throws LicensingException {
		return Arrays.asList( //
				new ProductIdentifier(encode(product.identifier())), //
				new ProductVersion(encode(product.version())), //
				new LicensingAction(action()), //
				new LicenseUser(access.getUser()), //
				new LicenseUser(access.getUser()), //
				new EncodingAlgorithm(hash), //
				new ServerAuthenticationType(access.getServer().getAuthentication().getType()), //
				new ServerAuthenticationExpression(encode(access.getServer().getAuthentication().getExpression())));

	}

	protected String encode(String value) throws LicensingException {
		try {
			return URLEncoder.encode(value, "UTF-8"); //$NON-NLS-1$
		} catch (UnsupportedEncodingException e) {
			throw new LicensingException(AccessMessages.RequestParameters_encoding_failed, e);
		}
	}

	protected abstract PassageAction action() throws LicensingException;

	@SuppressWarnings("rawtypes")
	protected abstract List<NamedData> actionParameters() throws LicensingException;

}
