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
package org.eclipse.passage.lbc.internal.base.chains.tests;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public final class PlaceholderTest {

	@Test
	public void test() {
		assertFalse(doILikeOnion());
	}

	public boolean doILikeOnion() {
		return false;
	}

}
