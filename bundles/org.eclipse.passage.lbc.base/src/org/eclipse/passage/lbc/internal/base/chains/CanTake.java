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
import java.util.stream.Stream;

import org.eclipse.passage.lbc.internal.api.RequestedCondition;
import org.eclipse.passage.lbc.internal.api.persistence.PersistableLicense;
import org.eclipse.passage.lbc.internal.base.troubles.ConditionEntryNotFound;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;

@SuppressWarnings("restriction")
public final class CanTake implements Function<RequestedCondition, ServiceInvocationResult<Boolean>> {

	private final Function<Condition, Optional<PersistableLicense>> find;

	public CanTake(Function<Condition, Optional<PersistableLicense>> find) {
		this.find = find;
	}

	@Override
	public ServiceInvocationResult<Boolean> apply(RequestedCondition request) {
		Optional<PersistableLicense> license = license(request.condition());
		if (license.isPresent()) {
			return new BaseServiceInvocationResult<Boolean>(license.get().get().takeable());
		} else {
			return new BaseServiceInvocationResult<>(
					new Trouble(new ConditionEntryNotFound(), request.condition().identifier()));
		}
	}

	private Optional<PersistableLicense> license(Condition request) {
		return Stream.of(request) //
				.map(find) //
				.findAny().get();
	}

}
