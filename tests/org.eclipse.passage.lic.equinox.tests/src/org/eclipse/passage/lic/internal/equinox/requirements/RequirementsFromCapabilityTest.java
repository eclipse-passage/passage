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
package org.eclipse.passage.lic.internal.equinox.requirements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class RequirementsFromCapabilityTest {

	@Test
	public void read() {
		DataBundle data = new DataBundle();
		Set<Requirement> found = data.capabilities().stream() //
				.map(c -> new RequirementFromCapability(data.get(), c)) //
				.map(RequirementFromCapability::get) //
				.collect(Collectors.toSet());
		assertContainsUnsatisfiableRequirement(found);
		assertContainsAllExpectedRequirements(data, found);
	}

	private void assertContainsUnsatisfiableRequirement(Set<Requirement> requirements) {
		assertTrue(//
				requirements.stream()//
						.filter(new Unsatisfiable())//
						.findAny() //
						.isPresent()//
		);
	}

	private void assertContainsAllExpectedRequirements(DataBundle data, Set<Requirement> requirements) {
		assertEquals(//
				data.validRequirements(), //
				requirements.stream()//
						.filter(r -> !new Unsatisfiable().test(r)) //
						.collect(Collectors.toSet())//
		);
	}

}
