/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.base.conditions;

import java.time.ZonedDateTime;
import java.util.Objects;

import org.eclipse.passage.lic.api.conditions.ValidityPeriodClosed;

/**
 * @since 2.1
 */
public final class BaseValidityPeriodClosed implements ValidityPeriodClosed {

	private final ZonedDateTime from;
	private final ZonedDateTime to;

	public BaseValidityPeriodClosed(ZonedDateTime from, ZonedDateTime to) {
		Objects.requireNonNull(from, "BaseValidityPeriodClosed::from"); //$NON-NLS-1$
		Objects.requireNonNull(to, "BaseValidityPeriodClosed:to"); //$NON-NLS-1$
		if (!from.isBefore(to)) {
			throw new IllegalStateException("BaseValidityPeriodClosed: 'from' must be strictly less than 'to'"); //$NON-NLS-1$
		}
		this.from = from;
		this.to = to;
	}

	@Override
	public ZonedDateTime from() {
		return from;
	}

	@Override
	public ZonedDateTime to() {
		return to;
	}

	@Override
	public boolean valid(ZonedDateTime date) {
		Objects.requireNonNull(date, "BaseValidityPeriodClosed::valid::date"); //$NON-NLS-1$
		return (from.isBefore(date) || date.isEqual(from)) && //
				(date.isBefore(to) || date.isEqual(to));
	}

}
