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
package org.eclipse.passage.lic.api.tests.version;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lic.api.version.Version;
import org.junit.Test;

public abstract class VersionContractTest {

	@Test
	public void sameValuedVersionsAreEqual() {
		assertTrue(fromString("1.0.0").equals(fromString("1.0.0"))); //$NON-NLS-1$//$NON-NLS-2$
	}

	@Test
	public void differentValuedVersionsAreNotEqual() {
		assertFalse(fromString("1.0.0").equals(fromString("1.0.1"))); //$NON-NLS-1$//$NON-NLS-2$
	}

	protected abstract Version fromString(String source);

}
