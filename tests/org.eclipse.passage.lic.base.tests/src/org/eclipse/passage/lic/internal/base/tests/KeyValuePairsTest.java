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
package org.eclipse.passage.lic.internal.base.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Properties;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.base.KeyValuePairs;
import org.junit.jupiter.api.Test;

public final class KeyValuePairsTest {

	@Test
	public void empty() {
		assertTrue(properties("").isEmpty()); //$NON-NLS-1$
	}

	@Test
	public void single() {
		Properties properties = properties("key=value");//$NON-NLS-1$
		assertEquals(1, properties.size());
		assertProperty(properties, "key", "value"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void sequential() {
		Properties properties = properties("key1=value1\nkey2=value2");//$NON-NLS-1$
		assertEquals(2, properties.size());
		assertProperty(properties, "key1", "value1"); //$NON-NLS-1$ //$NON-NLS-2$
		assertProperty(properties, "key2", "value2"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void incomplete() {
		Properties properties = properties("key");//$NON-NLS-1$
		assertEquals(1, properties.size());
		assertProperty(properties, "key", ""); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void spacesAndQuotes() {
		Properties properties = properties(//
				"licensing.feature=\"PI\"\nversion=\"3.14.15\"\nname=\"PI of version PI\"\nlevel=\"error\""); //$NON-NLS-1$
		assertEquals(4, properties.size());
		assertProperty(properties, "licensing.feature", "\"PI\""); //$NON-NLS-1$ //$NON-NLS-2$
		assertProperty(properties, "version", "\"3.14.15\""); //$NON-NLS-1$ //$NON-NLS-2$
		assertProperty(properties, "name", "\"PI of version PI\""); //$NON-NLS-1$ //$NON-NLS-2$
		assertProperty(properties, "level", "\"error\""); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private void assertProperty(Properties properties, String key, String value) {
		assertTrue(properties.containsKey(key));
		assertEquals(value, properties.get(key));
	}

	private Properties properties(String source) {
		try {
			return new KeyValuePairs(source, "intended").get(); //$NON-NLS-1$
		} catch (LicensingException e) {
			fail("Is not intended to fail on valid data"); //$NON-NLS-1$
			return new Properties();// unreachable
		}
	}

	@Test
	public void sourceIsMandatory() {
		assertThrows(NullPointerException.class, () -> new KeyValuePairs(null, "intended")); //$NON-NLS-1$
	}

	@Test
	public void errorContextIsMandatory() {
		assertThrows(NullPointerException.class, () -> new KeyValuePairs("should not be parsed", null)); //$NON-NLS-1$
	}
}
