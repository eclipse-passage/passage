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
import org.eclipse.passage.lic.internal.base.diagnostic.NoSevereErrors;
import org.eclipse.passage.lic.internal.hc.remote.impl.AccessPacks;
import org.eclipse.passage.lic.internal.hc.remote.impl.HttpClient;

public final class RemoteConditions implements MinedConditions {

	private final ConditionTransportRegistry transports;
	private final KeyKeeperRegistry keys;
	private final StreamCodecRegistry codecs;
	private final ConditionMiningTarget target = new ConditionMiningTarget.Remote(); // $NON-NLS-1$

	public RemoteConditions(KeyKeeperRegistry keys, StreamCodecRegistry codecs, ConditionTransportRegistry transports) {
		this.keys = keys;
		this.codecs = codecs;
		this.transports = transports;
	}

	@Override
	public ConditionMiningTarget id() {
		return target;
	}

	@Override
	public ServiceInvocationResult<Collection<ConditionPack>> all(LicensedProduct product) {
		ServiceInvocationResult<Collection<FloatingLicenseAccess>> accesses = accesses(product);
		if (!new NoSevereErrors().test(accesses.diagnostic())) {
			return new BaseServiceInvocationResult<>(accesses.diagnostic());
		}
		return accesses.data().get().stream()//
				.map(access -> conditions(product, access))//
				.reduce(new BaseServiceInvocationResult.Sum<>(new SumOfCollections<>()))//
				.orElse(new BaseServiceInvocationResult<>(Collections.emptyList()));
	}

	private ServiceInvocationResult<Collection<FloatingLicenseAccess>> accesses(LicensedProduct product) {
		return new AccessPacks(product, keys, codecs).get();
	}

	private ServiceInvocationResult<Collection<ConditionPack>> conditions(LicensedProduct product,
			FloatingLicenseAccess access) {
		return new HttpClient<Collection<ConditionPack>>().request(//
				new RemoteConditionsRequest(product, access), //
				new DecryptedConditions(transports, access.getServer()));
	}

}
