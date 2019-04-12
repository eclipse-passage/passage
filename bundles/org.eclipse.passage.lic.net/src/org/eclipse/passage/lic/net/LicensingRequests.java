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
package org.eclipse.passage.lic.net;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.apache.http.client.utils.URIBuilder;
import org.eclipse.passage.lic.base.LicensingProperties;

public class LicensingRequests {

	public static final String PRODUCT = "product"; //$NON-NLS-1$
	public static final String VERSION = "version"; //$NON-NLS-1$
	public static final String USER = "user"; //$NON-NLS-1$

	public static final String PROTOCOL_TYPE_ID = "http";
	public static final String HOST = "host";
	public static final String PORT = "port";
	public static final String CONTENT_TYPE = "content.type";

	private static Logger logger = Logger.getLogger(LicensingRequests.class.getName());

	public static Map<String, String> initRequestParams(String host, String port, String roleId, String productId,
			String productVersion) {
		Map<String, String> requestAttributes = new HashMap<>();
		requestAttributes.put(HOST, host);
		requestAttributes.put(PORT, port);

		requestAttributes.put(USER, "12345678");
		requestAttributes.put(LicensingNet.ROLE, roleId);
		requestAttributes.put(PRODUCT, productId);
		requestAttributes.put(VERSION, productVersion);
		return requestAttributes;
	}

	public static URIBuilder createRequestUriBuilder(Map<String, String> attributes) {
		String host = "";
		String port = "";
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
		attributes.put(CONTENT_TYPE, LicensingProperties.LICENSING_CONTENT_TYPE_XML);
		return createRequestUriBuilder(attributes);
	}

}
