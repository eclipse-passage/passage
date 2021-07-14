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
package org.eclipse.passage.lic.equinox.requirements;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public final class BundleVendorTest {

	@Test
	public void readVendor() {
		assertEquals(//
				"Eclipse Passage", //$NON-NLS-1$
				new BundleVendor(new DataBundle().bundle()).get());
	}

	@Test(expected = NullPointerException.class)
	public void prohibitNull() {
		new BundleVendor(null);
	}

}
