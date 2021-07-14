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

import static org.junit.Assert.assertEquals;

import java.time.ZonedDateTime;
import java.util.function.Function;

import org.eclipse.passage.lic.api.conditions.ValidityPeriodOpen;
import org.junit.Test;

/**
 * <p>
 * Core thing the contract demands: no invention, no defaults. If something is
 * wrong with the incoming data - the period-definition instance has no right to
 * exist.
 * </p>
 * <p>
 * Successfully created period is always consistent.
 * </p>
 */
public abstract class ValidityPeriodOpenContractTest<V extends ValidityPeriodOpen>
		extends ValidityPeriodContractTest<V> {

	/**
	 * Implementation must rise NPE if there is no data for starting date
	 * definition.
	 */
	@Test(expected = NullPointerException.class)
	public final void doNotInventFrom() {
		atLeastMonthLongFrom(null);
	}

	@Test
	public final void mustStartWithDefinedFrom() {
		mustBoundWithDefinedDate(this::atLeastMonthLongFrom, V::from);
	}

	protected final void mustBoundWithDefinedDate(Function<ZonedDateTime, V> ctor, Function<V, ZonedDateTime> bound) {
		ZonedDateTime now = ZonedDateTime.now();
		V period = ctor.apply(now);
		ZonedDateTime original = bound.apply(period);
		assertEquals(now, original);
	}

}
