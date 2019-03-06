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
package org.eclipse.passage.lic.equinox.conditions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

import org.eclipse.osgi.service.environment.EnvironmentInfo;
import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.base.LicensingPaths;
import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.runtime.ConditionMiner;
import org.eclipse.passage.lic.runtime.LicensingCondition;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.io.KeyKeeper;
import org.eclipse.passage.lic.runtime.io.LicensingConditionTransport;
import org.eclipse.passage.lic.runtime.io.StreamCodec;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

public class EnvironmentBasedConditionMiner implements ConditionMiner {
	
	private final Logger logger = Logger.getLogger(EnvironmentBasedConditionMiner.class.getName());

	private final Map<LicensingConfiguration, StreamCodec> streamCodecs = new HashMap<>();
	private final Map<LicensingConfiguration, KeyKeeper> keyKeepers = new HashMap<>();

	private EnvironmentInfo environmentInfo;
	private String conditionArea = LicensingPaths.PROPERTY_OSGI_INSTALL_AREA;
	private LicensingConditionTransport conditionDescriptorTransport;
	
	@Activate
	public void activate(Map<String, Object> properties) {
		Object object = properties.get(LicensingProperties.LICENSING_CONDITION_AREA);
		String value = String.valueOf(object);
		conditionArea = value;
	}

	@Reference
	public void bindEnvironmentInfo(EnvironmentInfo environmentInfo) {
		this.environmentInfo = environmentInfo;
	}

	public void unbindEnvironmentInfo(EnvironmentInfo environmentInfo) {
		this.environmentInfo = null;
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void bindKeyKeeper(KeyKeeper keeper, Map<String, Object> properties) {
		LicensingConfiguration key = LicensingConfigurations.create(properties);
		keyKeepers.put(key, keeper);
	}

	public void unbindKeyKeeper(KeyKeeper keeper, Map<String, Object> properties) {
		LicensingConfiguration key = LicensingConfigurations.create(properties);
		keyKeepers.remove(key, keeper);
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void bindStreamCodec(StreamCodec codec, Map<String, Object> properties) {
		LicensingConfiguration key = LicensingConfigurations.create(properties);
		streamCodecs.put(key, codec);
	}

	public void unbindStreamCodec(StreamCodec codec, Map<String, Object> properties) {
		LicensingConfiguration key = LicensingConfigurations.create(properties);
		streamCodecs.remove(key, codec);
	}

	@Reference(cardinality = ReferenceCardinality.AT_LEAST_ONE)
	public void bindConditionDescriptorTransport(LicensingConditionTransport transport, Map<String, Object> properties) {
		String contentType = String.valueOf(properties.get(LicensingProperties.LICENSING_CONTENT_TYPE));
		if (LicensingProperties.LICENSING_CONTENT_TYPE_XML.equals(contentType)) {
			this.conditionDescriptorTransport = transport;
		}
	}

	public void unbindConditionDescriptorTransport(LicensingConditionTransport transport, Map<String, Object> properties) {
		String contentType = String.valueOf(properties.get(LicensingProperties.LICENSING_CONTENT_TYPE));
		if (LicensingProperties.LICENSING_CONTENT_TYPE_XML.equals(contentType)) {
			this.conditionDescriptorTransport = null;
		}
	}

	@Override
	public Iterable<LicensingCondition> extractLicensingConditions(LicensingConfiguration configuration) {
		List<LicensingCondition> mined = new ArrayList<>();
		if (configuration == null) {
			return mined;
		}
		if (conditionDescriptorTransport == null) {
			return mined;
		}
		if (environmentInfo == null) {
			return mined;
		}
		KeyKeeper keyKeeper = keyKeepers.get(configuration);
		StreamCodec streamCodec = streamCodecs.get(configuration);
		if (!isConsistens(streamCodec, keyKeeper)) {
			return mined;
		}
		
		String areaValue = environmentInfo.getProperty(conditionArea);
		Path configurationPath = LicensingPaths.resolveConfigurationPath(areaValue, configuration);
		if (!Files.isDirectory(configurationPath)) {
			return mined;
		}
		if (streamCodec == null) {
			mineDecrypted(configurationPath, mined);
		} else {
			mineEncrypted(configuration, configurationPath, streamCodec, keyKeeper, mined);
		}
		return mined;
	}

	protected boolean isConsistens(StreamCodec streamCodec, KeyKeeper keyKeeper) {
		if (streamCodec == null && keyKeeper == null) {
			//not encrypted
			return true;
		}
		if (streamCodec != null && keyKeeper != null) {
			//encrypted
			return true;
		}
		//invalid
		return false;
	}

	protected void mineDecrypted(Path configurationPath, List<LicensingCondition> mined) {
		List<Path> licenseFiles = collectPacks(configurationPath, LicensingPaths.EXTENSION_LICENSE_DECRYPTED);
		for (Path path : licenseFiles) {
			try (FileInputStream raw = new FileInputStream(path.toFile())){
				Iterable<LicensingCondition> extracted = conditionDescriptorTransport.readConditionDescriptors(raw);
				for (LicensingCondition condition : extracted) {
					mined.add(condition);
				}
			} catch (Exception e) {
				logger.log(Level.FINEST, e.getMessage(), e);
			}
		}
	}

	protected void mineEncrypted(LicensingConfiguration configuration, Path configurationPath, StreamCodec streamCodec,
			KeyKeeper keyKeeper, List<LicensingCondition> mined) {
		List<Path> licenseFiles = collectPacks(configurationPath, LicensingPaths.EXTENSION_LICENSE_ENCRYPTED);
		for (Path path : licenseFiles) {
			try (FileInputStream encoded = new FileInputStream(path.toFile()); ByteArrayOutputStream decoded = new ByteArrayOutputStream(); InputStream keyRing = keyKeeper.openKeyStream(configuration)){
				streamCodec.decodeStream(encoded, decoded, keyRing, null);
				byte[] byteArray = decoded.toByteArray();
				try (ByteArrayInputStream input = new ByteArrayInputStream(byteArray)) {
					Iterable<LicensingCondition> extracted = conditionDescriptorTransport.readConditionDescriptors(input);
					for (LicensingCondition condition : extracted) {
						mined.add(condition);
					}
				} catch (Exception e) {
					logger.log(Level.SEVERE, "Failed to extract conditions", e);
				}
			} catch (Exception e) {
				logger.log(Level.FINEST, e.getMessage(), e);
			}
		}
	}

	protected List<Path> collectPacks(Path configurationPath, String extension) {
		List<Path> licenseFiles = new ArrayList<>();
		try {
			Files.walkFileTree(configurationPath, new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					String lowerCase = file.toString().toLowerCase();
					if (lowerCase.endsWith(extension)) {
						licenseFiles.add(file);
					}
					return FileVisitResult.CONTINUE;
				}

			});
		} catch (IOException e) {
			logger.log(Level.FINEST, e.getMessage(), e);
		}
		return licenseFiles;
	}

}
