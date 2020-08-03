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
package org.eclipse.passage.lic.internal.base.tests.conditions.mining;

import java.nio.file.Path;

import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.base.conditions.mining.LocalConditions;
import org.eclipse.passage.lic.internal.base.conditions.mining.MiningEquipment;

@SuppressWarnings("restriction")
final class TempFolderResidentConditions extends LocalConditions {

	private final Path root;

	TempFolderResidentConditions(Path root, MiningEquipment equipment) {
		super(new StringServiceId("temp-folder-conditions"), equipment); //$NON-NLS-1$
		this.root = root;
	}

	@Override
	protected Path base() {
		return root;
	}

}
