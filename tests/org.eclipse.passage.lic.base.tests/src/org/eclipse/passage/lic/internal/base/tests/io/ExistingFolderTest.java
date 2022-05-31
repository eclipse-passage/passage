/*******************************************************************************
 * Copyright (c) 2022 ArSysOp
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
package org.eclipse.passage.lic.internal.base.tests.io;

import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeFalse;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.passage.lic.base.io.ExistingFolder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public final class ExistingFolderTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void createsIfDoesNotExist() {
		Supplier<Path> faulty = notExistingFolder();
		assumeFalse(Files.exists(faulty.get()));
		assertTrue(Files.exists(new ExistingFolder(faulty).get()));
	}

	private Supplier<Path> notExistingFolder() {
		return new NotExistingFolder(folder.getRoot().toPath());
	}

}
