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
package org.eclipse.passage.lic.base.conditions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.base.io.LicensingPaths;
import org.eclipse.passage.lic.base.io.NullKeyKeeper;
import org.eclipse.passage.lic.base.io.NullStreamCodec;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.LicensingException;
import org.eclipse.passage.lic.runtime.LicensingReporter;
import org.eclipse.passage.lic.runtime.LicensingResult;
import org.eclipse.passage.lic.runtime.conditions.ConditionMiner;
import org.eclipse.passage.lic.runtime.conditions.ConditionTransport;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;
import org.eclipse.passage.lic.runtime.io.KeyKeeper;
import org.eclipse.passage.lic.runtime.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.runtime.io.StreamCodec;
import org.eclipse.passage.lic.runtime.io.StreamCodecRegistry;

public abstract class BasePathConditionMiner implements ConditionMiner {

	private LicensingReporter licensingReporter;
	private KeyKeeperRegistry keyKeeperRegistry;
	private StreamCodecRegistry streamCodecRegistry;
	private final Map<String, ConditionTransport> conditionTransports = new HashMap<>();

	protected void bindLicensingReporter(LicensingReporter reporter) {
		this.licensingReporter = reporter;
	}

	protected void unbindLicensingReporter(LicensingReporter reporter) {
		if (this.licensingReporter == reporter) {
			this.licensingReporter = null;
		}
	}

	protected void bindKeyKeeperRegistry(KeyKeeperRegistry registry) {
		this.keyKeeperRegistry = registry;
	}

	protected void unbindKeyKeeperRegistry(KeyKeeperRegistry registry) {
		if (this.keyKeeperRegistry == registry) {
			this.keyKeeperRegistry = null;
		}
	}

	protected void bindStreamCodecRegistry(StreamCodecRegistry registry) {
		this.streamCodecRegistry = registry;
	}

	protected void unbindStreamCodecRegistry(StreamCodecRegistry registry) {
		if (this.streamCodecRegistry == registry) {
			this.streamCodecRegistry = null;
		}
	}

	protected void bindConditionTransport(ConditionTransport transport, Map<String, Object> properties) {
		String contentType = String.valueOf(properties.get(LicensingProperties.LICENSING_CONTENT_TYPE));
		conditionTransports.put(contentType, transport);
	}

	protected void unbindConditionTransport(ConditionTransport transport, Map<String, Object> properties) {
		String contentType = String.valueOf(properties.get(LicensingProperties.LICENSING_CONTENT_TYPE));
		ConditionTransport current = conditionTransports.get(contentType);
		if (transport == current) {
			conditionTransports.remove(contentType);
		}
	}

	@Override
	public final Iterable<LicensingCondition> extractLicensingConditions(LicensingConfiguration configuration) {
		String source = getClass().getName();
		List<LicensingCondition> mined = new ArrayList<>();
		if (configuration == null) {
			return mined;
		}
		ConditionTransport transport = conditionTransports.get(LicensingProperties.LICENSING_CONTENT_TYPE_XML);
		if (transport == null) {
			return mined;
		}

		Path from = getBasePath();
		Path configurationPath = LicensingPaths.resolveConfigurationPath(from, configuration);
		if (!Files.isDirectory(configurationPath)) {
			return mined;
		}
		KeyKeeper keyKeeper = keyKeeperRegistry.getKeyKeeper(configuration);
		StreamCodec streamCodec = streamCodecRegistry.getStreamCodec(configuration);
		String extension;
		if (NullKeyKeeper.INSTANCE == keyKeeper && NullStreamCodec.INSTANCE == streamCodec) {
			extension = LicensingPaths.EXTENSION_LICENSE_DECRYPTED;
		} else {
			extension = LicensingPaths.EXTENSION_LICENSE_ENCRYPTED;
		}
		try {
			List<String> packs = ConditionMiners.collectPacks(configurationPath, extension);

			LicensingResult result = ConditionMiners.mine(source, configuration, mined, keyKeeper, streamCodec,
					transport, packs);
			licensingReporter.logResult(result);
		} catch (LicensingException e) {
			licensingReporter.logResult(e.getResult());
		}

		return mined;
	}

	protected abstract Path getBasePath();

}
