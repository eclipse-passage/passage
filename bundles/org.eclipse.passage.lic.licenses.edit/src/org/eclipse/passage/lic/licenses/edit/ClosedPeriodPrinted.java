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
package org.eclipse.passage.lic.licenses.edit;

import java.util.Date;
import java.util.function.Function;

import org.eclipse.passage.lic.licenses.model.api.ValidityPeriod;
import org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed;

public final class ClosedPeriodPrinted {

	private final String from;
	private final String until;

	// FIXME: no code in constructor. It's work for CachingFunction.
	public ClosedPeriodPrinted(ValidityPeriod valid) {
		ValidityPeriodClosed period = closed(valid);
		this.from = date(period, ValidityPeriodClosed::getFrom);
		this.until = date(period, ValidityPeriodClosed::getUntil);
	}

	private String date(ValidityPeriodClosed period, Function<ValidityPeriodClosed, Date> date) {
		return period == null //
				? "unknown" //$NON-NLS-1$
				: new DatePrinted(date.apply(period)).get();
	}

	private ValidityPeriodClosed closed(ValidityPeriod period) {
		if (period instanceof ValidityPeriodClosed) {
			return (ValidityPeriodClosed) period;
		}
		return null;
	}

	public String from() {
		return from;
	}

	public String until() {
		return until;
	}

}
