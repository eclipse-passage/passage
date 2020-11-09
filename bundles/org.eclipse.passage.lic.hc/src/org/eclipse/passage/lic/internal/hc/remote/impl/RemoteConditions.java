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
package org.eclipse.passage.lic.internal.hc.remote.impl;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionMiningTarget;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransportRegistry;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.api.io.KeyKeeper;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodec;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.SumOfCollections;
import org.eclipse.passage.lic.internal.base.diagnostic.NoSevereErrors;
import org.eclipse.passage.lic.internal.base.diagnostic.code.ServiceCannotOperate;
import org.eclipse.passage.lic.internal.hc.i18n.MinerMessages;

public final class RemoteConditions implements MinedConditions {

	private final ConditionTransportRegistry transports;
	private final KeyKeeperRegistry keys;
	private final StreamCodecRegistry codecs;
	private final ConditionMiningTarget id = new ConditionMiningTarget.Remote(); // $NON-NLS-1$

	public RemoteConditions(KeyKeeperRegistry keys, StreamCodecRegistry codecs, ConditionTransportRegistry transports) {
		this.keys = keys;
		this.codecs = codecs;
		this.transports = transports;
	}

	@Override
	public ConditionMiningTarget id() {
		return id;
	}

	@Override
	public ServiceInvocationResult<Collection<ConditionPack>> all(LicensedProduct product) {
		KeyKeeper key;
		StreamCodec codec;
		try {
			codec = codec(product);
			key = key(product);
		} catch (LicensingException e) {
			return new BaseServiceInvocationResult<>(new Trouble(new ServiceCannotOperate(),
					MinerMessages.RemoteConditions_insfficient_configuration, e));
		}

		ServiceInvocationResult<Collection<FloatingLicenseAccess>> accesses = //
				new AccessPacks(product, key, codec).get();
		if (!new NoSevereErrors().test(accesses.diagnostic())) {
			return new BaseServiceInvocationResult<>(accesses.diagnostic());
		}

		return accesses.data().get().stream()//
				.map(access -> conditions(product, access))//
				.reduce(new BaseServiceInvocationResult.Sum<>(new SumOfCollections<>()))//
				.orElse(new BaseServiceInvocationResult<>(Collections.emptyList()));
	}

	private ServiceInvocationResult<Collection<ConditionPack>> conditions(LicensedProduct product,
			FloatingLicenseAccess access) {
		return new HttpClient().remoteConditions(//
				new RemoteConditionsRequest(product, access), //
				new DecryptedConditions(transports, access.getServer()));
	}

	private KeyKeeper key(LicensedProduct product) throws LicensingException {
		if (!keys.get().hasService(product)) {
			throw new LicensingException(String.format(MinerMessages.RemoteConditions_no_key_keeper, product));
		}
		return keys.get().service(product);
	}

	private StreamCodec codec(LicensedProduct product) throws LicensingException {
		if (!codecs.get().hasService(product)) {
			throw new LicensingException(String.format(MinerMessages.RemoteConditions_no_stream_codec, product));
		}
		return codecs.get().service(product);
	}

}
