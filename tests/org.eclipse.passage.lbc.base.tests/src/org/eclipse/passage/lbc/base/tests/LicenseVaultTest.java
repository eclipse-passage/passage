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
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.passage.lbc.internal.base.BaseLicenseVault;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.junit.Test;

public class LicenseVaultTest {

	@Test
	public void availableLicenses() {
		Collection<ConditionPack> availableLicenses = new BaseLicenseVault().availableLicenses(new FakeMiningRequest());
		if (availableLicenses.size() > 0) {
			availableLicenses.forEach((conditionPack) -> {
				assertEquals("floating", conditionPack.origin()); //$NON-NLS-1$
				assertEquals(Collections.emptyList(), conditionPack.conditions());
			});
		} else {
			fail();
		}
	}

}
