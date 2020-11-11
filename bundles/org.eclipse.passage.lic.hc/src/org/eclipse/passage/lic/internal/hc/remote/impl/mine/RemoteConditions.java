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
package org.eclipse.passage.lic.internal.hc.remote.impl.mine;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionMiningTarget;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransportRegistry;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.SumOfCollections;
import org.eclipse.passage.lic.internal.hc.remote.impl.HttpClient;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteService;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteServiceData;

public final class RemoteConditions extends RemoteService<Collection<ConditionPack>, RemoteServiceData.Bulk>
		implements MinedConditions {

	private final ConditionTransportRegistry transports;
	private final ConditionMiningTarget target = new ConditionMiningTarget.Remote();

	public RemoteConditions(KeyKeeperRegistry keys, StreamCodecRegistry codecs, ConditionTransportRegistry transports) {
		super(keys, codecs);
		this.transports = transports;
	}

	@Override
	public ConditionMiningTarget id() {
		return target;
	}

	@Override
	public ServiceInvocationResult<Collection<ConditionPack>> all(LicensedProduct product) {
		return request(new RemoteServiceData.Bulk(product));
	}

	@Override
	protected ServiceInvocationResult<Collection<ConditionPack>> withServers(RemoteServiceData.Bulk params,
			Collection<FloatingLicenseAccess> servers) {
		return servers.stream()//
				.map(access -> conditions(params.product(), access))//
				.reduce(new BaseServiceInvocationResult.Sum<>(new SumOfCollections<>()))//
				.orElse(new BaseServiceInvocationResult<>(Collections.emptyList()));
	}

	private ServiceInvocationResult<Collection<ConditionPack>> conditions(LicensedProduct product,
			FloatingLicenseAccess access) {
		return new HttpClient<Collection<ConditionPack>>().request(//
				new RemoteConditionsRequest(product, access), //
				new DecryptedConditions(transports, access.getServer()));
	}

}
