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
import org.eclipse.passage.lic.runtime.conditions.ConditionMiner;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;
import org.eclipse.passage.lic.runtime.conditions.LicensingConditionTransport;
import org.eclipse.passage.lic.runtime.io.KeyKeeper;
import org.eclipse.passage.lic.runtime.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.runtime.io.StreamCodec;
import org.eclipse.passage.lic.runtime.io.StreamCodecRegistry;

public abstract class BasePathConditionMiner implements ConditionMiner {

	private KeyKeeperRegistry keyKeeperRegistry;
	private StreamCodecRegistry streamCodecRegistry;
	private final Map<String, LicensingConditionTransport> conditionTransports = new HashMap<>();

	protected void bindKeyKeeperRegistry(KeyKeeperRegistry registry) {
		this.keyKeeperRegistry = registry;
	}

	protected void unbindKeyKeeperRegistry(KeyKeeperRegistry registry) {
		if (this.keyKeeperRegistry == registry) {
			this.keyKeeperRegistry = registry;
		}
	}

	protected void bindStreamCodecRegistry(StreamCodecRegistry registry) {
		this.streamCodecRegistry = registry;
	}

	protected void unbindStreamCodecRegistry(StreamCodecRegistry registry) {
		if (this.streamCodecRegistry == registry) {
			this.streamCodecRegistry = registry;
		}
	}

	protected void bindConditionDescriptorTransport(LicensingConditionTransport transport,
			Map<String, Object> properties) {
		String contentType = String.valueOf(properties.get(LicensingProperties.LICENSING_CONTENT_TYPE));
		conditionTransports.put(contentType, transport);
	}

	protected void unbindConditionDescriptorTransport(LicensingConditionTransport transport,
			Map<String, Object> properties) {
		String contentType = String.valueOf(properties.get(LicensingProperties.LICENSING_CONTENT_TYPE));
		LicensingConditionTransport current = conditionTransports.get(contentType);
		if (transport == current) {
			conditionTransports.remove(contentType);
		}
	}

	@Override
	public final Iterable<LicensingCondition> extractLicensingConditions(LicensingConfiguration configuration)
			throws LicensingException {
		List<LicensingCondition> mined = new ArrayList<>();
		if (configuration == null) {
			return mined;
		}
		LicensingConditionTransport transport = conditionTransports.get(LicensingProperties.LICENSING_CONTENT_TYPE_XML);
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
		if (NullKeyKeeper.INSTANCE == keyKeeper && NullStreamCodec.INSTANCE == streamCodec) {
			ConditionMiners.mineDecrypted(transport, configurationPath, mined);
		} else {
			ConditionMiners.mineEncrypted(transport, configuration, configurationPath, streamCodec, keyKeeper, mined);
		}
		return mined;
	}

	protected abstract Path getBasePath() throws LicensingException;

}
