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
package org.eclipse.passage.lic.internal.base.tests.time;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lic.internal.base.time.IsLocalFuture;
import org.junit.Test;

public class IsLocalFutureTest {

	private final IsLocalFuture predicate = new IsLocalFuture();

	@Test
	public void testIsFutureLocalTimeNegative() {
		assertFalse(predicate.test(null));
		assertFalse(predicate.test("")); //$NON-NLS-1$
	}

	@Test
	public void testIsFutureLocalTimePositive() {
		assertFalse(predicate.test("2018-11-20T17:00:00")); //$NON-NLS-1$
		assertTrue(predicate.test("2028-11-20T17:00:00")); //$NON-NLS-1$
	}

}
