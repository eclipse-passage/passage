/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.lic.jface.tests;

import static org.junit.Assert.assertTrue;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.passage.lic.jface.resource.LicensingColors;
import org.junit.Test;

public class LicensingColorTest {

	/**
	 * excluded due swt dependency private static final RGB RGB_1_TEST = new RGB(1,
	 * 1, 1);
	 */

	@Test
	public void getLicenseColorTest() {
		// value for color not defined
		IEclipsePreferences preferences = LicensingColors.getPreferences();
		String colorValue = preferences.get(LicensingColors.COLOR_LEVEL_ERROR, ""); //$NON-NLS-1$
		assertTrue(colorValue.isEmpty());

		// value for color matches with definition in code
		String colorErrorDefaultValue = LicensingColors.getLicensingColor(LicensingColors.COLOR_LEVEL_ERROR);
		String colorError = LicensingColors.RGB_LEVEL_ERROR.toString();
		assertTrue(colorError.equals(colorErrorDefaultValue));

		// value for color matches with definition from registry

		/**
		 * excluded due swt dependency
		 *
		 *
		 * Color colorErrorColor =
		 * LicensingColors.getColor(LicensingColors.COLOR_LEVEL_ERROR);
		 * assertTrue(colorError.equals(colorErrorColor.getRGB().toString()));
		 *
		 *
		 *
		 * // applying change error color Map<String, RGB> mapColorsChange = new
		 * HashMap<String, RGB>();
		 * mapColorsChange.put(LicensingColors.COLOR_LEVEL_ERROR, RGB_1_TEST);
		 * LicensingColors.acceptColors(mapColorsChange);
		 *
		 * // changed value retrieved via preferences matches with new value String
		 * colorErrorChangedValue =
		 * LicensingColors.getLicensingColor(LicensingColors.COLOR_LEVEL_ERROR);
		 * assertTrue(colorErrorChangedValue.equals(RGB_1_TEST.toString()));
		 *
		 * // changed value retrieved via preferences matches with old value
		 * assertFalse(colorErrorChangedValue.equals(colorError));
		 */
	}
}
