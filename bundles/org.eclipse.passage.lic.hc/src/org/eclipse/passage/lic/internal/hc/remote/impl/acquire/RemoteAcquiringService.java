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
package org.eclipse.passage.lic.internal.hc.remote.impl.acquire;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.acquire.GrantAcqisition;
import org.eclipse.passage.lic.internal.api.acquire.LicenseAcquisitionService;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionMiningTarget;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteServiceData;

public final class RemoteAcquiringService implements LicenseAcquisitionService {

	private final KeyKeeperRegistry keys;
	private final StreamCodecRegistry codecs;
	private final ConditionMiningTarget target = new ConditionMiningTarget.Remote();

	protected RemoteAcquiringService(KeyKeeperRegistry keys, StreamCodecRegistry codecs) {
		this.keys = keys;
		this.codecs = codecs;
	}

	@Override
	public ConditionMiningTarget id() {
		return target;
	}

	@Override
	public ServiceInvocationResult<GrantAcqisition> acquire(LicensedProduct product, String feature) {
		return new RemoteAcquire(keys, codecs).request(new RemoteServiceData.OfFeature(product, feature));
	}

	@Override
	public ServiceInvocationResult<Boolean> release(LicensedProduct product, GrantAcqisition acquisition) {
		return null; // TODO: YTBD
	}

}
