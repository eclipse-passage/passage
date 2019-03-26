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

import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL_DEFAULT;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL_ERROR;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL_FATAL;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL_INFO;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL_WARN;
import static org.eclipse.passage.lic.base.LicensingProperties.toRestrictionLevelProperty;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LicensingPropertiesTest {

	@Test
	public void testRestrictionLevelProperty() {
		assertEquals(LICENSING_RESTRICTION_LEVEL_DEFAULT, toRestrictionLevelProperty(this));
		assertEquals(LICENSING_RESTRICTION_LEVEL_DEFAULT, toRestrictionLevelProperty(this.toString()));

		assertEquals(LICENSING_RESTRICTION_LEVEL_INFO, toRestrictionLevelProperty(LICENSING_RESTRICTION_LEVEL_INFO));
		assertEquals(LICENSING_RESTRICTION_LEVEL_WARN, toRestrictionLevelProperty(LICENSING_RESTRICTION_LEVEL_WARN));
		assertEquals(LICENSING_RESTRICTION_LEVEL_ERROR, toRestrictionLevelProperty(LICENSING_RESTRICTION_LEVEL_ERROR));
		assertEquals(LICENSING_RESTRICTION_LEVEL_FATAL, toRestrictionLevelProperty(LICENSING_RESTRICTION_LEVEL_FATAL));

		assertEquals(LICENSING_RESTRICTION_LEVEL_INFO, toRestrictionLevelProperty("INfo")); //$NON-NLS-1$
		assertEquals(LICENSING_RESTRICTION_LEVEL_WARN, toRestrictionLevelProperty("wARn")); //$NON-NLS-1$
		assertEquals(LICENSING_RESTRICTION_LEVEL_ERROR, toRestrictionLevelProperty("eRroR")); //$NON-NLS-1$
		assertEquals(LICENSING_RESTRICTION_LEVEL_FATAL, toRestrictionLevelProperty("FaTaL")); //$NON-NLS-1$
	}

}
