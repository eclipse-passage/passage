/*******************************************************************************
 * Copyright (c) 2020, 2026 ArSysOp
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
package org.eclipse.passage.lic.api.tests.conditions.evaluation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.eclipse.passage.lic.api.conditions.evaluation.Emission;
import org.eclipse.passage.lic.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.api.tests.fakes.conditions.FakeConditionPack;
import org.eclipse.passage.lic.api.tests.fakes.conditions.evaluation.FakePermission;
import org.junit.jupiter.api.Test;

public final class EmissionTest {

	@Test
	public void conditionPackIsMandatory() {
		assertThrows(NullPointerException.class, () -> new Emission(null));
	}

	@Test
	public void doesNotTolerateNullPermission() {
		assertThrows(RuntimeException.class, () -> new Emission(new FakeConditionPack(), (Permission) null));
	}

	@Test
	public void doesNotTolerateNullPermissions() {
		assertThrows(NullPointerException.class,
				() -> new Emission(new FakeConditionPack(), (Collection<Permission>) null));
	}

	@Test
	public void canSupplyNoPermissions() {
		assertTrue(new Emission(new FakeConditionPack()).permissions().isEmpty());
	}

	@Test
	public void accumulateSinglePermission() {
		Permission fake = new FakePermission();
		Collection<Permission> permissions = new Emission(new FakeConditionPack(), fake).permissions();
		assertTrue(permissions.size() == 1);
		assertEquals(fake, permissions.iterator().next());
	}

}
