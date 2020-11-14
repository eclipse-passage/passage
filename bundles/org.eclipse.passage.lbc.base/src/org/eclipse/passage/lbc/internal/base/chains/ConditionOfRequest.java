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

import java.util.function.Function;

import org.eclipse.passage.lbc.internal.api.BackendLicensingRequest;
import org.eclipse.passage.lbc.internal.api.RequestedCondition;
import org.eclipse.passage.lbc.internal.base.BaseRequestedCondition;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.Condition;

@Deprecated
// FIXME: to be reimplemented
public final class ConditionOfRequest implements Function<BackendLicensingRequest, RequestedCondition> {

	private final Function<String, ServiceInvocationResult<Condition>> deserialization;

	public ConditionOfRequest(Function<String, ServiceInvocationResult<Condition>> deserialization) {
		this.deserialization = deserialization;
	}

	@Override
	public RequestedCondition apply(BackendLicensingRequest request) {
		return new BaseRequestedCondition(deserialization.apply(request.body()).data().get(), request.requester());
	}

}
