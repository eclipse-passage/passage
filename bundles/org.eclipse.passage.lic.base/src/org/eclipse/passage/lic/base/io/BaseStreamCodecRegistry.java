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
package org.eclipse.passage.lic.base.io;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.io.StreamCodec;
import org.eclipse.passage.lic.runtime.registry.StreamCodecRegistry;

public class BaseStreamCodecRegistry implements StreamCodecRegistry {

	private final Map<LicensingConfiguration, StreamCodec> streamCodecs = new HashMap<>();

	@Override
	public StreamCodec getStreamCodec(LicensingConfiguration configuration) {
		StreamCodec streamCodec = streamCodecs.get(configuration);
		if (streamCodec == null) {
			streamCodec = NullStreamCodec.INSTANCE;
		}
		return streamCodec;
	}

	@Override
	public void registerStreamCodec(StreamCodec codec, Map<String, Object> properties) {
		LicensingConfiguration key = LicensingConfigurations.create(properties);
		streamCodecs.put(key, codec);
	}

	@Override
	public void unregisterStreamCodec(StreamCodec codec, Map<String, Object> properties) {
		LicensingConfiguration key = LicensingConfigurations.create(properties);
		streamCodecs.remove(key, codec);
	}

}
