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

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.acquire.LicenseAcquisitionService;
import org.eclipse.passage.lic.internal.api.acquire.LicenseAcquisitionServicesRegistry;
import org.eclipse.passage.lic.internal.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditionsRegistry;
import org.eclipse.passage.lic.internal.api.io.Hashes;
import org.eclipse.passage.lic.internal.api.io.HashesRegistry;
import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.base.io.MD5Hashes;
import org.eclipse.passage.lic.internal.base.registry.ReadOnlyRegistry;
import org.eclipse.passage.lic.internal.hc.remote.impl.NetConnection;
import org.eclipse.passage.lic.internal.hc.remote.impl.acquire.RemoteAcquisitionService;
import org.eclipse.passage.lic.internal.hc.remote.impl.mine.RemoteConditions;
import org.osgi.framework.FrameworkUtil;

@SuppressWarnings("restriction")
final class RemoteAccessCycleConfiguration extends BaseAccessCycleConfiguration {

	private final Registry<ConditionMiningTarget, MinedConditions> conditions;
	private final Registry<ConditionMiningTarget, LicenseAcquisitionService> acquirers;
	private final Registry<StringServiceId, Hashes> hashes;

	RemoteAccessCycleConfiguration(Supplier<LicensedProduct> product) {
		super(product, () -> FrameworkUtil.getBundle(RemoteAccessCycleConfiguration.class));
		conditions = new ReadOnlyRegistry<>(new RemoteConditions<NetConnection>(keyKeepers(), codecs(), transports()));
		acquirers = new ReadOnlyRegistry<>(new RemoteAcquisitionService<NetConnection>(keyKeepers(), codecs()));
		hashes = new ReadOnlyRegistry<>(new MD5Hashes());
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

}
