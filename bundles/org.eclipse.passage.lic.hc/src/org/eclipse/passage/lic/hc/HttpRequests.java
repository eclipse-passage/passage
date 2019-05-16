/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.hc;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.utils.URIBuilder;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.internal.hc.HcMessages;
import org.eclipse.passage.lic.net.LicensingNet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class with static methods to work with http(s) requests
 *
 */
public final class HttpRequests {

	private static final String PROTOCOL_TYPE_ID = "http"; //$NON-NLS-1$
	private static final String USER = "user"; //$NON-NLS-1$
	private static final String HOST = "host"; //$NON-NLS-1$
	private static final String PORT = "port"; //$NON-NLS-1$

	private static final Logger LOG = LoggerFactory.getLogger(HttpRequests.class);

	public static Map<String, String> initRequestParams(String host, String port, String roleId, String productId,
			String productVersion) {
		Map<String, String> requestAttributes = new HashMap<>();
		requestAttributes.put(HOST, host);
		requestAttributes.put(PORT, port);

		// FIXME: rework
		requestAttributes.put(USER, "12345678"); //$NON-NLS-1$
		requestAttributes.put(LicensingNet.ROLE, roleId);
		requestAttributes.put(LicensingConfigurations.LICENSING_PRODUCT_IDENTIFIER, productId);
		requestAttributes.put(LicensingConfigurations.LICENSING_PRODUCT_VERSION, productVersion);
		return requestAttributes;
	}

	private static URIBuilder createRequestUriBuilder(Map<String, String> attributes) {
		String host = null;
		String port = null;
		Object hostAttr = attributes.get(HOST);
		if (hostAttr instanceof String) {
			host = (String) hostAttr;
		} else {
			String message = NLS.bind(HcMessages.HttpRequests_e_host_invalid, String.valueOf(hostAttr));
			LOG.info(message);
			return null;
		}
		Object portAttr = attributes.get(PORT);
		if (portAttr instanceof String) {
			port = (String) portAttr;
		} else {
			String message = NLS.bind(HcMessages.HttpRequests_e_port_invalid, String.valueOf(portAttr));
			LOG.info(message);
			return null;
		}

		String requestHead = String.format("%s://%s:%s", PROTOCOL_TYPE_ID, host, port); //$NON-NLS-1$
		URIBuilder builder;
		try {
			builder = new URIBuilder(requestHead);
			for (Entry<String, String> entry : attributes.entrySet()) {
				if (entry.getKey().equals(HOST) || entry.getKey().equals(PORT)) {
					continue;
				}
				builder.setParameter(entry.getKey(), entry.getValue());
			}
			return builder;

		} catch (URISyntaxException e) {
			LOG.info(e.getMessage());
		}
		return null;
	}

	public static URIBuilder createRequestURI(Map<String, String> attributes, String action) {
		attributes.put(LicensingNet.ACTION, action);
		attributes.put(LicensingProperties.LICENSING_CONTENT_TYPE, LicensingProperties.LICENSING_CONTENT_TYPE_XML);
		return createRequestUriBuilder(attributes);
	}

}
