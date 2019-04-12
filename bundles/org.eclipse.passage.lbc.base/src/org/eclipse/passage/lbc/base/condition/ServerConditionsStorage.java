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

import org.eclipse.passage.lbc.base.BaseComponent;
import org.eclipse.passage.lbc.runtime.conditions.LicensingConditionStorage;
import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.equinox.io.EquinoxPaths;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.conditions.ConditionTransport;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;
import org.eclipse.passage.lic.runtime.io.StreamCodec;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.log.LoggerFactory;

@Component
public class ServerConditionsStorage extends BaseComponent implements LicensingConditionStorage {

	private static final String TRANSPORT_NOT_FOUND = "Transport for license descriptors not found";
	private StreamCodec streamCodec;
	private String CONDITION_EXTENSION = ".licen";
	private ConditionTransport conditionTransport;
	private ServerConditionsArbitr conditionArbitr;

	@Override
	@Reference
	protected void bindLogger(LoggerFactory loggerFactory) {
		super.bindLogger(loggerFactory);
	}

	@Reference
	public void bindStreamCodec(StreamCodec codec) {
		this.streamCodec = codec;
	}

	public void unbindStreamCodec(StreamCodec codec) {
		this.streamCodec = null;
	}

	public void bindServerConditionsArbitr(ServerConditionsArbitr conditionArbitr) {
		this.conditionArbitr = conditionArbitr;
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void bindConditionTransport(ConditionTransport transport, Map<String, Object> properties) {
		String contentType = String.valueOf(properties.get(LicensingProperties.LICENSING_CONTENT_TYPE));
		if (LicensingProperties.LICENSING_CONTENT_TYPE_XML.equals(contentType)) {
			this.conditionTransport = transport;
		}
	}

	public void unbindConditionTransport(ConditionTransport transport, Map<String, Object> properties) {
		String contentType = String.valueOf(properties.get(LicensingProperties.LICENSING_CONTENT_TYPE));
		if (LicensingProperties.LICENSING_CONTENT_TYPE_XML.equals(contentType)) {
			this.conditionTransport = null;
		}
	}

	@Override
	public Iterable<LicensingCondition> getLicensingCondition(LicensingConfiguration configuration) {

		List<LicensingCondition> descriptors = new ArrayList<>();
		Path areaPath = EquinoxPaths.resolveInstallBasePath();

		if (!Files.isDirectory(areaPath)) {
			return descriptors;
		}
		String productId = configuration.getProductIdentifier();
		String productVersion = configuration.getProductVersion();

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
					if (conditionTransport != null) {
						Iterable<LicensingCondition> extractedConditions = conditionTransport.readConditions(input);

						for (LicensingCondition condition : extractedConditions) {
							if (conditionArbitr.addConditionToReserv(condition)) {
								descriptors.add(condition);
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
