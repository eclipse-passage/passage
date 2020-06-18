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
package org.eclipse.passage.lic.internal.base.conditions;

import java.util.Date;
import java.util.Objects;

import org.eclipse.passage.lic.internal.api.conditions.ValidityPeriodClosed;

@SuppressWarnings("restriction")
public final class BaseValidityPeriodClosed implements ValidityPeriodClosed {

	private final Date from;
	private final Date to;

	public BaseValidityPeriodClosed(Date from, Date to) {
		Objects.requireNonNull(from, "BaseValidityPeriodClosed::from"); //$NON-NLS-1$
		Objects.requireNonNull(to, "BaseValidityPeriodClosed:to"); //$NON-NLS-1$
		if (!from.before(to)) {
			throw new IllegalStateException("BaseValidityPeriodClosed: 'from' must be strictly less than 'to'"); //$NON-NLS-1$
		}
		this.from = from;
		this.to = to;
	}

	@Override
	public Date from() {
		return new Date(from.getTime());
	}

	@Override
	public Date to() {
		return new Date(to.getTime());
	}

	@Override
	public boolean valid(Date date) {
		Objects.requireNonNull(date, "BaseValidityPeriodClosed::valid::date"); //$NON-NLS-1$
		return (from.before(date) || date.equals(from)) && //
				(date.before(to) || date.equals(to));
	}

}
