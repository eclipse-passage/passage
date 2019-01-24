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
package org.eclipse.passage.lic.bc.tests;

import static org.eclipse.passage.lic.bc.BcProperties.*;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

@SuppressWarnings("restriction")
public class BcPropertiesTest {
	
	@Test
	public void testExtractKeyAlgo() {
		assertEquals(KEY_ALGO_DEFAULT, extractKeyAlgo(null));
		assertEquals(KEY_ALGO_DEFAULT, extractKeyAlgo(new HashMap<>()));
	}

	@Test
	public void testExtractKeySize() {
		assertEquals(KEY_SIZE_DEFAULT, extractKeySize(null));
		assertEquals(KEY_SIZE_DEFAULT, extractKeySize(new HashMap<>()));
	}
}
