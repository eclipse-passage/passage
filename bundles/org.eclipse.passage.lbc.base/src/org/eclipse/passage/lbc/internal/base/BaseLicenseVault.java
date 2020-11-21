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
package org.eclipse.passage.lbc.internal.base;

import java.util.Collection;

import org.eclipse.passage.lbc.internal.api.BackendLicenseVault;
import org.eclipse.passage.lbc.internal.api.BackendServerConfiguration;
import org.eclipse.passage.lbc.internal.api.ProductLicensesRequest;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.internal.base.conditions.mining.PersonalLicenseMiningEquipment;

/**
 * @since 1.0
 */
public final class BaseLicenseVault implements BackendLicenseVault {

	private final MinedConditions miner;

	public BaseLicenseVault(BackendServerConfiguration configuration) {
		this.miner = new VaultMinedConditions(
				new PersonalLicenseMiningEquipment(configuration.keyKeepers(), configuration.codecs(), configuration.transports()));
	}

	@Override
	public ServiceInvocationResult<Collection<ConditionPack>> availableLicenses(ProductLicensesRequest request) {
		return miner.all(request.product());
	}

}
