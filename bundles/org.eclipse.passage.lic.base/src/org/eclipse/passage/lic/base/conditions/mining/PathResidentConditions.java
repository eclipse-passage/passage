/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.api.conditions.mining.MiningEquipment;
import org.eclipse.passage.lic.base.io.PathFromLicensedProduct;

/**
 * 
 * @since 2.1
 */
public final class PathResidentConditions extends LocalConditions {

	private final Path root;

	public PathResidentConditions(Path root, MiningEquipment equipment) {
		super(new ConditionMiningTarget.Local().child(root.toAbsolutePath().toString()), equipment);
		this.root = root;
	}

	@Override
	protected Supplier<Path> base(LicensedProduct product) {
		return new PathFromLicensedProduct(() -> root, product);
	}

}
