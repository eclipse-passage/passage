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
package org.eclipse.passage.lbc.chains;

import java.util.Optional;
import java.util.function.Function;

import org.eclipse.passage.lbc.chains.transform.ConditionOfRequest;
import org.eclipse.passage.lbc.internal.api.RequestedCondition;
import org.eclipse.passage.lbc.internal.api.persistence.PersistableLicense;
import org.eclipse.passage.lbc.internal.base.BackendAction;
import org.eclipse.passage.lbc.internal.base.persistence.LockFolder;
import org.eclipse.passage.lbc.internal.base.troubles.ConditionEntryNotFound;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;

@SuppressWarnings("restriction")
public final class CanTake extends Operation<RequestedCondition, Boolean> {

	public CanTake(LockFolder folder) {
		super(new ConditionOfRequest(), new Encode(), folder);
	}

	@Override
	public ServiceInvocationResult<Boolean> execute(RequestedCondition request) {
		Optional<PersistableLicense> license = license(request.condition());
		if (license.isPresent()) {
			return new BaseServiceInvocationResult<Boolean>(license.get().get().takeable());
		} else {
			return new BaseServiceInvocationResult<>(
					new Trouble(new ConditionEntryNotFound(), request.condition().identifier()));
		}
	}

	private final static class Encode
			implements Function<ServiceInvocationResult<Boolean>, ServiceInvocationResult<String>> {

		@Override
		public ServiceInvocationResult<String> apply(ServiceInvocationResult<Boolean> result) {
			// TODO: invent a protocol to transfer condition availability data
			if (result.data().isPresent()) {
				return new BaseServiceInvocationResult<>("{ result: \"" + result.data().get() + "\"}"); //$NON-NLS-1$ //$NON-NLS-2$
			}
			return new BaseServiceInvocationResult<>(
					"{ error: \"" + result.diagnostic().severe().get(0).code().explanation() + "\"}"); //$NON-NLS-1$ //$NON-NLS-2$
		}

	}

	@Override
	public BackendAction action() {
		return new BackendAction.CanTake();
	}

}
