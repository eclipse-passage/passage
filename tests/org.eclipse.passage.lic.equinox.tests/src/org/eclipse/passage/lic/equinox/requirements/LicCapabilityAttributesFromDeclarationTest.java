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
package org.eclipse.passage.lic.equinox.requirements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.eclipse.passage.lic.api.LicensingException;
import org.junit.Test;

public final class LicCapabilityAttributesFromDeclarationTest {

	@Test
	public void empty() {
		assertTrue(packs("").isEmpty()); //$NON-NLS-1$
	}

	@Test
	public void foreign() {
		assertTrue(packs("foreign.capability-first;property1=value1;property2=value2," //$NON-NLS-1$
				+ " foreign.capability-second;property1=value1;property2=value2" //$NON-NLS-1$
		).isEmpty());
	}

	@Test
	public void several() {
		Collection<Map<String, Object>> packs = packs(
				"licensing.feature;licensing.feature=\"PI\";version=\"3.14.15\";name=\"PI of version PI\";level=\"error\",\r\n" //$NON-NLS-1$
						+ " licensing.feature;licensing.feature=\"E\";version=\"2.71.82\";name=\"Euler number\";level=\"info\";provider=\"Euler\";agreements=\"Honor Euler.txt::comp_lics/EULERS IDENTITY\",\r\n" //$NON-NLS-1$
						+ " licensing.feature;licensing.feature=\"Incomplete\",\r\n" //$NON-NLS-1$
						+ " licensing.feature"); //$NON-NLS-1$
		assertEquals(4, packs.size()); // all read
		assertEquals(1, packs.stream().filter(Map::isEmpty).count()); // including the empty one
		Optional<Map<String, Object>> pi = packs.stream()//
				.filter(map -> "PI".equals(map.get("licensing.feature"))) //$NON-NLS-1$ //$NON-NLS-2$
				.findFirst();
		assertTrue(pi.isPresent());
		assertEquals("3.14.15", pi.get().get("version")); //$NON-NLS-1$//$NON-NLS-2$
		assertEquals("PI of version PI", pi.get().get("name")); //$NON-NLS-1$//$NON-NLS-2$
		assertEquals("error", pi.get().get("level")); //$NON-NLS-1$//$NON-NLS-2$
		Optional<Map<String, Object>> e = packs.stream()//
				.filter(map -> "E".equals(map.get("licensing.feature"))) //$NON-NLS-1$ //$NON-NLS-2$
				.findFirst();
		assertTrue(e.isPresent());
		assertEquals("Honor Euler.txt::comp_lics/EULERS IDENTITY", e.get().get("agreements")); //$NON-NLS-1$//$NON-NLS-2$
	}

	@Test(expected = NullPointerException.class)
	public void sourceIsMandatory() {
		new LicCapabilityAttributesFromDeclaration(null);
	}

	private Collection<Map<String, Object>> packs(String source) {
		try {
			return new LicCapabilityAttributesFromDeclaration(source).get();
		} catch (LicensingException e) {
			fail("Not intended to fail on valid data"); //$NON-NLS-1$
			return Collections.emptySet(); // unreachable
		}

	}

}
