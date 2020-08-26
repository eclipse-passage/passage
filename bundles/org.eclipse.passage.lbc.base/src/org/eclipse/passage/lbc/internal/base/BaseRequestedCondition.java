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

import java.util.Objects;

import org.eclipse.passage.lbc.internal.api.RequestedCondition;
import org.eclipse.passage.lbc.internal.api.Requester;
import org.eclipse.passage.lic.internal.api.conditions.Condition;

/**
 * @since 1.0
 */
public final class BaseRequestedCondition implements RequestedCondition {

	private final Requester requester;
	private final Condition condition;

	public BaseRequestedCondition(Condition condition, Requester requester) {
		Objects.requireNonNull(condition, "BaseRequestedCondition::condition"); //$NON-NLS-1$
		Objects.requireNonNull(requester, "BaseRequestedCondition::requester"); //$NON-NLS-1$
		this.requester = requester;
		this.condition = condition;
	}

	@Override
	public Requester requester() {
		return requester;
	}

	@Override
	public Condition condition() {
		return condition;
	}

}
