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
package org.eclipse.passage.lic.base.conditions.mining;

import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.conditions.mining.MiningEquipment;
import org.eclipse.passage.lic.base.io.ExistingFolder;
import org.eclipse.passage.lic.base.io.LicensingFolder;
import org.eclipse.passage.lic.base.io.PassageFileExtension;
import org.eclipse.passage.lic.base.io.PathFromLicensedProduct;
import org.eclipse.passage.lic.base.io.UserHomePath;

/**
 * Reads all the conditions containing in license files under
 * {@code user.home}'s {@code .passage} data folder.
 * 
 * @since 2.1
 */
public final class UserHomeResidentConditions extends LocalConditions {

	public UserHomeResidentConditions(MiningEquipment equipment) {
		super(new UserHomeMiningTarget().get(), equipment);
	}

	public UserHomeResidentConditions(MiningEquipment equipment, PassageFileExtension scope) {
		super(new UserHomeMiningTarget().get(), equipment, scope);
	}

	@Override
	protected Supplier<Path> base(LicensedProduct product) {
		return new ExistingFolder(//
				new PathFromLicensedProduct(//
						new LicensingFolder(//
								new UserHomePath()), //
						product));
	}

}
