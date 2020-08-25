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
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;

import org.eclipse.passage.lbc.base.tests.LbcTestsBase;
import org.eclipse.passage.lbc.internal.api.persistence.PersistableLicense;
import org.eclipse.passage.lbc.internal.base.persistence.LockFile;
import org.eclipse.passage.lbc.internal.base.persistence.LockFolder;
import org.eclipse.passage.lbc.json.JsonPersistableLicense;
import org.junit.Test;

public class LicensePersistenceTest extends LbcTestsBase {

	@Test
	public void persistence() {
		JsonPersistableLicense persistable = new JsonPersistableLicense(boundLicense());
		initTempFiles();
		try {
			persistable.save();
			PersistableLicense loaded = JsonPersistableLicense.load(condition());
			clearFileSystem();
			assertEquals(persistable.get().identifier().get().get(), loaded.get().identifier().get().get());
			assertEquals(persistable.get().taken().get().get(), loaded.get().taken().get().get());
			assertEquals(persistable.get().capacity().get().get(), loaded.get().capacity().get().get());
		} catch (IOException e) {
			clearFileSystem();
			fail();
		}
	}

	private void initTempFiles() {
		try {
			Files.createDirectories(new LockFolder().get());
			Files.createFile(new LockFile(boundLicense()).get());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void clearFileSystem() {
		try {
			Files.deleteIfExists(new LockFile(boundLicense()).get());
			Files.deleteIfExists(new LockFolder().get());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
