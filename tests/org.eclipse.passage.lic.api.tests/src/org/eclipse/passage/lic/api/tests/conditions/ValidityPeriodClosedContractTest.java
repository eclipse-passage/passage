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

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.eclipse.passage.lic.internal.api.conditions.ValidityPeriodClosed;
import org.junit.Test;

/**
 * <p>
 * Core thing the contract demands: no invention, no defaults. If something is
 * wrong with the incoming data - the period-definition instance has no right to
 * exist.
 * </p>
 * <p>
 * Successfully created period is always valid.
 * </p>
 */
@SuppressWarnings("restriction")
public abstract class ValidityPeriodClosedContractTest extends ValidityPeriodOpenContractTest<ValidityPeriodClosed> {

	/**
	 * Attempt to construct a period on reversed dates must fail, type of failure is
	 * on an implementor.
	 */
	@Test(expected = Exception.class)
	public final void doNotReverseIncorectBounds() {
		forTwoDates(movedNow(10), movedNow(-10));
	}

	/**
	 * Implementation must rise NPE if there is no data for ending date definition.
	 */
	@Test(expected = NullPointerException.class)
	public final void doNotInventTo() {
		forTwoDates(new Date(), null);
	}

	@Test
	public final void fromBeforeTo() {
		ValidityPeriodClosed period = forTwoDates(new Date(), movedNow(1));
		assertTrue(period.from().before(period.to()));
	}

	@Test
	public final void mustEndWithDefinedTo() {
		mustBoundWithDefinedDate(this::someTimeBefore, ValidityPeriodClosed::to);
	}

	@Test
	public final void toIsConstant() {
		boundIsConstant(this::someTimeBefore, ValidityPeriodClosed::to);
	}

	protected abstract ValidityPeriodClosed forTwoDates(Date from, Date to);

	protected abstract ValidityPeriodClosed someTimeBefore(Date to);

}
