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
package org.eclipse.passage.lbc.internal.base.tobemoved.mine;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.internal.api.conditions.mining.MiningEquipment;
import org.eclipse.passage.lic.internal.api.conditions.mining.MiningTool;

final class ReassemblingMiningEquipment implements MiningEquipment {

	private final String user;

	ReassemblingMiningEquipment(String user) {
		this.user = user;
	}

	@Override
	public MiningTool tool(LicensedProduct product, ConditionMiningTarget miner) throws LicensingException {
		return new ReassemblingMiningTool(product, user, miner);
	}

}
