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
package org.eclipse.passage.lbc.json.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.eclipse.passage.lbc.internal.api.persistence.BoundLicense;
import org.eclipse.passage.lbc.internal.base.BaseBoundLicense;
import org.eclipse.passage.lbc.internal.base.ConditionIdentifier;
import org.eclipse.passage.lbc.internal.base.LicenseCapacity;
import org.eclipse.passage.lbc.internal.base.LicenseTaken;
import org.eclipse.passage.lbc.json.JsonObjectMapper;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationCycleTest {

	@Test
	public void positive() {
		try {
			String serialized = mapper().writeValueAsString(new BaseBoundLicense(identifier(), taken(), capacity()));
			BoundLicense license = mapper().readValue(serialized, BoundLicense.class);
			assertEquals(Integer.valueOf(3), license.capacity().get().get());
			assertEquals(Integer.valueOf(0), license.taken().get().get());
			assertEquals("the.best.feature.in.the.world", license.identifier().get().get()); //$NON-NLS-1$
			assertTrue(license.takeable());
		} catch (JsonProcessingException e) {
			fail();
		}
	}

	private ObjectMapper mapper() {
		return new JsonObjectMapper().get();
	}

	private ConditionIdentifier identifier() {
		return new ConditionIdentifier("the.best.feature.in.the.world"); //$NON-NLS-1$
	}

	private LicenseTaken taken() {
		return new LicenseTaken(key -> 0);
	}

	private LicenseCapacity capacity() {
		return new LicenseCapacity(key -> 3);
	}

}
