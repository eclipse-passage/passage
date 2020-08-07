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
package org.eclipse.passage.lbc.base.tests;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import java.util.LinkedList;

import org.eclipse.passage.lbc.internal.api.BackendLicenseLock;
import org.eclipse.passage.lbc.internal.api.LicenseAlreadyLockedException;
import org.eclipse.passage.lbc.internal.api.LicenseNotLockedException;
import org.eclipse.passage.lbc.internal.base.BaseLicenseLock;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.junit.Test;

public class LicenseLockTest {

	private final BackendLicenseLock conditionDispatcher = new BaseLicenseLock();

	@Test
	public void takeOnce() {
		try {
			conditionDispatcher.lock(license("origin")); //$NON-NLS-1$
		} catch (LicenseAlreadyLockedException e) {
			fail();
		}
	}

	@Test
	public void takeTwice() {
		assertThrows(LicenseAlreadyLockedException.class, () -> {
			conditionDispatcher.lock(license("origin")); //$NON-NLS-1$
			conditionDispatcher.lock(license("origin")); //$NON-NLS-1$
		});
	}

	@Test
	public void releaseExisting() {
		ConditionPack license = license("origin"); //$NON-NLS-1$
		try {
			conditionDispatcher.lock(license);
		} catch (LicenseAlreadyLockedException e) {
			fail();
		}
		try {
			conditionDispatcher.release(license);
		} catch (LicenseNotLockedException e) {
			fail();
		}
	}

	@Test
	public void releaseNonExisting() {
		assertThrows(LicenseNotLockedException.class, () -> {
			conditionDispatcher.release(license("origin")); //$NON-NLS-1$
		});
	}

	// In this we only need a way to determine what license is it, and so there is
	// no need to work with Conditions yet.
	private ConditionPack license(String origin) {
		return new FakeLicense(origin, new LinkedList<>());
	}

}
