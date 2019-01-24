/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.net.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lic.net.TimeConditions;
import org.junit.Test;

public class TimeConditionsTest {
	
	@Test
	public void testIsFutureLocalTimeNegative() {
		assertFalse(TimeConditions.isFutureLocalDateTime(null));
		assertFalse(TimeConditions.isFutureLocalDateTime(new String()));
	}
	@Test
	public void testIsFutureLocalTimePositive() {
		assertFalse(TimeConditions.isFutureLocalDateTime("2018-11-20T17:00:00")); //$NON-NLS-1$
		assertTrue(TimeConditions.isFutureLocalDateTime("2028-11-20T17:00:00")); //$NON-NLS-1$
	}

}
