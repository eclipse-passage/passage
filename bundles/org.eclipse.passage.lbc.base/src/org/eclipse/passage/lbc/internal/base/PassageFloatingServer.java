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
package org.eclipse.passage.lbc.internal.base;

import org.eclipse.passage.lbc.internal.api.BackendFloatingServer;
import org.eclipse.passage.lbc.internal.api.CheckRequest;
import org.eclipse.passage.lbc.internal.api.ReleaseRequest;
import org.eclipse.passage.lbc.internal.api.TakeRequest;
import org.eclipse.passage.lbc.internal.base.chains.AcquireConditionChain;
import org.eclipse.passage.lbc.internal.base.chains.CanTakeConditionChain;
import org.eclipse.passage.lbc.internal.base.chains.ReleaseConditionChain;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;

/**
 * @since 1.0
 */
public final class PassageFloatingServer implements BackendFloatingServer {

	@Override
	public ServiceInvocationResult<Boolean> canTake(CheckRequest request) {
		return new CanTakeConditionChain().apply(request);
	}

	@Override
	public ServiceInvocationResult<ExaminationCertificate> take(TakeRequest request) {
		return new AcquireConditionChain().apply(request);
	}

	@Override
	public ServiceInvocationResult<Boolean> release(ReleaseRequest request) {
		return new ReleaseConditionChain().apply(request);
	}

}
