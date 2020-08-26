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

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.passage.lbc.internal.api.RequestedCertificate;
import org.eclipse.passage.lbc.internal.api.persistence.PersistableLicense;
import org.eclipse.passage.lbc.internal.base.SatisfiedRequirements;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;

@SuppressWarnings("restriction")
public final class Release extends ConditionInteraction<RequestedCertificate, Map<Condition, Boolean>> {

	public Release(Function<Condition, Optional<PersistableLicense>> find) {
		super(find);
	}

	@Override
	public ServiceInvocationResult<Map<Condition, Boolean>> apply(RequestedCertificate request) {
		Map<Condition, Boolean> result = new SatisfiedRequirements().apply(request.certificate()).entrySet().stream() //
				.map(Map.Entry::getValue) //
				.map(Permission::condition) //
				.collect(Collectors.toMap(Function.identity(), this::release));
		return new BaseServiceInvocationResult<>(result);
	}

	private boolean release(Condition condition) {
		final Optional<PersistableLicense> found = license(condition);
		if (found.isPresent()) {
			PersistableLicense persistable = found.get();
			if (!persistable.get().releasable()) {
				return false;
			}
			return persistable.releaseOne();
		}
		return false;
	}

}
