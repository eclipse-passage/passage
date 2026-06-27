/*******************************************************************************
 * Copyright (c) 2020. 2026 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support and improvements
 *******************************************************************************/
package org.eclipse.passage.lic.internal.base.tests.conditions.evaluation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;

import org.eclipse.passage.lic.api.conditions.ConditionPack;
import org.eclipse.passage.lic.api.conditions.evaluation.Emission;
import org.eclipse.passage.lic.api.tests.fakes.conditions.FakeConditionPack;
import org.eclipse.passage.lic.api.tests.fakes.conditions.evaluation.FakePermission;
import org.eclipse.passage.lic.base.conditions.evaluation.SumOfEmissions;
import org.junit.jupiter.api.Test;

@SuppressWarnings("restriction")
public final class SumOfEmissionsTest {

	@Test
	public void prohibitsNullFirstOperand() {
		assertThrows(NullPointerException.class,
				() -> new SumOfEmissions().apply(null, empty(new FakeConditionPack())));
	}

	@Test
	public void prohibitsNullSecondOperand() {
		assertThrows(NullPointerException.class,
				() -> new SumOfEmissions().apply(empty(new FakeConditionPack()), null));
	}

	@Test
	public void sumsPermissions() {
		ConditionPack common = new FakeConditionPack();
		assertEquals(2, //
				new SumOfEmissions().apply(//
						new Emission(common, new FakePermission()), //
						new Emission(common, new FakePermission()))//
						.permissions().size());
	}

	@Test
	public void sumSharesOriginalPack() {
		ConditionPack common = new FakeConditionPack();
		assertEquals(common, //
				new SumOfEmissions().apply(empty(common), empty(common)).conditionPack());
	}

	@Test
	public void doesNotSumDifferentPacks() {
		assertThrows(Exception.class, () -> new SumOfEmissions().apply(//
				new Emission(new FakeConditionPack(), new FakePermission()), //
				new Emission(new FakeConditionPack(), new FakePermission())));
	}

	private Emission empty(ConditionPack pack) {
		return new Emission(pack, Collections.emptyList());
	}

}
