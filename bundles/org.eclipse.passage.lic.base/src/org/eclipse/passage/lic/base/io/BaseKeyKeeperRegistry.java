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
import org.eclipse.passage.lic.runtime.io.KeyKeeper;
import org.eclipse.passage.lic.runtime.registry.KeyKeeperRegistry;

public class BaseKeyKeeperRegistry implements KeyKeeperRegistry {

	private final Map<LicensingConfiguration, KeyKeeper> keyKeepers = new HashMap<>();

	@Override
	public KeyKeeper getKeyKeeper(LicensingConfiguration configuration) {
		KeyKeeper keyKeeper = keyKeepers.get(configuration);
		if (keyKeeper == null) {
			keyKeeper = NullKeyKeeper.INSTANCE;
		}
		return keyKeeper;
	}

	@Override
	public void registerKeyKeeper(KeyKeeper keeper, Map<String, Object> properties) {
		LicensingConfiguration key = LicensingConfigurations.create(properties);
		keyKeepers.put(key, keeper);
	}

	@Override
	public void unregisterKeyKeeper(KeyKeeper keeper, Map<String, Object> properties) {
		LicensingConfiguration key = LicensingConfigurations.create(properties);
		keyKeepers.remove(key, keeper);
	}

}
