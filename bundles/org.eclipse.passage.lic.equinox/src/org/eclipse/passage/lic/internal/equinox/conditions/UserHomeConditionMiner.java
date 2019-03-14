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

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.eclipse.passage.lic.base.conditions.BasePathConditionMiner;
import org.eclipse.passage.lic.base.io.LicensingPaths;
import org.eclipse.passage.lic.runtime.LicensingReporter;
import org.eclipse.passage.lic.runtime.conditions.ConditionMiner;
import org.eclipse.passage.lic.runtime.conditions.LicensingConditionTransport;
import org.eclipse.passage.lic.runtime.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.runtime.io.StreamCodecRegistry;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

@Component
public class UserHomeConditionMiner extends BasePathConditionMiner implements ConditionMiner {

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
	public void bindConditionDescriptorTransport(LicensingConditionTransport transport,
			Map<String, Object> properties) {
		super.bindConditionDescriptorTransport(transport, properties);
	}

	@Override
	public void unbindConditionDescriptorTransport(LicensingConditionTransport transport,
			Map<String, Object> properties) {
		super.unbindConditionDescriptorTransport(transport, properties);
	}

	@Override
	protected Path getBasePath() {
		String property = System.getProperty("user.home"); //$NON-NLS-1$
		String value = new File(property).getAbsolutePath();
		return Paths.get(value, LicensingPaths.FOLDER_LICENSING_BASE);
	}

}
