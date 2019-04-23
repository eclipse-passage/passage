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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lic.base.conditions.LicensingConditions;
import org.junit.Test;

public class LicensingConditionsTest {

	@Test
	public void testEvaluateSegmentValuePositive() {
		assertTrue(LicensingConditions.evaluateSegmentValue("*some*", "something")); //$NON-NLS-1$//$NON-NLS-2$
		assertTrue(LicensingConditions.evaluateSegmentValue("some*", "something")); //$NON-NLS-1$ //$NON-NLS-2$
		assertFalse(LicensingConditions.evaluateSegmentValue("*some*", "anotherthing")); //$NON-NLS-1$ //$NON-NLS-2$
		assertFalse(LicensingConditions.evaluateSegmentValue("*Thin*", "anotherthing")); //$NON-NLS-1$//$NON-NLS-2$
		assertTrue(LicensingConditions.evaluateSegmentValue("*Thin*", "anotherThing")); //$NON-NLS-1$ //$NON-NLS-2$
		assertTrue(LicensingConditions.evaluateSegmentValue("A*the*Some*", "Another Something")); //$NON-NLS-1$ //$NON-NLS-2$
	}

}
