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
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.seal.demo.tests.access;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lic.internal.base.access.Access;
import org.junit.Test;

/**
 * Integration test: demands OSGi running
 */
public final class AccessCanUseTest {

	@Test
	public void allowUnknownFeature() {
		assertTrue(new Access(new TestFramework.Expired()).canUse("unkonwn-feature")); //$NON-NLS-1$
	}

	@Test
	public void allowKnownFeatureUnderLicense() {
		assertTrue(new Access(new TestFramework.Everlasting()).canUse("prince-to-frog")); //$NON-NLS-1$
	}

	@Test
	public void forbidKnownFeatureUnderExpiredLicense() {
		assertFalse(new Access(new TestFramework.Expired()).canUse("prince-to-frog")); //$NON-NLS-1$
	}

	@Test
	public void forbidKnownFeatureUnderNotStartedLicense() {
		assertFalse(new Access(new TestFramework.NotStarted()).canUse("prince-to-frog")); //$NON-NLS-1$
	}

	@Test
	public void forbidKnownFeatureErrorRestrictedWithoutLicense() {
		assertFalse(new Access(new TestFramework.Expired()).canUse("frog-firework")); //$NON-NLS-1$
	}

	@Test
	public void allowKnownFeatureInfoRestrictedWithoutLicense() {
		assertTrue(new Access(new TestFramework.Expired()).canUse("frog-to-prince")); //$NON-NLS-1$
	}

}
