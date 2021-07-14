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
package org.eclipse.passage.lbc.internal.fls.seal;

import java.util.Arrays;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.api.conditions.mining.MinedConditionsRegistry;
import org.eclipse.passage.lic.api.registry.Registry;
import org.eclipse.passage.lic.base.conditions.mining.UserHomeResidentConditions;
import org.eclipse.passage.lic.base.registry.ReadOnlyRegistry;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.acquire.LicenseAcquisitionService;
import org.eclipse.passage.lic.internal.api.acquire.LicenseAcquisitionServicesRegistry;
import org.eclipse.passage.lic.internal.base.acquire.UserHomeLicenseAcquisitionService;
import org.eclipse.passage.lic.internal.equinox.acquire.ConfigurationLicenseAcquisitionService;
import org.eclipse.passage.lic.internal.equinox.acquire.InstallationLicenseAcquisitionService;
import org.eclipse.passage.lic.internal.equinox.conditions.ConfigurationResidentConditions;
import org.eclipse.passage.lic.internal.equinox.conditions.InstallationResidentConditions;
import org.osgi.framework.FrameworkUtil;

@SuppressWarnings("restriction")
final class SealedAccessCycleConfiguration extends BaseAccessCycleConfiguration {

	private final Registry<ConditionMiningTarget, MinedConditions> conditions;
	private final Registry<ConditionMiningTarget, LicenseAcquisitionService> acquirers;

	SealedAccessCycleConfiguration(Supplier<LicensedProduct> product) {
		super(product, () -> FrameworkUtil.getBundle(SealedAccessCycleConfiguration.class));
		conditions = new ReadOnlyRegistry<>(Arrays.asList(//
				new UserHomeResidentConditions(miningEquipment()), //
				new InstallationResidentConditions(miningEquipment()), //
				new ConfigurationResidentConditions(miningEquipment())//
		));
		acquirers = new ReadOnlyRegistry<>(Arrays.asList(//
				new UserHomeLicenseAcquisitionService(), //
				new InstallationLicenseAcquisitionService(), //
				new ConfigurationLicenseAcquisitionService()//
		));
	}

	@Override
	public MinedConditionsRegistry conditionMiners() {
		return () -> conditions;
	}

	@Override
	public LicenseAcquisitionServicesRegistry acquirers() {
		return () -> acquirers;
	}

}
