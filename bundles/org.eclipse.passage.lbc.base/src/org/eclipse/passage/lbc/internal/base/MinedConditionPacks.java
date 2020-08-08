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
import java.util.function.Function;

import org.eclipse.passage.lbc.internal.api.BackendLicenseVault;
import org.eclipse.passage.lbc.internal.api.MiningRequest;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;

/**
 * @since 1.0
 */
public final class MinedConditionPacks implements Function<MiningRequest, Collection<ConditionPack>> {

	private final BackendLicenseVault licenseVault;

	public MinedConditionPacks(BackendLicenseVault licenseVault) {
		this.licenseVault = licenseVault;
	}

	@Override
	public Collection<ConditionPack> apply(MiningRequest request) {
		return licenseVault.availableLicenses(request);
	}

}
