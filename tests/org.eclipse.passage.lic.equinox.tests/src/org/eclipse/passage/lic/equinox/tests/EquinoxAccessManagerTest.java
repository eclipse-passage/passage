/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.equinox.tests;

import org.eclipse.passage.lic.base.SystemReporter;
import org.eclipse.passage.lic.internal.equinox.access.EquinoxAccessManager;
import org.junit.Test;

@SuppressWarnings("restriction")
public class EquinoxAccessManagerTest {
	
	@Test
	public void testExecuteRestrictions() {
		EquinoxAccessManager accessManager = new EquinoxAccessManager();
		accessManager.bindLicensingReporter(SystemReporter.INSTANCE);
		accessManager.executeAccessRestrictions(null);
	}

}
