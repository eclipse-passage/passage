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
import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.equinox.io.EquinoxPaths;
import org.eclipse.passage.lic.net.LicensingNet;
import org.eclipse.passage.lic.net.LicensingRequests;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.conditions.ConditionActions;
import org.eclipse.passage.lic.runtime.conditions.ConditionMiner;
import org.eclipse.passage.lic.runtime.conditions.ConditionTransport;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;
import org.osgi.service.component.annotations.Component;

@Component
public class NetConditionMiner implements ConditionMiner {

	private static final String HOST_PORT = "%s:%s"; //$NON-NLS-1$

	private static final String SETTINGS_EXTENSION = ".settings";

	private final Map<String, org.eclipse.passage.lic.runtime.conditions.ConditionTransport> contentType2ConditionTransport = new HashMap<>();

	private final Map<String, String> settingsMap = new HashMap<>();

	Logger logger = Logger.getLogger(NetConditionMiner.class.getName());

	@Override
	public Iterable<LicensingCondition> extractLicensingConditions(LicensingConfiguration configuration) {

		List<LicensingCondition> conditions = new ArrayList<>();

		Path configurationPath = EquinoxPaths.resolveInstallConfigurationPath(configuration);
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
			String hostValue = settingsMap.get(LicensingNet.LICENSING_SERVER_HOST);
			if (hostValue == null || hostValue.isEmpty()) {
				logger.log(Level.FINEST, NetConditionMsg.HOST_VALUE_NOT_SPECIFIED_ERROR);
				return conditions;
			}
			String portValue = settingsMap.get(LicensingNet.LICENSING_SERVER_PORT);
			if (portValue == null || portValue.isEmpty()) {
				logger.log(Level.FINEST, NetConditionMsg.PORT_VALUE_NOT_SPECIFIED_ERROR);
				return conditions;
			}

			Map<String, String> requestAttributes = LicensingRequests.initRequestParams(hostValue, portValue,
					LicensingNet.ROLE, "product.1", "1.0.0");
			HttpHost host = HttpHost.create(String.format(HOST_PORT, hostValue, portValue));
			URIBuilder requestBulder = LicensingRequests.createRequestURI(requestAttributes,
					ConditionActions.ACQUIRE);
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
						org.eclipse.passage.lic.runtime.conditions.ConditionTransport transport = getConditionTransport(
								contentType);
						Iterable<LicensingCondition> conditionDescriptors = transport
								.readConditions(entity.getContent());
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

	private ConditionTransport getConditionTransport(String typeOfContent) {
		return contentType2ConditionTransport.get(typeOfContent);
	}

	public void bindConditionTransport(ConditionTransport transport, Map<String, String> context) {
		String contentType = context.get(LicensingProperties.LICENSING_CONTENT_TYPE);
		if (contentType != null) {
			if (!contentType2ConditionTransport.containsKey(contentType)) {
				contentType2ConditionTransport.put(contentType, transport);
			}
		}
	}

	public void unbindConditionTransport(ConditionTransport transport, Map<String, String> context) {
		String contentType = context.get(LicensingProperties.LICENSING_CONTENT_TYPE);
		if (contentType != null) {
			if (contentType2ConditionTransport.containsKey(contentType)) {
				contentType2ConditionTransport.remove(contentType, transport);
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
