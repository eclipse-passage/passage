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
import java.util.stream.Stream;

import org.eclipse.passage.lbc.internal.base.BaseLicenseVault;
import org.eclipse.passage.lbc.internal.base.BaseMiningRequest;
import org.eclipse.passage.lbc.internal.base.MinedConditionPacks;
import org.junit.Test;

public final class MinedConditionPacksTest extends LbcTestsBase {

	@Test
	public void positive() {
		Stream.of(new BaseMiningRequest(identifier(), version(), requester())) //
				.map(new MinedConditionPacks(new BaseLicenseVault())) //
				.flatMap(packs -> packs.stream()) //
				.forEach(pack -> {
					assertEquals("floating", pack.origin()); //$NON-NLS-1$
					assertEquals(Collections.emptyList(), pack.conditions());
				});
		;
	}

}
