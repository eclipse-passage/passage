/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.base.tests.permission.observatory;

import static org.junit.Assert.assertEquals;

import java.time.temporal.ChronoUnit;

import org.eclipse.passage.lic.internal.base.observatory.CheckSchedule;
import org.junit.Test;

@SuppressWarnings("restriction")
public class CheckScheduleTest {

	@Test
	public void testDefaultSchedule() {
		assertEquals(10 * 60, new CheckSchedule().seconds());
	}

	@Test
	public void testMinutesSchedule() {
		assertEquals(5 * 60, new CheckSchedule(5).seconds());
	}

	@Test
	public void testAlternativechedule() {
		assertEquals(3 * 24 * 60 * 60, new CheckSchedule(3, ChronoUnit.DAYS).seconds());
	}

}
