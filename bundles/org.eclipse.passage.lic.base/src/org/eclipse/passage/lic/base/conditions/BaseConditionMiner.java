/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.passage.lic.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.base.SystemReporter;
import org.eclipse.passage.lic.runtime.LicensingReporter;
import org.eclipse.passage.lic.runtime.conditions.ConditionMiner;
import org.eclipse.passage.lic.runtime.conditions.ConditionTransport;

public abstract class BaseConditionMiner implements ConditionMiner {

	protected LicensingReporter licensingReporter = SystemReporter.INSTANCE;
	protected KeyKeeperRegistry keyKeeperRegistry;
	protected StreamCodecRegistry streamCodecRegistry;
	protected final Map<String, ConditionTransport> conditionTransports = new HashMap<>();

	protected void bindLicensingReporter(LicensingReporter reporter) {
		this.licensingReporter = reporter;
	}

	protected void unbindLicensingReporter(LicensingReporter reporter) {
		if (this.licensingReporter == reporter) {
			this.licensingReporter = SystemReporter.INSTANCE;
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
		conditionTransports.remove(contentType, transport);
	}

	protected abstract String getBaseLocation();

}
