/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.internal.equinox.conditions;

import java.nio.file.Path;
import java.util.function.Consumer;

import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.base.conditions.mining.LocalConditions;
import org.eclipse.passage.lic.internal.base.conditions.mining.MiningEquipment;
import org.eclipse.passage.lic.internal.base.io.LicensingFolder;
import org.eclipse.passage.lic.internal.equinox.io.ConfigurationPath;

/**
 * Reads all the conditions containing in license files under {@code .passage}
 * settings folder located under the product {@code configuration} directory
 * (supplied by the eclipse platform).
 */
@SuppressWarnings("restriction")
public final class ConfigurationResidentConditions extends LocalConditions {

	public ConfigurationResidentConditions(MiningEquipment equipment, Consumer<LicensingException> handler) {
		super(new StringServiceId("installation-conditions"), equipment, handler); //$NON-NLS-1$
	}

	@Override
	protected Path base() {
		return new LicensingFolder(new ConfigurationPath()).get();
	}

}
