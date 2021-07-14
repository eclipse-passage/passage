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
package org.eclipse.passage.lic.api.tests.conditions.evaluation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.eclipse.passage.lic.api.conditions.evaluation.Emission;
import org.eclipse.passage.lic.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.api.tests.fakes.conditions.FakeConditionPack;
import org.eclipse.passage.lic.api.tests.fakes.conditions.evaluation.FakePermission;
import org.junit.Test;

public final class EmissionTest {

	@Test(expected = NullPointerException.class)
	public void conditionPackIsMandatory() {
		new Emission(null);
	}

	@Test(expected = RuntimeException.class)
	public void doesNotTolerateNullPermission() {
		new Emission(new FakeConditionPack(), (Permission) null);
	}

	@Test(expected = NullPointerException.class)
	public void doesNotTolerateNullPermissions() {
		new Emission(new FakeConditionPack(), (Collection<Permission>) null);
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
