/*******************************************************************************
 * Copyright (c) 2020, 2022 ArSysOp
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
package org.eclipse.passage.lic.equinox.conditions;

import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.conditions.mining.MiningEquipment;
import org.eclipse.passage.lic.base.conditions.mining.LocalConditions;
import org.eclipse.passage.lic.base.io.ExistingFolder;
import org.eclipse.passage.lic.base.io.LicensingFolder;
import org.eclipse.passage.lic.base.io.PathFromLicensedProduct;
import org.eclipse.passage.lic.equinox.io.ConfigurationPath;

/**
 * Reads all the conditions containing in license files under {@code .passage}
 * settings folder located under the product {@code configuration} directory
 * (supplied by the eclipse platform).
 * 
 * @since 2.1
 */
public final class ConfigurationResidentConditions extends LocalConditions {

	public ConfigurationResidentConditions(MiningEquipment equipment) {
		super(new ConfigurationMiningTarget().get(), equipment);
	}

	@Override
	protected Supplier<Path> base(LicensedProduct product) {
		return new ExistingFolder(//
				new PathFromLicensedProduct(//
						new LicensingFolder(//
								new ConfigurationPath()), //
						product));
	}

}
