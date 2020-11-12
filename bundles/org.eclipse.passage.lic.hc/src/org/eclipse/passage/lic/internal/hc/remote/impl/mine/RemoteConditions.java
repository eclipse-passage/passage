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
import java.util.function.BinaryOperator;

import org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionMiningTarget;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransportRegistry;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.base.SumOfCollections;
import org.eclipse.passage.lic.internal.hc.remote.ResponseHandler;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteRequest;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteServiceData;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteServiceData.Bulk;
import org.eclipse.passage.lic.internal.hc.remote.impl.ServiceEvery;

public final class RemoteConditions extends ServiceEvery<Collection<ConditionPack>, RemoteServiceData.Bulk>
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
	protected RemoteRequest request(Bulk params, FloatingLicenseAccess access) {
		return new RemoteConditionsRequest(params.product(), access);
	}

	@Override
	protected ResponseHandler<Collection<ConditionPack>> handler(FloatingLicenseAccess access) {
		return new DecryptedConditions(transports, access.getServer());
	}

	@Override
	protected BinaryOperator<Collection<ConditionPack>> sum() {
		return new SumOfCollections<>();
	}

	@Override
	protected Collection<ConditionPack> noResult() {
		return Collections.emptyList();
	}

}
