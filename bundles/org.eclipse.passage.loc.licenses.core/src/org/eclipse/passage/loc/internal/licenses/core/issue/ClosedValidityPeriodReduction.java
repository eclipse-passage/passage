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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.passage.lic.api.conditions.ValidityPeriodClosed;
import org.eclipse.passage.lic.base.conditions.BaseValidityPeriodClosed;
import org.eclipse.passage.loc.internal.licenses.core.i18n.ReductionMessages;

final class ClosedValidityPeriodReduction<L> implements Reduction<L> {

	private final Logger log = LogManager.getLogger(getClass());
	private final Function<L, Optional<ValidityPeriodClosed>> get;
	private final BiConsumer<L, ValidityPeriodClosed> set;
	private final int length = 3;

	ClosedValidityPeriodReduction(Function<L, Optional<ValidityPeriodClosed>> get,
			BiConsumer<L, ValidityPeriodClosed> set) {
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
			log.warn(String.format(ReductionMessages.ClosedValidityPeriodReduction_reduction_validityperiod_length,
					length));
			log.warn(String.format(ReductionMessages.ClosedValidityPeriodReduction_reduction_validityperiod_allowed,
					valid.get().from(), valid.get().to(), valid.get().from(), allowed));
			set.accept(license, new BaseValidityPeriodClosed(valid.get().from(), allowed));
		}
	}

	private ZonedDateTime allowed(ZonedDateTime from) {
		return from.plus(length, ChronoUnit.MONTHS);
	}

}
