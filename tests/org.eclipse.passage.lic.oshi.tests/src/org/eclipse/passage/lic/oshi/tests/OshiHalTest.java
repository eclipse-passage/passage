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

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import org.eclipse.passage.lic.oshi.OshiHal;
import org.junit.Test;

public class OshiHalTest {

	@Test
	public void testExtractPropertyNegative() throws Exception {
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			OshiHal.dumpOperatingSystem(baos, new HashMap<String, String>());
			byte[] byteArray = baos.toByteArray();
			assertEquals(0, byteArray.length);
		}
	}

}
