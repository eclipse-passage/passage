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
package org.eclipse.passage.lbc.base.tests;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.passage.lbc.internal.base.BaseLicenseVault;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.junit.Test;

public class LicenseVaultTest {

	@Test
	public void availableLicenses() {
		List<ConditionPack> pack = new LinkedList(new BaseLicenseVault().availableLicenses(new FakeMiningRequest()));
		assertEquals("floating", pack.get(0).origin()); //$NON-NLS-1$
		assertEquals(Collections.emptyList(), pack.get(0).conditions());
	}

}
