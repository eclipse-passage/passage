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
import java.net.URLEncoder;
import java.util.Arrays;

import org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;
import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.internal.base.NamedData;
import org.eclipse.passage.lic.internal.base.ProductIdentifier;
import org.eclipse.passage.lic.internal.base.ProductVersion;
import org.eclipse.passage.lic.internal.base.conditions.mining.LicensingContentType;
import org.eclipse.passage.lic.internal.net.LicensingAction;

final class RequestParameters {

	private final LicensedProduct product;
	private final FloatingLicenseAccess access;

	RequestParameters(LicensedProduct product, FloatingLicenseAccess access) {
		this.product = product;
		this.access = access;
	}

	String query() throws UnsupportedEncodingException {
		StringBuilder params = new StringBuilder();
		Arrays.stream(parameters())//
				.map(NamedData.Writable<String>::new)//
				.forEach(writable -> writable.write(params, "=", "&")); //$NON-NLS-1$ //$NON-NLS-2$
		return '?' + params.toString();

	}

	@SuppressWarnings("rawtypes")
	private NamedData[] parameters() throws UnsupportedEncodingException {
		return new NamedData[] { //
				new ProductIdentifier(encode(product.identifier())), //
				new ProductVersion(encode(product.version())), //
				new LicensingAction(new ConditionAction.Acquire()), //
				new LicensingContentType(new ContentType.Xml()), //
				new LicenseUser(access.getUser()), //
				new ServerAuthenticationType(access.getServer()), //
				new ServerAuthenticationExpression(access.getServer()) };
	}

	private String encode(String value) throws UnsupportedEncodingException {
		return URLEncoder.encode(value, "UTF-8"); //$NON-NLS-1$
	}

}
