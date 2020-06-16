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
package org.eclipse.passage.lic.api.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public final class TemporaryTest {
	/**
	 * <p>
	 * There only contract (meaning abstract) tests in this bundle. Nonetheless,
	 * it's still recognized as 'tests' bundle. All the special treatments are
	 * applied: sure-fire plug-in is applied. This one fails on a bundle where is
	 * nothing to run.
	 * </p>
	 * 
	 * <p>
	 * Thus we add a single deadly stupid but workable test especially for sure-fire
	 * to make it go.
	 * </p>
	 */
	@Test
	public void singleWorkableTestInABundleFullOfAbstractTest() {
		assertTrue(System.currentTimeMillis() > 0);
	}

}
