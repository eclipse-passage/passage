/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.execute;

import java.util.Arrays;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.acquire.LicenseAcquisitionService;
import org.eclipse.passage.lic.api.acquire.LicenseAcquisitionServicesRegistry;
import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.api.conditions.mining.MinedConditionsRegistry;
import org.eclipse.passage.lic.api.conditions.mining.MiningEquipment;
import org.eclipse.passage.lic.api.io.Hashes;
import org.eclipse.passage.lic.api.io.HashesRegistry;
import org.eclipse.passage.lic.api.registry.Registry;
import org.eclipse.passage.lic.api.registry.StringServiceId;
import org.eclipse.passage.lic.base.acquire.UserHomeLicenseAcquisitionService;
import org.eclipse.passage.lic.base.conditions.mining.UserHomeResidentConditions;
import org.eclipse.passage.lic.base.registry.ReadOnlyRegistry;
import org.eclipse.passage.lic.equinox.acquire.ConfigurationLicenseAcquisitionService;
import org.eclipse.passage.lic.equinox.acquire.InstallationLicenseAcquisitionService;
import org.eclipse.passage.lic.equinox.conditions.ConfigurationResidentConditions;
import org.eclipse.passage.lic.equinox.conditions.InstallationResidentConditions;

final class PersonalLicensing implements LicensingDirection {

	private final Registry<ConditionMiningTarget, MinedConditions> conditions;
	private final Registry<ConditionMiningTarget, LicenseAcquisitionService> acquirers;

	PersonalLicensing(Supplier<MiningEquipment> equipment) {
		this.conditions = new ReadOnlyRegistry<>(Arrays.asList(//
				new UserHomeResidentConditions(equipment.get()), //
				new InstallationResidentConditions(equipment.get()), //
				new ConfigurationResidentConditions(equipment.get())//
		));
		this.acquirers = new ReadOnlyRegistry<>(Arrays.asList(//
				new UserHomeLicenseAcquisitionService(), //
				new InstallationLicenseAcquisitionService(), //
				new ConfigurationLicenseAcquisitionService()//
		));
	}

	@Override
	public final MinedConditionsRegistry conditionMiners() {
		return () -> conditions;
	}

	@Override
	public final LicenseAcquisitionServicesRegistry acquirers() {
		return () -> acquirers;
	}

	@Override
	public final HashesRegistry hashes() {
		return () -> new ReadOnlyRegistry<StringServiceId, Hashes>();
	}

}
