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
package org.eclipse.passage.seal.internal.demo;

import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.AccessCycleConfiguration;
import org.eclipse.passage.lic.internal.api.conditions.mining.MiningEquipment;
import org.eclipse.passage.lic.internal.base.conditions.mining.PersonalLicenseMiningEquipment;

final class MiningEquipmentConfigured implements Supplier<MiningEquipment> {

	private final AccessCycleConfiguration configuration;

	MiningEquipmentConfigured(AccessCycleConfiguration configuration) {
		this.configuration = configuration;
	}

	@Override
	public MiningEquipment get() {
		return new PersonalLicenseMiningEquipment(//
				configuration.keyKeepers(), //
				configuration.codecs(), //
				configuration.transports());
	}

}
