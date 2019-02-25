/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.oshi.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.stream.StreamSupport;

import org.eclipse.passage.lic.inspector.HardwareInspector;
import org.eclipse.passage.lic.internal.oshi.OshiHardwareInspector;
import org.junit.Test;

@SuppressWarnings("restriction")
public class OshiHardwareInspectorTest {
	
	@Test
	public void testKnownProperties() {
		HardwareInspector inspector = new OshiHardwareInspector();
		Iterable<String> knownProperties = inspector.getKnownProperties();
		assertTrue(knownProperties.iterator().hasNext());
		for (String name : knownProperties) {
			String property = inspector.inspectProperty(name);
			assertNotNull(property);
		}
	}

	@Test
	public void testDump() throws IOException {
		HardwareInspector inspector = new OshiHardwareInspector();
		StringBuilder infoBuilder = new StringBuilder();
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()){
			inspector.dumpHardwareInfo(baos);
			infoBuilder.append(new String(baos.toByteArray()));
		}
		String string = infoBuilder.toString();
		long values = 0;
		String[] split = string.split(String.valueOf('\n'));
		for (String line : split) {
			if (line.trim().isEmpty()) {
				continue;
			}
			values++;
		}
		long known = StreamSupport.stream(inspector.getKnownProperties().spliterator(), false).count();
		assertEquals(values, known);
	}

}
