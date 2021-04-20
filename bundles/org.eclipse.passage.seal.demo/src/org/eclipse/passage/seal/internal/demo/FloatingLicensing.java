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
package org.eclipse.passage.seal.internal.demo;

import org.eclipse.passage.lic.internal.api.acquire.LicenseAcquisitionService;
import org.eclipse.passage.lic.internal.api.acquire.LicenseAcquisitionServicesRegistry;
import org.eclipse.passage.lic.internal.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransportRegistry;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditionsRegistry;
import org.eclipse.passage.lic.internal.api.io.Hashes;
import org.eclipse.passage.lic.internal.api.io.HashesRegistry;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.base.io.MD5Hashes;
import org.eclipse.passage.lic.internal.base.registry.ReadOnlyRegistry;
import org.eclipse.passage.lic.internal.hc.remote.impl.Equipment;
import org.eclipse.passage.lic.internal.hc.remote.impl.NetConnection;
import org.eclipse.passage.lic.internal.hc.remote.impl.acquire.RemoteAcquisitionService;
import org.eclipse.passage.lic.internal.hc.remote.impl.mine.RemoteConditions;

@SuppressWarnings("restriction")
final class FloatingLicensing implements LicensingDirection {

	private final Registry<ConditionMiningTarget, MinedConditions> conditions;
	private final Registry<ConditionMiningTarget, LicenseAcquisitionService> acquirers;
	private final Registry<StringServiceId, Hashes> hashes;

	FloatingLicensing(KeyKeeperRegistry keys, StreamCodecRegistry codecs, ConditionTransportRegistry transports) {
		this.hashes = new ReadOnlyRegistry<>(new MD5Hashes());
		this.conditions = new ReadOnlyRegistry<>(
				new RemoteConditions<NetConnection>(equipment(keys, codecs, transports)));
		this.acquirers = new ReadOnlyRegistry<>(
				new RemoteAcquisitionService<NetConnection>(equipment(keys, codecs, transports)));
	}

	@Override
	public MinedConditionsRegistry conditionMiners() {
		return () -> conditions;
	}

	@Override
	public LicenseAcquisitionServicesRegistry acquirers() {
		return () -> acquirers;
	}

	@Override
	public HashesRegistry hashes() {
		return () -> hashes;
	}

	private Equipment equipment(KeyKeeperRegistry keys, StreamCodecRegistry codecs,
			ConditionTransportRegistry transports) {
		return new Equipment(keys, codecs, transports, hashes());
	}

}
