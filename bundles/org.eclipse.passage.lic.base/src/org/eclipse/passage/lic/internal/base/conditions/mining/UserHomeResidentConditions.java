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
package org.eclipse.passage.lic.internal.base.conditions.mining;

import java.nio.file.Path;

import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.base.io.LicensingFolder;
import org.eclipse.passage.lic.internal.base.io.UserHomePath;

/**
 * Reads all the conditions containing in license files under
 * {@code user.home}'s {@code .passage} data folder.
 */
public final class UserHomeResidentConditions extends LocalConditions {

	public UserHomeResidentConditions(MiningEquipment equipment) {
		super(new StringServiceId("user-home-conditions"), equipment); //$NON-NLS-1$
	}

	@Override
	protected Path base() {
		return new LicensingFolder(new UserHomePath()).get();
	}

}
