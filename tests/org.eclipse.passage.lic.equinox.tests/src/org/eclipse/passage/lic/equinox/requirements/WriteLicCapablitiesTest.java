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

import java.util.Arrays;
import java.util.List;

import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.base.NamedData;
import org.junit.Test;

public final class WriteLicCapablitiesTest {
	private final String expectation = "Provide-Capability: " //$NON-NLS-1$
			+ "licensing.feature;licensing.feature=\"E\";name=\"Euler number\";version=\"2.71.82\";provider=\"Euler\";level=\"info\";agreements=\"Honor Euler.txt::comp_lics/EULERS IDENTITY\"," //$NON-NLS-1$
			+ "licensing.feature;licensing.feature=\"PI\";name=\"PI of version PI\";version=\"3.14.15\";provider=\"Eclipse Passage\";level=\"error\"," //$NON-NLS-1$
			+ "licensing.feature;licensing.feature=\"Incomplete\";name=\"Incomplete\";version=\"0.0.0\";provider=\"Eclipse Passage\";level=\"warn\""; //$NON-NLS-1$

	@Test
	public void writeProvideCapabilityHeader() {
		DataBundle data = new DataBundle();
		StringBuilder target = new StringBuilder();
		new NamedData.Writable<List<Requirement>>(//
				new RequirementsToBundle(Arrays.asList(//
						data.e(), //
						data.pi(), //
						data.incomplete())))//
								.write(target); //
		assertEquals(expectation, target.toString());

	}
}
