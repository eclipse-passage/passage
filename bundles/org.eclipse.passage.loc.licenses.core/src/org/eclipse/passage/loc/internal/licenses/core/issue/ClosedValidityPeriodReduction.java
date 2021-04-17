/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.loc.internal.licenses.core.issue;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.eclipse.passage.lic.internal.api.conditions.ValidityPeriodClosed;
import org.eclipse.passage.lic.internal.base.conditions.BaseValidityPeriodClosed;

final class ClosedValidityPeriodReduction<L> implements Reduction<L> {

	private final Function<L, Optional<ValidityPeriodClosed>> get;
	private final BiConsumer<L, ValidityPeriodClosed> set;
	private final int length = 1;

	ClosedValidityPeriodReduction(Function<L, Optional<ValidityPeriodClosed>> get, BiConsumer<L, ValidityPeriodClosed> set) {
		this.get = get;
		this.set = set;
	}

	@Override
	public void accept(L license) {
		Optional<ValidityPeriodClosed> valid = get.apply(license);
		if (!valid.isPresent()) {
			return;
		}
		ZonedDateTime allowed = allowed(valid.get().from());
		if (allowed.isBefore(valid.get().to())) {
			set.accept(license, new BaseValidityPeriodClosed(valid.get().from(), allowed));
		}
	}

	private ZonedDateTime allowed(ZonedDateTime from) {
		return from.plus(length, ChronoUnit.MONTHS);
	}

}
