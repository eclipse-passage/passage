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
package org.eclipse.passage.lic.internal.hc;

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
import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.api.conditions.ConditionActions;
import org.eclipse.passage.lic.api.conditions.ConditionMiner;
import org.eclipse.passage.lic.api.conditions.ConditionTransport;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;
import org.eclipse.passage.lic.base.conditions.BaseConditionMiner;
import org.eclipse.passage.lic.equinox.io.EquinoxPaths;
import org.eclipse.passage.lic.hc.HttpRequests;
import org.eclipse.passage.lic.net.LicensingNet;
import org.osgi.service.component.annotations.Component;

@Component(service = ConditionMiner.class)
public class HcConditionMiner extends BaseConditionMiner {

	private static final String HOST_PORT = "%s:%s"; //$NON-NLS-1$

	private static final String SETTINGS_EXTENSION = ".settings"; //$NON-NLS-1$

	private final Map<String, String> settingsMap = new HashMap<>();

	Logger logger = Logger.getLogger(HcConditionMiner.class.getName());

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
			String hostValue = settingsMap.get(LicensingNet.LICENSING_SERVER_HOST);
			if (hostValue == null || hostValue.isEmpty()) {
				logger.log(Level.FINEST, HcMessages.HcConditionMiner_e_host_invalid);
				return conditions;
			}
			String portValue = settingsMap.get(LicensingNet.LICENSING_SERVER_PORT);
			if (portValue == null || portValue.isEmpty()) {
				logger.log(Level.FINEST, HcMessages.HcConditionMiner_e_port_invalid);
				return conditions;
			}
			String location = String.format(HOST_PORT, hostValue, portValue);

			// FIXME: use LicensingConfiguration
			String productId = "product.1"; //$NON-NLS-1$
			String productVersion = "1.0.0"; //$NON-NLS-1$
			Map<String, String> requestAttributes = HttpRequests.initRequestParams(hostValue, portValue,
					LicensingNet.ROLE, productId, productVersion);
			HttpHost host = HttpHost.create(location);

			URIBuilder requestBulder = HttpRequests.createRequestURI(requestAttributes, ConditionActions.ACQUIRE);
			// FIXME: rework API, it should never return null instead of builder instance
			if (requestBulder == null) {
				logger.log(Level.FINEST, "Could not create URI for request"); //$NON-NLS-1$
				return conditions;
			}
			HttpPost httpPost = new HttpPost(requestBulder.build());

			ResponseHandler<Iterable<LicensingCondition>> responseHandler = new ResponseHandler<Iterable<LicensingCondition>>() {

				@Override
				public Iterable<LicensingCondition> handleResponse(HttpResponse response)
						throws ClientProtocolException, IOException {

					Header header = response.getEntity().getContentType();
					String contentType = header.getValue();
					HttpEntity entity = response.getEntity();
					// FIXME: revisit and improve error handling
					if (entity != null && entity.getContent() != null) {
						ConditionTransport transport = conditionTransports.get(contentType);
						Iterable<LicensingCondition> conditionDescriptors = transport
								.readConditions(entity.getContent());
						return conditionDescriptors;
					} else {
						logger.log(Level.FINE, "Could not receive an InputStream from request"); //$NON-NLS-1$
					}
					return null;
				}
			};
			try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
				httpClient.execute(host, httpPost, responseHandler);
			}
		} catch (Exception e) {
			logger.log(Level.FINER, e.getMessage());
		}
		return conditions;
	}

	private Map<String, String> loadIstallationAreaSettings(List<String> readAllLines) {
		Map<String, String> settings = new HashMap<>();
		for (String iter : readAllLines) {
			String[] setting = iter.split("="); //$NON-NLS-1$
			if (setting.length == 2) {
				settings.put(setting[0], setting[1]);
			}
		}
		return settings;
	}

	@Override
	protected String getBaseLocation() {
		String hostValue = settingsMap.get(LicensingNet.LICENSING_SERVER_HOST);
		String portValue = settingsMap.get(LicensingNet.LICENSING_SERVER_PORT);
		return String.format(HOST_PORT, hostValue, portValue);
	}

}
