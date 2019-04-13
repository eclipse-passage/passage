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
import java.util.logging.Logger;

import org.apache.http.client.utils.URIBuilder;
import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.net.LicensingNet;

public class HttpRequests {

	private static final String PROTOCOL_TYPE_ID = "http"; //$NON-NLS-1$
	private static final String USER = "user"; //$NON-NLS-1$
	private static final String HOST = "host"; //$NON-NLS-1$
	private static final String PORT = "port"; //$NON-NLS-1$

	private static Logger logger = Logger.getLogger(HttpRequests.class.getName());

	public static Map<String, String> initRequestParams(String host, String port, String roleId, String productId,
			String productVersion) {
		Map<String, String> requestAttributes = new HashMap<>();
		requestAttributes.put(HOST, host);
		requestAttributes.put(PORT, port);

		requestAttributes.put(USER, "12345678");
		requestAttributes.put(LicensingNet.ROLE, roleId);
		requestAttributes.put(LicensingConfigurations.LICENSING_PRODUCT_IDENTIFIER, productId);
		requestAttributes.put(LicensingConfigurations.LICENSING_PRODUCT_VERSION, productVersion);
		return requestAttributes;
	}

	public static URIBuilder createRequestUriBuilder(Map<String, String> attributes) {
		String host = null;
		String port = null;
		Object hostAttr = attributes.get(HOST);
		Object portAttr = attributes.get(PORT);
		if (hostAttr instanceof String) {
			host = (String) hostAttr;
		} else {
			logger.info("Host value undefined.");
			return null;
		}

		if (portAttr instanceof String) {
			port = (String) portAttr;
		} else {
			logger.info("Port value undefined.");
			return null;
		}

		String requestHead = String.format("%s://%s:%s", PROTOCOL_TYPE_ID, host, port);
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
			logger.info(e.getMessage());
		}
		return null;
	}

	public static URIBuilder createRequestURI(Map<String, String> attributes, String action) {
		attributes.put(LicensingNet.ACTION, action);
		attributes.put(LicensingProperties.LICENSING_CONTENT_TYPE, LicensingProperties.LICENSING_CONTENT_TYPE_XML);
		return createRequestUriBuilder(attributes);
	}

}
