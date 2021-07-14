/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.net.tests.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.file.Paths;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.KeyKeeper;
import org.eclipse.passage.lic.base.io.FileKeyKeeper;
import org.eclipse.passage.lic.base.io.MD5Hashes;
import org.eclipse.passage.lic.internal.net.io.SafePayload;
import org.junit.Test;

public final class SafePayloadTest {

	@Test
	public void symmetric() {
		String original = "S0me sophisticäted Str!ng"; //$NON-NLS-1$
		try {
			byte[] encoded = new SafePayload(keerper(), new MD5Hashes()).encode(original.getBytes());
			assertTrue(encoded.length > 0);
			System.out.println(new String(encoded));
			byte[] decoded = new SafePayload(keerper(), new MD5Hashes()).decode(encoded);
			assertTrue(decoded.length > 0);
			assertEquals(original, new String(decoded));
		} catch (LicensingException e) {
			fail("Not intended to fail on valid data"); //$NON-NLS-1$
		}
	}

	private KeyKeeper keerper() throws LicensingException {
		return new FileKeyKeeper(Paths.get("resource").resolve("io").resolve("key.pub")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
}
