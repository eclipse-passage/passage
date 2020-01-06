/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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
package org.eclipse.passage.lbc.server.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNoException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.eclipse.passage.lic.api.conditions.LicensingCondition;
import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.base.io.LicensingPaths;
import org.eclipse.passage.lic.equinox.io.EquinoxPaths;
import org.eclipse.passage.lic.hc.HttpRequests;
import org.eclipse.passage.lic.net.LicensingNet;
import org.junit.BeforeClass;
import org.junit.Test;

public class ServerConditionMinerTest {

	private static final String EXTENSION_SERVER_SETTINGS = ".settings"; //$NON-NLS-1$
	private static final String PASSAGE_SERVER_HOST_DEF = LicensingNet.LICENSING_SERVER_HOST + "=localhost"; //$NON-NLS-1$
	private static final String PASSAGE_SERVER_PORT_DEF = LicensingNet.LICENSING_SERVER_PORT + "=8080"; //$NON-NLS-1$
	private static final String PRODUCT_ID_TEST = "product.test"; //$NON-NLS-1$

	/**
	 * Passed through maven-surefire-plugin configuration
	 */
	private static final String MVN_PROJECT_OUTPUT_PROPERTY = "project.build.directory"; //$NON-NLS-1$
	private static final String MVN_PROJECT_OUTPUT_VALUE = "target"; //$NON-NLS-1$

	public static String resolveOutputDirName() {
		String userDir = System.getProperty("user.dir"); //$NON-NLS-1$
		String defaultValue = userDir + File.separator + MVN_PROJECT_OUTPUT_VALUE;
		String outDir = System.getProperty(MVN_PROJECT_OUTPUT_PROPERTY, defaultValue);
		return outDir;
	}

	@BeforeClass
	public static void preprocesingTest() {
		try {
			createServerConfiguration(LicensingConfigurations.create(PRODUCT_ID_TEST, null));
		} catch (IOException e) {
			assumeNoException(e.getMessage(), e);
		}
	}

	private static void createServerConfiguration(LicensingConfiguration configuration) throws IOException {
		Path path = EquinoxPaths.resolveInstallConfigurationPath(configuration);
		Files.createDirectories(path);
		String fileName = LicensingPaths.composeFileName(configuration, EXTENSION_SERVER_SETTINGS);
		File serverConfigurationFile = path.resolve(fileName).toFile();

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(serverConfigurationFile));) {
			bw.write(PASSAGE_SERVER_HOST_DEF);
			bw.newLine();
			bw.write(PASSAGE_SERVER_PORT_DEF);
			bw.newLine();
			bw.flush();
		} catch (Exception e) {
			assumeNoException(e.getMessage(), e);
		}

	}

	@Test
	public void mineConditionFromServerPositiveTest() {
		LicensingConfiguration configuration = LicensingConfigurations.create(PRODUCT_ID_TEST, null);
		Path configurationPath = EquinoxPaths.resolveInstallConfigurationPath(configuration);
		assertTrue(Files.isDirectory(configurationPath));

		List<Path> settinsFiles = new ArrayList<>();
		try {
			Files.walkFileTree(configurationPath, new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					if (file.toString().toLowerCase().endsWith(EXTENSION_SERVER_SETTINGS)) {
						settinsFiles.add(file);
					}
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			assumeNoException(e);
		}
		Map<String, String> settingsMap = new HashMap<>();
		for (Path path : settinsFiles) {
			try {
				Map<String, String> loadedSettings = loadIstallationAreaSettings(Files.readAllLines(path));
				settingsMap.putAll(loadedSettings);

			} catch (Exception e) {
				assumeNoException(e);
			}
		}

		CloseableHttpClient httpClient = HttpClients.createDefault();
		String hostValue = settingsMap.get(LicensingNet.LICENSING_SERVER_HOST);
		assertNotNull(hostValue);
		assertFalse(hostValue.isEmpty());

		String portValue = settingsMap.get(LicensingNet.LICENSING_SERVER_PORT);
		assertNotNull(portValue);
		assertFalse(portValue.isEmpty());

		// FIXME: rework, currently it duplicates HcConditionMiner
		String productId = "product1.id"; //$NON-NLS-1$
		String productVersion = "1.0.0"; //$NON-NLS-1$
		Map<String, String> requestAttributes = HttpRequests.initRequestParams(hostValue, portValue,
				LicensingNet.ROLE_LICENSEE, productId, productVersion);
		HttpHost host = HttpHost.create(String.format("%s:%s", hostValue, portValue)); //$NON-NLS-1$
		URIBuilder requestBulder = HttpRequests.createRequestURI(requestAttributes, ConditionActions.ACQUIRE);

		assertNotNull(requestBulder);

		HttpPost httpPost = null;
		try {
			httpPost = new HttpPost(requestBulder.build());
		} catch (URISyntaxException e) {
			assumeNoException(e);
		}

		ResponseHandler<Iterable<LicensingCondition>> responseHandler = new ResponseHandler<Iterable<LicensingCondition>>() {

			@Override
			public Iterable<LicensingCondition> handleResponse(HttpResponse response)
					throws ClientProtocolException, IOException {
				HttpEntity entity = response.getEntity();
				assertNotNull(entity);
				assertNotNull(entity.getContent());
				return null;
			}
		};
		try {
			httpClient.execute(host, httpPost, responseHandler);
		} catch (IOException e) {
			assumeNoException(e);
		}

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
}
