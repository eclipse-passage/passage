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

import java.util.Optional;
import java.util.function.Function;

import org.eclipse.passage.lbc.internal.api.RequestedCondition;
import org.eclipse.passage.lbc.internal.api.persistence.Deserialization;
import org.eclipse.passage.lbc.internal.api.persistence.LoadedLicense;
import org.eclipse.passage.lbc.internal.api.persistence.PersistableLicense;
import org.eclipse.passage.lbc.internal.base.troubles.ConditionEntryNotFound;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.net.LicensingAction;

public final class CanTake extends Operation<RequestedCondition, Boolean> {

	public CanTake(Deserialization<Condition> deserialization, LoadedLicense load) {
		super(new ConditionOfRequest(deserialization), new Encode(), load);
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
	public LicensingAction action() {
		return new LicensingAction(new ConditionAction.Of("can-take")); //$NON-NLS-1$
	}

}
