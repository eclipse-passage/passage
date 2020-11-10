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

import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.acquire.LicenseAcquisitionService;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionMiningTarget;

public final class RemoteAcquiringService implements LicenseAcquisitionService {

	private final ConditionMiningTarget target = new ConditionMiningTarget.Remote();

	@Override
	public ConditionMiningTarget id() {
		return target;
	}

	@Override
	public ServiceInvocationResult<Boolean> acquire() {

		return null;
	}

	@Override
	public ServiceInvocationResult<Boolean> release() {
		return null;
	}

}
