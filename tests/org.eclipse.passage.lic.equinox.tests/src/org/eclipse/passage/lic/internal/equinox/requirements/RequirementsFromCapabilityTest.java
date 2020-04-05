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

import java.util.stream.Collectors;

import org.junit.Test;

public final class RequirementsFromCapabilityTest {

	@Test
	public void read() {
		DataBundle data = new DataBundle();
		assertEquals(//
				data.requirements(), //
				data.capabilities().stream() //
						.map(c -> new RequirementFromCapability(data.get(), c)) //
						.map(RequirementFromCapability::get) //
						.collect(Collectors.toSet())//
		);
	}

}
