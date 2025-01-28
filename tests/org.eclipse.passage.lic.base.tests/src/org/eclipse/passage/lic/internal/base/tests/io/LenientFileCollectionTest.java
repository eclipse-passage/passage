/*******************************************************************************
 * Copyright (c) 2025 ArSysOp
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

import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.base.io.CollectedFiles;
import org.eclipse.passage.lic.base.io.LenientFileCollection;
import org.eclipse.passage.lic.base.io.PassageFileExtension;
import org.junit.Test;

public final class LenientFileCollectionTest extends LocalFileCollectionTest {

	@Test
	public final void tolerateAbsentFolder() throws LicensingException {
		assertTrue(service(//
				new NotExistingFolder(folder.getRoot().toPath()), //
				new PassageFileExtension.PublicKey()//
		).get().isEmpty());
	}

	@Override
	protected CollectedFiles instance(Supplier<Path> base, PassageFileExtension extension) {
		return new LenientFileCollection(base, extension);
	}

}
