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

import org.eclipse.passage.lic.base.conditions.PathConditionMiner;
import org.eclipse.passage.lic.base.io.LicensingPaths;
import org.eclipse.passage.lic.runtime.LicensingReporter;
import org.eclipse.passage.lic.runtime.conditions.ConditionMiner;
import org.eclipse.passage.lic.runtime.conditions.ConditionTransport;
import org.eclipse.passage.lic.runtime.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.runtime.io.StreamCodecRegistry;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

@Component(service = ConditionMiner.class)
public class UserHomeConditionMiner extends PathConditionMiner {

	@Reference(cardinality = ReferenceCardinality.OPTIONAL)
	@Override
	public void bindLicensingReporter(LicensingReporter reporter) {
		super.bindLicensingReporter(reporter);
	}

	@Override
	public void unbindLicensingReporter(LicensingReporter reporter) {
		super.unbindLicensingReporter(reporter);
	}

	@Reference(cardinality = ReferenceCardinality.OPTIONAL)
	@Override
	public void bindKeyKeeperRegistry(KeyKeeperRegistry registry) {
		super.bindKeyKeeperRegistry(registry);
	}

	@Override
	public void unbindKeyKeeperRegistry(KeyKeeperRegistry registry) {
		super.unbindKeyKeeperRegistry(registry);
	}

	@Reference(cardinality = ReferenceCardinality.OPTIONAL)
	@Override
	public void bindStreamCodecRegistry(StreamCodecRegistry registry) {
		super.bindStreamCodecRegistry(registry);
	}

	@Override
	public void unbindStreamCodecRegistry(StreamCodecRegistry registry) {
		super.unbindStreamCodecRegistry(registry);
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	@Override
	public void bindConditionTransport(ConditionTransport transport, Map<String, Object> properties) {
		super.bindConditionTransport(transport, properties);
	}

	@Override
	public void unbindConditionTransport(ConditionTransport transport, Map<String, Object> properties) {
		super.unbindConditionTransport(transport, properties);
	}

	@Override
	protected Path getBasePath() {
		String property = System.getProperty("user.home"); //$NON-NLS-1$
		String value = new File(property).getAbsolutePath();
		return Paths.get(value, LicensingPaths.FOLDER_LICENSING_BASE);
	}

}
