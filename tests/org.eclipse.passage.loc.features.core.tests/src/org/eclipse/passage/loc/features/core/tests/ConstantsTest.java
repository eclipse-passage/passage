/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Nikifor Fedorov <zelenyhleb@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.features.core.tests;

import static org.junit.Assert.assertEquals;

import org.eclipse.passage.loc.features.core.Features;
import org.eclipse.passage.loc.features.core.FeaturesCore;
import org.junit.Test;

public class ConstantsTest {

	@Test
	public void isDomainNameCorrect() {
		assertEquals("features", Features.DOMAIN_NAME);
	}

	@Test
	public void isFileExtensionXMICorrect() {
		assertEquals("features_xmi", Features.FILE_EXTENSION_XMI);
	}

	@Test
	public void isBundleNameCorrect() {
		assertEquals("org.eclipse.passage.loc.features.core", FeaturesCore.BUNDLE_SYMBOLIC_NAME);
	}
	
}
