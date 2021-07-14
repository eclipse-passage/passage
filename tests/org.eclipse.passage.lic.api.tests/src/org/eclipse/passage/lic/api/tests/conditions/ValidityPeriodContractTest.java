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
package org.eclipse.passage.lic.api.tests.conditions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.ZonedDateTime;

import org.eclipse.passage.lic.api.conditions.ValidityPeriod;
import org.junit.Test;

public abstract class ValidityPeriodContractTest<V extends ValidityPeriod> {

	@Test
	public final void dateCanBeValid() {
		assertTrue(atLeastMonthLongFrom(movedNow(-1)).valid(ZonedDateTime.now()));
	}

	@Test
	public final void dateCanBeInvalid() {
		assertFalse(atLeastMonthLongFrom(movedNow(1)).valid(ZonedDateTime.now()));
	}

	protected final ZonedDateTime movedNow(int hours) {
		return ZonedDateTime.now().plusHours(hours);
	}

	protected abstract V atLeastMonthLongFrom(ZonedDateTime from);

}
