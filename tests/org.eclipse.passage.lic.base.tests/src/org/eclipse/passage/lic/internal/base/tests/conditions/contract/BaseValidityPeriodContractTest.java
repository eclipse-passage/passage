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
package org.eclipse.passage.lic.internal.base.tests.conditions.contract;

import java.time.ZonedDateTime;

import org.eclipse.passage.lic.api.conditions.ValidityPeriodClosed;
import org.eclipse.passage.lic.api.tests.conditions.ValidityPeriodClosedContractTest;
import org.eclipse.passage.lic.base.conditions.BaseValidityPeriodClosed;

@SuppressWarnings("restriction")
public final class BaseValidityPeriodContractTest extends ValidityPeriodClosedContractTest {

	@Override
	protected ValidityPeriodClosed forTwoDates(ZonedDateTime from, ZonedDateTime to) {
		return new BaseValidityPeriodClosed(from, to);
	}

	@Override
	protected ValidityPeriodClosed someTimeBefore(ZonedDateTime to) {
		return new BaseValidityPeriodClosed(movedNow(-1), to);
	}

	@Override
	protected ValidityPeriodClosed atLeastMonthLongFrom(ZonedDateTime from) {
		return new BaseValidityPeriodClosed(from, movedNow(24 * 35));
	}

}
