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

import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.base.NamedData;
import org.eclipse.passage.lic.internal.hc.i18n.AccessMessages;

public abstract class RequestParameters {

	protected RequestParameters() {
	}

	public final String query() throws LicensingException {
		StringBuilder params = new StringBuilder();
		Arrays.stream(parameters())//
				.map(NamedData.Writable<String>::new)//
				.forEach(writable -> writable.write(params, "=", "&")); //$NON-NLS-1$ //$NON-NLS-2$
		return '?' + params.toString();

	}

	@SuppressWarnings("rawtypes")
	protected abstract NamedData[] parameters() throws LicensingException;

	protected String encode(String value) throws LicensingException {
		try {
			return URLEncoder.encode(value, "UTF-8"); //$NON-NLS-1$
		} catch (UnsupportedEncodingException e) {
			throw new LicensingException(AccessMessages.RequestParameters_encoding_failed, e);
		}
	}
}
