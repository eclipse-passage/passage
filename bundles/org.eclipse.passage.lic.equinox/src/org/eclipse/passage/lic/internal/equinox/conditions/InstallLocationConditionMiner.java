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
package org.eclipse.passage.lic.internal.equinox.conditions;

import java.nio.file.Path;
import java.util.Map;

import org.eclipse.passage.lic.base.conditions.BasePathConditionMiner;
import org.eclipse.passage.lic.equinox.io.EquinoxPaths;
import org.eclipse.passage.lic.runtime.LicensingReporter;
import org.eclipse.passage.lic.runtime.conditions.ConditionMiner;
import org.eclipse.passage.lic.runtime.conditions.ConditionTransport;
import org.eclipse.passage.lic.runtime.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.runtime.io.StreamCodecRegistry;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

@Component
public class InstallLocationConditionMiner extends BasePathConditionMiner implements ConditionMiner {

	@Reference
	@Override
	public void bindLicensingReporter(LicensingReporter reporter) {
		super.bindLicensingReporter(reporter);
	}

	@Override
	public void unbindLicensingReporter(LicensingReporter reporter) {
		super.unbindLicensingReporter(reporter);
	}

	@Reference
	@Override
	public void bindKeyKeeperRegistry(KeyKeeperRegistry registry) {
		super.bindKeyKeeperRegistry(registry);
	}

	@Override
	public void unbindKeyKeeperRegistry(KeyKeeperRegistry registry) {
		super.unbindKeyKeeperRegistry(registry);
	}

	@Reference
	@Override
	public void bindStreamCodecRegistry(StreamCodecRegistry registry) {
		super.bindStreamCodecRegistry(registry);
	}

	@Override
	public void unbindStreamCodecRegistry(StreamCodecRegistry registry) {
		super.unbindStreamCodecRegistry(registry);
	}

	@Override
	@Reference(cardinality = ReferenceCardinality.AT_LEAST_ONE)
	public void bindConditionTransport(ConditionTransport transport,
			Map<String, Object> properties) {
		super.bindConditionTransport(transport, properties);
	}

	@Override
	public void unbindConditionTransport(ConditionTransport transport,
			Map<String, Object> properties) {
		super.unbindConditionTransport(transport, properties);
	}

	@Override
	protected Path getBasePath() {
		return EquinoxPaths.resolveInstallBasePath();
	}

}
