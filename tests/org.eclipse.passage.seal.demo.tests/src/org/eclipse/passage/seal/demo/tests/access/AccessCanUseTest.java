/*******************************************************************************
 * Copyright (c) 2020, 2025 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support and improvements
 *******************************************************************************/
package org.eclipse.passage.seal.demo.tests.access;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lic.base.BaseFeatureIdentifier;
import org.eclipse.passage.lic.internal.base.access.Access;
import org.junit.Test;

/**
 * Integration test: demands OSGi running
 */
@SuppressWarnings("restriction")
public final class AccessCanUseTest {

	@Test
	public void forbidUnknownFeature() {
		assertFalse(new Access(new TestFramework.Expired()).canUse(new BaseFeatureIdentifier("unkonwn-feature"))); //$NON-NLS-1$
	}

	@Test
	public void allowKnownFeatureUnderLicense() {
		assertTrue(new Access(new TestFramework.Everlasting()).canUse(new BaseFeatureIdentifier("prince-to-frog"))); //$NON-NLS-1$
	}

	@Test
	public void forbidKnownFeatureUnderExpiredLicense() {
		assertFalse(new Access(new TestFramework.Expired()).canUse(new BaseFeatureIdentifier("prince-to-frog"))); //$NON-NLS-1$
	}

	@Test
	public void forbidKnownFeatureUnderNotStartedLicense() {
		assertFalse(new Access(new TestFramework.NotStarted()).canUse(new BaseFeatureIdentifier("prince-to-frog"))); //$NON-NLS-1$
	}

	@Test
	public void forbidKnownFeatureErrorRestrictedWithoutLicense() {
		assertFalse(new Access(new TestFramework.Expired()).canUse(new BaseFeatureIdentifier("frog-firework"))); //$NON-NLS-1$
	}

	@Test
	public void allowKnownFeatureInfoRestrictedWithoutLicense() {
		assertTrue(new Access(new TestFramework.Expired()).canUse(new BaseFeatureIdentifier("frog-to-prince"))); //$NON-NLS-1$
	}

}
