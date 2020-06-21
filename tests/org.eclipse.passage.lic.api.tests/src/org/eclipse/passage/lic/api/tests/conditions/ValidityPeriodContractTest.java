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
package org.eclipse.passage.lic.api.tests.conditions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.eclipse.passage.lic.internal.api.conditions.ValidityPeriod;
import org.junit.Test;

@SuppressWarnings("restriction")
public abstract class ValidityPeriodContractTest<V extends ValidityPeriod> {

	@Test
	public final void dateCanBeValid() {
		assertTrue(atLeastMonthLongFrom(movedNow(-1)).valid(new Date()));
	}

	@Test
	public final void dateCanBeInvalid() {
		assertFalse(atLeastMonthLongFrom(movedNow(1)).valid(new Date()));
	}

	protected final Date movedNow(int hours) {
		return new Date(System.currentTimeMillis() + hours * 3_600_000L);
	}

	protected abstract V atLeastMonthLongFrom(Date from);

}
