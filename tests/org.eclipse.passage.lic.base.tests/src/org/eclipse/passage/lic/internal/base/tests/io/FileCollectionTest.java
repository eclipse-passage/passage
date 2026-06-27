/*******************************************************************************
 * Copyright (c) 2020, 2026 ArSysOp
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
package org.eclipse.passage.lic.internal.base.tests.io;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.base.io.CollectedFiles;
import org.eclipse.passage.lic.base.io.FileCollection;
import org.eclipse.passage.lic.base.io.PassageFileExtension;
import org.junit.jupiter.api.Test;

public final class FileCollectionTest extends LocalFileCollectionTest {

	@Test
	public final void failsToTraverseAbsentPath() throws LicensingException {
		assertThrows(LicensingException.class, () -> service(//
				new NotExistingFolder(folder.toPath()), //
				new PassageFileExtension.PublicKey()//
		).get());
	}

	@Override
	protected CollectedFiles instance(Supplier<Path> base, PassageFileExtension extension) {
		return new FileCollection(base, extension);
	}

}
