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
package org.eclipse.passage.lic.internal.json;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.net.LicensingNet;
import org.eclipse.passage.lic.net.LicensingRequests;
import org.eclipse.passage.lic.runtime.access.FeaturePermission;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestProducer {

	static Logger logger = Logger.getLogger(RequestProducer.class.getName());

	private static final String REQUEST_ACTION_CONDITIONS_EVALUATE = "evaluateConditions"; // NLS-$1
	private static final String CHARSET_UTF_8 = "UTF-8"; // NLS-$1

	public static final String PARAMETER_CONFIGURATION = "configuration"; // NLS-$1

	public Iterable<? extends FeaturePermission> evaluateConditionsRequest(CloseableHttpClient httpClient,
			HttpHost host, Map<String, String> requestAttributes, Iterable<LicensingCondition> conditions) {
		try {
			requestAttributes.put(LicensingNet.ACTION, REQUEST_ACTION_CONDITIONS_EVALUATE);
			URIBuilder builder = LicensingRequests.createRequestUriBuilder(requestAttributes);
			FeaturePermissionAggregator transferObject = processingEvaluateConditions(httpClient, host, builder,
					conditions);
			return transferObject.getFeaturePermissions();
		} catch (Exception e) {
			Logger.getLogger(RequestProducer.class.getName()).info(e.getMessage());
		}
		return Collections.emptyList();
	}

	private FeaturePermissionAggregator processingEvaluateConditions(CloseableHttpClient httpClient, HttpHost host,
			URIBuilder builder, Iterable<LicensingCondition> conditions)
			throws URISyntaxException, ClientProtocolException, IOException {

		HttpPost httpPost = new HttpPost(builder.build());
		ObjectMapper mapper = JsonTransport.createObjectMapper();

		LicensingConditionAggregator transferObject = new LicensingConditionAggregator();
		for (LicensingCondition d : conditions) {
			transferObject.addLicensingCondition(d);
		}

		String objectAsString = mapper.writeValueAsString(transferObject);
		StringEntity entity = new StringEntity(objectAsString, CHARSET_UTF_8);
		httpPost.setEntity(entity);
		httpPost.setHeader("Content-type", LicensingProperties.LICENSING_CONTENT_TYPE_JSON);

		ResponseHandler<FeaturePermissionAggregator> responseHandler = new ResponseHandler<FeaturePermissionAggregator>() {

			@Override
			public FeaturePermissionAggregator handleResponse(HttpResponse response)
					throws ClientProtocolException, IOException {
				HttpEntity entity = response.getEntity();
				ObjectMapper mapper = JsonTransport.createObjectMapper();
				try (InputStream inputContext = entity.getContent()) {
					FeaturePermissionAggregator transferObject = null;
					transferObject = mapper.readValue(inputContext, FeaturePermissionAggregator.class);
					return transferObject;

				} catch (Exception e) {
					logger.info(e.getMessage());
					return null;
				}
			}
		};
		return httpClient.execute(host, httpPost, responseHandler);
	}

}
