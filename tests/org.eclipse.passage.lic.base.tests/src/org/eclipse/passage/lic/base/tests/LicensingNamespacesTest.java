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
package org.eclipse.passage.lic.base.tests;

import static org.eclipse.passage.lic.base.LicensingNamespaces.ATTRIBUTE_LEVEL_DEFAULT;
import static org.eclipse.passage.lic.base.LicensingNamespaces.ATTRIBUTE_LEVEL_ERROR;
import static org.eclipse.passage.lic.base.LicensingNamespaces.ATTRIBUTE_LEVEL_FATAL;
import static org.eclipse.passage.lic.base.LicensingNamespaces.ATTRIBUTE_LEVEL_INFO;
import static org.eclipse.passage.lic.base.LicensingNamespaces.ATTRIBUTE_LEVEL_WARN;
import static org.eclipse.passage.lic.base.LicensingNamespaces.toLevelAttribute;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LicensingNamespacesTest {

	@Test
	public void testRestrictionLevelProperty() {
		assertEquals(ATTRIBUTE_LEVEL_DEFAULT, toLevelAttribute(this));
		assertEquals(ATTRIBUTE_LEVEL_DEFAULT, toLevelAttribute(this.toString()));

		assertEquals(ATTRIBUTE_LEVEL_INFO, toLevelAttribute(ATTRIBUTE_LEVEL_INFO));
		assertEquals(ATTRIBUTE_LEVEL_WARN, toLevelAttribute(ATTRIBUTE_LEVEL_WARN));
		assertEquals(ATTRIBUTE_LEVEL_ERROR, toLevelAttribute(ATTRIBUTE_LEVEL_ERROR));
		assertEquals(ATTRIBUTE_LEVEL_FATAL, toLevelAttribute(ATTRIBUTE_LEVEL_FATAL));

		assertEquals(ATTRIBUTE_LEVEL_INFO, toLevelAttribute("INfo")); //$NON-NLS-1$
		assertEquals(ATTRIBUTE_LEVEL_WARN, toLevelAttribute("wARn")); //$NON-NLS-1$
		assertEquals(ATTRIBUTE_LEVEL_ERROR, toLevelAttribute("eRroR")); //$NON-NLS-1$
		assertEquals(ATTRIBUTE_LEVEL_FATAL, toLevelAttribute("FaTaL")); //$NON-NLS-1$
	}

}
