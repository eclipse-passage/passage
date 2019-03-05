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
package org.eclipse.passage.lbc.base.condition;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
import java.util.Map.Entry;

import org.eclipse.osgi.service.environment.EnvironmentInfo;
import org.eclipse.passage.lic.base.LicensingPaths;
import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.base.conditions.BaseLicensingCondition;
import org.eclipse.passage.lic.runtime.LicensingCondition;
import org.eclipse.passage.lic.runtime.io.LicensingConditionTransport;
import org.eclipse.passage.lic.runtime.io.StreamCodec;

import org.eclipse.passage.lbc.base.BaseComponent;
import org.eclipse.passage.lbc.runtime.LicensingConditionStorage;

public class ServerConditionsStorage extends BaseComponent implements LicensingConditionStorage {

	private static final String TRANSPORT_NOT_FOUND = "Transport for license descriptors not found";
	private EnvironmentInfo environmentInfo;
	private StreamCodec streamCodec;
	private String CONDITION_EXTENSION = ".licen";
	private LicensingConditionTransport conditionDescriptorTransport;
	private ServerConditionsArbitr conditionArbitr;

	public void bindEnvironmentInfo(EnvironmentInfo environmentInfo) {
		this.environmentInfo = environmentInfo;
	}

	public void unbindEnvironmentInfo(EnvironmentInfo environmentInfo) {
		this.environmentInfo = null;
	}

	public void bindStreamCodec(StreamCodec codec) {
		this.streamCodec = codec;
	}

	public void bindServerConditionsArbitr(ServerConditionsArbitr conditionArbitr) {
		this.conditionArbitr = conditionArbitr;
	}

	public void unbindStreamCodec(StreamCodec codec) {
		this.streamCodec = null;
	}

	public void bindConditionDescriptorTransport(LicensingConditionTransport transport,
			Map<String, Object> properties) {
		String contentType = String.valueOf(properties.get(LicensingProperties.LICENSING_CONTENT_TYPE));
		if (LicensingProperties.LICENSING_CONTENT_TYPE_XML.equals(contentType)) {
			this.conditionDescriptorTransport = transport;
		}
	}

	public void unbindConditionDescriptorTransport(LicensingConditionTransport transport,
			Map<String, Object> properties) {
		String contentType = String.valueOf(properties.get(LicensingProperties.LICENSING_CONTENT_TYPE));
		if (LicensingProperties.LICENSING_CONTENT_TYPE_XML.equals(contentType)) {
			this.conditionDescriptorTransport = null;
		}
	}

	@Override
	public List<BaseLicensingCondition> getLicensingCondition(String productId, String productVersion) {

		List<BaseLicensingCondition> descriptors = new ArrayList<>();
		String areaValue = environmentInfo.getProperty(LicensingPaths.PROPERTY_OSGI_INSTALL_AREA);
		Path areaPath = LicensingPaths.getBasePath(areaValue);

		if (!Files.isDirectory(areaPath)) {
			return descriptors;
		}

		Map<Path, File> producKeyMap2TokenPath = new HashMap<>();
		try {
			Files.walkFileTree(areaPath, new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult visitFile(Path filePath, BasicFileAttributes attrs) throws IOException {

					if (filePath.toString().toLowerCase().endsWith(CONDITION_EXTENSION)) {
						Path parentFolderVersion = filePath.getParent();
						Path parentFolderProduct = parentFolderVersion.getParent();
						String productIdFileName = parentFolderProduct.getFileName().toString();
						String productVersionFileName = parentFolderVersion.getFileName().toString();

						if (productId.equals(productIdFileName) && productVersion.equals(productVersionFileName)) {
							String productKeyFileName = String.format("%s_%s.pub", productId, productVersion);
							File productVersionFile = parentFolderVersion.toFile();
							for (File file : productVersionFile.listFiles()) {
								if (productKeyFileName.equals(file.getName())
										&& !producKeyMap2TokenPath.containsKey(filePath)) {
									producKeyMap2TokenPath.put(filePath, file);
									break;
								}
							}
						}

					}
					return FileVisitResult.CONTINUE;
				}

			});
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		for (Entry<Path, File> productEntry : producKeyMap2TokenPath.entrySet()) {

			File productConditionFile = productEntry.getKey().toFile();
			File productTokenFile = productEntry.getValue();

			if (productTokenFile == null || productConditionFile == null) {
				logger.error("Product Installation Token(public key) or Condition(license) not found");
				return descriptors;
			}

			ByteArrayOutputStream decodedTokenStream = new ByteArrayOutputStream();

			try (FileInputStream productTokenStream = new FileInputStream(productTokenFile);
					FileInputStream productConditionStream = new FileInputStream(productConditionFile)) {

				streamCodec.decodeStream(productConditionStream, decodedTokenStream, productTokenStream, null);
				byte[] byteArray = decodedTokenStream.toByteArray();
				try (ByteArrayInputStream input = new ByteArrayInputStream(byteArray)) {
					if (conditionDescriptorTransport != null) {
						Iterable<LicensingCondition> extractedConditions = conditionDescriptorTransport
								.readConditionDescriptors(input);

						for (LicensingCondition condition : extractedConditions) {
							if (conditionArbitr.addConditionToReserv(condition)) {
								descriptors.add((BaseLicensingCondition) condition);
							}
						}
					} else {
						logger.error(TRANSPORT_NOT_FOUND);
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return descriptors;
	}
}
