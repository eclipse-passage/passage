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
import static org.junit.Assert.assertFalse;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.passage.lbc.internal.base.BaseLicenseVault;
import org.eclipse.passage.lbc.internal.base.BaseMiningRequest;
import org.eclipse.passage.lbc.internal.base.MinedConditionPacks;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class MinedConditionPacksTest extends LbcTestsBase {

	@Test
	public void positive() {
		List<ConditionPack> mined = Stream.of(new BaseMiningRequest(identifier(), version(), requester())) //
				.map(new MinedConditionPacks(new BaseLicenseVault())) //
				.flatMap(packs -> packs.stream()) //
				.collect(Collectors.toList());
		assertFalse(mined.isEmpty());
		ConditionPack pack = mined.get(0);
		assertEquals("floating", pack.origin()); //$NON-NLS-1$
		assertEquals(Collections.emptyList(), pack.conditions());
	}

}
