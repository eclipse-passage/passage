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
package org.eclipse.passage.lic.base.tests.conditions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.base.conditions.ConditionMiners;
import org.eclipse.passage.lic.base.io.LicensingPaths;
import org.eclipse.passage.lic.base.tests.LicensningBaseTests;
import org.eclipse.passage.lic.runtime.LicensingException;
import org.eclipse.passage.lic.runtime.LicensingResult;
import org.junit.Test;

public class ConditionMinersTest extends LicensningBaseTests {

	@Test
	public void testCollectPacksNegative() {
		collectPacksInvalid(null, (String) null);
		collectPacksInvalid(null, LicensingPaths.EXTENSION_LICENSE_DECRYPTED);
		collectPacksInvalid(null, LicensingPaths.EXTENSION_LICENSE_ENCRYPTED);
	}

	@SuppressWarnings("nls")
	private void collectPacksInvalid(Path path, String... extension) {
		try {
			ConditionMiners.collectPacks(path, extension);
			fail("Invalid arguments accepted");
		} catch (LicensingException e) {
			// expected
		}
	}

	@Test
	public void testCollectPacks() throws LicensingException {
		Path root = Paths.get(baseFolder.getRoot().getAbsolutePath());
		assertEquals(0, ConditionMiners.collectPacks(root, (String) null).size());
		assertEquals(0, ConditionMiners.collectPacks(root, (String[]) null).size());
	}

	@Test
	public void testMineNegative() {
		List<String> paths = Collections.emptyList();
		LicensingResult nothing = ConditionMiners.mine(LicensingConfigurations.INVALID, null, null, null, null, paths);
		assertEquals(LicensingResult.OK, nothing.getSeverity());
	}

}
