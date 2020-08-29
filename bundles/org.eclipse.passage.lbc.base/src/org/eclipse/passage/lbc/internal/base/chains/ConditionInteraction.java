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

import org.eclipse.passage.lbc.internal.api.persistence.PersistableLicense;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.Condition;

public abstract class ConditionInteraction<T, U> implements Function<T, ServiceInvocationResult<U>> {

	private final Function<Condition, Optional<PersistableLicense>> find;

	public ConditionInteraction(Function<Condition, Optional<PersistableLicense>> find) {
		this.find = find;
	}

	protected final Optional<PersistableLicense> license(Condition request) {
		return find.apply(request);
	}

}
