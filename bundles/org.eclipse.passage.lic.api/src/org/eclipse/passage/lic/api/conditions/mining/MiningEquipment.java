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
package org.eclipse.passage.lic.api.conditions.mining;

import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;

/**
 * @since 1.1
 */
public interface MiningEquipment {

	MiningTool tool(LicensedProduct product, ConditionMiningTarget miner) throws LicensingException;

}
