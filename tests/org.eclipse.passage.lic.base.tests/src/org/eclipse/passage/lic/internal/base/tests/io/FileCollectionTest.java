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
package org.eclipse.passage.lic.internal.base.tests.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNoException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.base.io.FileCollection;
import org.eclipse.passage.lic.internal.base.io.PassageFileExtension;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

@SuppressWarnings("restriction")
public final class FileCollectionTest {
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void findsMatchingFiles() throws LicensingException {
		Collection<Path> findings = //
				new FileCollection(this::emulated, new PassageFileExtension.LicenseEncrypted()).get();
		assertEquals(2, findings.size());
		assertTrue(findings.stream()//
				.map(Path::getFileName)//
				.map(Object::toString)//
				.allMatch(name -> name.contains("hunted"))); //$NON-NLS-1$
	}

	@Test(expected = NullPointerException.class)
	public void pathSupplierIsMandatory() {
		new FileCollection(null, new PassageFileExtension.PublicKey());
	}

	@Test(expected = NullPointerException.class)
	public void extensionIsMandatory() {
		new FileCollection(folder.getRoot()::toPath, null);
	}

	@Test(expected = NullPointerException.class)
	public void pushIsMandatoryOnAction() throws LicensingException {
		new FileCollection(() -> null, new PassageFileExtension.PublicKey()).get();

	}

	private Path emulated() {
		PassageFileExtension hunted = new PassageFileExtension.LicenseEncrypted();
		String foreign = ".txt"; //$NON-NLS-1$
		try {
			folder.newFolder("inner"); //$NON-NLS-1$
			folder.newFile(//
					Paths.get("inner") //$NON-NLS-1$
							.resolve("hunted_inner" + hunted.get()) //$NON-NLS-1$
							.toString());
			folder.newFile(//
					Paths.get("inner") //$NON-NLS-1$
							.resolve("foreign_inner" + foreign) //$NON-NLS-1$
							.toString());
			folder.newFile("hunted" + hunted.get()); //$NON-NLS-1$
			folder.newFile("foreign" + foreign); //$NON-NLS-1$
		} catch (IOException e) {
			assumeNoException(e);
		}

		return folder.getRoot().toPath();
	}
}
