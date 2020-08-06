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

import org.eclipse.passage.lbc.api.BackendConditionDispatcher;
import org.eclipse.passage.lbc.api.LicenseAlreadyTakenException;
import org.eclipse.passage.lbc.api.NoSuchTakenLicenseException;
import org.eclipse.passage.lbc.base.BaseConditionDispatcher;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.hamcrest.Condition;
import org.junit.Test;

public class ConditionLockerTest {

	private final BackendConditionDispatcher conditionDispatcher = new BaseConditionDispatcher();

	@Test
	public void takeOnce() {
		try {
			conditionDispatcher.take(license("origin")); //$NON-NLS-1$
		} catch (LicenseAlreadyTakenException e) {
			fail();
		}
	}

	@Test
	public void takeTwice() {
		assertThrows(LicenseAlreadyTakenException.class, () -> {
			conditionDispatcher.take(license("origin")); //$NON-NLS-1$
			conditionDispatcher.take(license("origin")); //$NON-NLS-1$
		});
	}

	@Test
	public void releaseExisting() {
		ConditionPack license = license("origin"); //$NON-NLS-1$
		try {
			conditionDispatcher.take(license);
		} catch (LicenseAlreadyTakenException e) {
			fail();
		}
		try {
			conditionDispatcher.release(license);
		} catch (NoSuchTakenLicenseException e) {
			fail();
		}
	}

	@Test
	public void releaseNonExisting() {
		assertThrows(NoSuchTakenLicenseException.class, () -> {
			conditionDispatcher.release(license("origin")); //$NON-NLS-1$
		});
	}

	private ConditionPack license(String origin, Condition... conditions) {
		return new License(origin, new LinkedList<>());
	}

}
