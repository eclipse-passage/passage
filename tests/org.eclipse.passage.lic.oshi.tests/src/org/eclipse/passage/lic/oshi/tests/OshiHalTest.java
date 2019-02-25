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
package org.eclipse.passage.lic.oshi.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.eclipse.passage.lic.oshi.OshiHal;
import org.junit.Test;

public class OshiHalTest {

	@Test
	public void testExtractPropertyNegative() throws Exception {
		assertNull(OshiHal.extractProperty(null));
		assertNull(OshiHal.extractProperty(new String()));
		assertFalse(OshiHal.evaluateProperty(null, null));
	}

}
