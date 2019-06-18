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
import java.util.List;

import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.api.conditions.ConditionTransport;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;
import org.eclipse.passage.lic.api.io.KeyKeeper;
import org.eclipse.passage.lic.api.io.StreamCodec;
import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.base.io.LicensingPaths;
import org.eclipse.passage.lic.base.io.NullKeyKeeper;
import org.eclipse.passage.lic.base.io.NullStreamCodec;
import org.eclipse.passage.lic.internal.base.i18n.BaseMessages;

public abstract class PathConditionMiner extends BaseConditionMiner {

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
			String message = String.format(BaseMessages.getString("PathConditionMiner.e_not_a_directory"), //$NON-NLS-1$
					configurationPath);
			licensingReporter.logResult(LicensingResults.createError(message, source));
			return mined;
		}
		if (keyKeeperRegistry == null) {
			licensingReporter.logResult(LicensingResults
					.createError(BaseMessages.getString("PathConditionMiner.e_no_key_keeper_registry"), source)); //$NON-NLS-1$
			return mined;
		}
		KeyKeeper keyKeeper = keyKeeperRegistry.getKeyKeeper(configuration);
		if (streamCodecRegistry == null) {
			licensingReporter.logResult(LicensingResults
					.createError(BaseMessages.getString("PathConditionMiner.e_no_stream_codec_registry"), source)); //$NON-NLS-1$
			return mined;
		}
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

	@Override
	protected String getBaseLocation() {
		return getBasePath().toString();
	}

}
