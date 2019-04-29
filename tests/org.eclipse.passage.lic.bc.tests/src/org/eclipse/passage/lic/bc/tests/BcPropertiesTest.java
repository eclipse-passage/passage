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

import static org.eclipse.passage.lic.bc.BcProperties.KEY_ALGO_DEFAULT;
import static org.eclipse.passage.lic.bc.BcProperties.KEY_SIZE_DEFAULT;
import static org.eclipse.passage.lic.bc.BcProperties.extractKeyAlgo;
import static org.eclipse.passage.lic.bc.BcProperties.extractKeySize;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

public class BcPropertiesTest {

	@Test
	public void testExtractKeyAlgo() {
		assertEquals("Key algo should match", KEY_ALGO_DEFAULT, extractKeyAlgo(null)); //$NON-NLS-1$
		assertEquals("Key algo should match", KEY_ALGO_DEFAULT, extractKeyAlgo(new HashMap<>())); //$NON-NLS-1$
	}

	@Test
	public void testExtractKeySize() {
		assertEquals("Key size should match", KEY_SIZE_DEFAULT, extractKeySize(null)); //$NON-NLS-1$
		assertEquals("Key size should match", KEY_SIZE_DEFAULT, extractKeySize(new HashMap<>())); //$NON-NLS-1$
	}
}
