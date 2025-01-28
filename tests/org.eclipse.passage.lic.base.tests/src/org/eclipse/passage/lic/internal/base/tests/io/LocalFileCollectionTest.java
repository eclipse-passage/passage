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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNoException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.base.io.CollectedFiles;
import org.eclipse.passage.lic.base.io.PassageFileExtension;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public abstract class LocalFileCollectionTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public final void findsMatchingFiles() throws LicensingException {
		Collection<Path> findings = //
				service(this::emulated, new PassageFileExtension.LicenseEncrypted()).get();
		assertEquals(2, findings.size());
		assertTrue(findings.stream()//
				.map(Path::getFileName)//
				.map(Object::toString)//
				.allMatch(name -> name.contains("hunted"))); //$NON-NLS-1$
	}

	@Test(expected = NullPointerException.class)
	public final void pathSupplierIsMandatory() {
		service(null, new PassageFileExtension.PublicKey());
	}

	@Test(expected = NullPointerException.class)
	public final void extensionIsMandatory() {
		service(folder.getRoot()::toPath, null);
	}

	@Test(expected = NullPointerException.class)
	public final void pathIsMandatoryOnAction() throws LicensingException {
		service(() -> null, new PassageFileExtension.PublicKey()).get();
	}

	@Test
	public final void canTraverseSingleFile() throws LicensingException {
		PassageFileExtension.LicenseEncrypted extension = new PassageFileExtension.LicenseEncrypted();
		Collection<Path> single = service(this::single, extension).get();
		assertEquals(1, single.size());
		assertEquals(//
				"single" + extension.get(), //$NON-NLS-1$
				single.iterator().next().getFileName().toString());
	}

	@Test
	public final void traverseOnlyDownTheScope() throws LicensingException {
		assertTrue(service(this::outOfScope, new PassageFileExtension.LicenseEncrypted()).get().isEmpty());
	}

	protected final CollectedFiles service(Supplier<Path> base, PassageFileExtension extension) {
		Supplier<Path> supplier = base == null ? null : () -> base.get(); // now idempotent
		return instance(supplier, extension);
	}

	protected abstract CollectedFiles instance(Supplier<Path> base, PassageFileExtension extension);

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

	private Path single() {
		try {
			String path = "single" + new PassageFileExtension.LicenseEncrypted().get(); //$NON-NLS-1$
			return folder.newFile(path).toPath();
		} catch (IOException e) {
			assumeNoException(e);
			return folder.getRoot().toPath(); // unreachable
		}
	}

	private Path outOfScope() {
		PassageFileExtension hunted = new PassageFileExtension.LicenseEncrypted();
		try {
			folder.newFolder("out-of-scope"); //$NON-NLS-1$
			folder.newFile(//
					Paths.get("out-of-scope") //$NON-NLS-1$
							.resolve("not-to-be-found" + hunted.get()) //$NON-NLS-1$
							.toString());
			return folder.newFolder("scope").toPath(); //$NON-NLS-1$
		} catch (IOException e) {
			assumeNoException(e);
			return folder.getRoot().toPath(); // unreachable
		}
	}

}
