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
package org.eclipse.passage.lbc.internal.base.chains;

import org.eclipse.passage.lbc.internal.api.Chain;
import org.eclipse.passage.lbc.internal.api.CheckAvailabilityRequest;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;

public class CanTakeConditionChain implements Chain<Boolean, CheckAvailabilityRequest> {

	@Override
	public ServiceInvocationResult<Boolean> execute(CheckAvailabilityRequest request) {
		return new BaseServiceInvocationResult<Boolean>(true);
	}

}
