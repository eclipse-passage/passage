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
package org.eclipse.passage.lbc.internal.base.persistence.tests;

import static org.junit.Assert.assertEquals;

import org.eclipse.passage.lbc.base.tests.LbcTestsBase;
import org.eclipse.passage.lbc.internal.base.persistence.LockFile;
import org.eclipse.passage.lbc.internal.base.persistence.LockFolder;
import org.eclipse.passage.lic.internal.base.io.LicensingFolder;
import org.eclipse.passage.lic.internal.base.io.UserHomePath;
import org.junit.Test;

@SuppressWarnings("restriction")
public class PathsTest extends LbcTestsBase {

	@Test
	public void folderDefault() {
		assertEquals(new LicensingFolder(new UserHomePath()).get().resolve("locked"), new LockFolder().get()); //$NON-NLS-1$
	}

	@Test
	public void fileOfConditionDefault() {
		assertEquals(new LicensingFolder(new UserHomePath()).get().resolve("locked").resolve(condition().identifier()), //$NON-NLS-1$
				new LockFile(condition()).get());
	}

	@Test
	public void fileOfBoundLicenseDefault() {
		assertEquals(new LicensingFolder(new UserHomePath()).get().resolve("locked") //$NON-NLS-1$
				.resolve(boundLicense().identifier().get().get()), new LockFile(boundLicense()).get());
	}

}
