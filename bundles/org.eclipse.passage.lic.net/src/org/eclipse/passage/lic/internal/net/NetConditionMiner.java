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
package org.eclipse.passage.lic.internal.net;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.eclipse.osgi.service.environment.EnvironmentInfo;
import org.eclipse.passage.lic.base.LicensingPaths;
import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.net.LicensingRequests;
import org.eclipse.passage.lic.runtime.ConditionMiner;
import org.eclipse.passage.lic.runtime.LicensingCondition;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.io.LicensingConditionTransport;

public class NetConditionMiner implements ConditionMiner {

	private static final String HOST_PORT = "%s:%s"; //$NON-NLS-1$

	private static final String SETTINGS_EXTENSION = ".settings";
	private static final String MODE = "client";
	private static final String MINER_LICENSING_CONDITION_TYPE = "extractConditions";

	private static String HOST_KEY = "passage.server.host";
	private static String PORT_KEY = "passage.server.port";

	private EnvironmentInfo environmentInfo;

	private final Map<String, org.eclipse.passage.lic.runtime.io.LicensingConditionTransport> contentType2ConditionDescriptorTransport = new HashMap<>();

	private final Map<String, String> settingsMap = new HashMap<>();

	Logger logger = Logger.getLogger(NetConditionMiner.class.getName());

	public void bindEnvironmentInfo(EnvironmentInfo environmentInfo) {
		this.environmentInfo = environmentInfo;
	}

	public void unbindEnvironmentInfo(EnvironmentInfo environmentInfo) {
		this.environmentInfo = null;
	}

	@Override
	public Iterable<LicensingCondition> extractLicensingConditions(LicensingConfiguration configuration) {

		List<LicensingCondition> conditions = new ArrayList<>();

		if (environmentInfo == null) {
			return conditions;
		}
		String areaValue = environmentInfo.getProperty(LicensingPaths.PROPERTY_OSGI_INSTALL_AREA);
		Path configurationPath = LicensingPaths.resolveConfigurationPath(areaValue, configuration);
		if (!Files.isDirectory(configurationPath)) {
			return conditions;
		}
		List<Path> settinsFiles = new ArrayList<>();
		try {
			Files.walkFileTree(configurationPath, new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					if (file.toString().toLowerCase().endsWith(SETTINGS_EXTENSION)) {
						settinsFiles.add(file);
					}
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			logger.log(Level.FINEST, e.getMessage(), e);
		}

		for (Path path : settinsFiles) {
			try {
				Map<String, String> loadedSettings = loadIstallationAreaSettings(Files.readAllLines(path));
				settingsMap.putAll(loadedSettings);

			} catch (Exception e) {
				logger.log(Level.FINEST, e.getMessage(), e);
			}
		}

		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			String hostValue = settingsMap.get(HOST_KEY);
			if (hostValue == null || hostValue.isEmpty()) {
				logger.log(Level.FINEST, NetConditionMsg.HOST_VALUE_NOT_SPECIFIED_ERROR);
				return conditions;
			}
			String portValue = settingsMap.get(PORT_KEY);
			if (portValue == null || portValue.isEmpty()) {
				logger.log(Level.FINEST, NetConditionMsg.PORT_VALUE_NOT_SPECIFIED_ERROR);
				return conditions;
			}

			Map<String, String> requestAttributes = LicensingRequests.initRequestParams(hostValue, portValue, MODE,
					"product.1", "1.0.0");
			HttpHost host = HttpHost.create(String.format(HOST_PORT, hostValue, portValue));
			URIBuilder requestBulder = LicensingRequests.createRequestURI(httpClient, host, requestAttributes,
					MINER_LICENSING_CONDITION_TYPE);
			if (requestBulder == null) {
				logger.log(Level.FINEST, "Could not create URI for request");
			}
			HttpPost httpPost = new HttpPost(requestBulder.build());

			ResponseHandler<Iterable<LicensingCondition>> responseHandler = new ResponseHandler<Iterable<LicensingCondition>>() {

				@Override
				public Iterable<LicensingCondition> handleResponse(HttpResponse response)
						throws ClientProtocolException, IOException {

					Header header = response.getEntity().getContentType();
					String contentType = header.getValue();
					HttpEntity entity = response.getEntity();
					if (entity != null && entity.getContent() != null) {
						org.eclipse.passage.lic.runtime.io.LicensingConditionTransport transport = getConditionDescriptorTransport(
								contentType);
						Iterable<LicensingCondition> conditionDescriptors = transport
								.readConditionDescriptors(entity.getContent());
						return conditionDescriptors;
					} else {
						logger.log(Level.FINE, "Could not recieve a inputStream from request");
					}
					return null;
				}
			};
			httpClient.execute(host, httpPost, responseHandler);

		} catch (Exception e) {
			logger.log(Level.FINER, e.getMessage());
		}
		return conditions;
	}

	private org.eclipse.passage.lic.runtime.io.LicensingConditionTransport getConditionDescriptorTransport(
			String typeOfContent) {
		return contentType2ConditionDescriptorTransport.get(typeOfContent);
	}

	public void bindConditionDescriptorTransport(LicensingConditionTransport transport, Map<String, String> context) {
		String contentType = context.get(LicensingProperties.LICENSING_CONTENT_TYPE);
		if (contentType != null) {
			if (!contentType2ConditionDescriptorTransport.containsKey(contentType)) {
				contentType2ConditionDescriptorTransport.put(contentType, transport);
			}
		}
	}

	public void unbindConditionDescriptorTransport(LicensingConditionTransport transport, Map<String, String> context) {
		String contentType = context.get(LicensingProperties.LICENSING_CONTENT_TYPE);
		if (contentType != null) {
			if (contentType2ConditionDescriptorTransport.containsKey(contentType)) {
				contentType2ConditionDescriptorTransport.remove(contentType, transport);
			}
		}
	}

	private Map<String, String> loadIstallationAreaSettings(List<String> readAllLines) {
		Map<String, String> settings = new HashMap<>();
		for (String iter : readAllLines) {
			String[] setting = iter.split("=");
			if (setting.length == 2) {
				settings.put(setting[0], setting[1]);
			}
		}
		return settings;
	}

}
