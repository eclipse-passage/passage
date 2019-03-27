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
package org.eclipse.passage.lic.base.tests;

import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.junit.Test;

public class LicensingConfigurationsTest {

	@Test
	public void testCreate() {
		final String p1 = "p1"; //$NON-NLS-1$
		final String v1 = "v1"; //$NON-NLS-1$
		LicensingConfiguration create = LicensingConfigurations.create(p1, v1);
		String string = create.toString();
		assertTrue(string.contains(p1));
		assertTrue(string.contains(v1));
	}

}
