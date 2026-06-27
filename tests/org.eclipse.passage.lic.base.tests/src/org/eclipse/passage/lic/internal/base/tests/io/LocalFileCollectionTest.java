/*******************************************************************************
 * Copyright (c) 2025, 2026 ArSysOp
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.base.io.CollectedFiles;
import org.eclipse.passage.lic.base.io.PassageFileExtension;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public abstract class LocalFileCollectionTest {

	@TempDir
	public File folder;

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

	@Test
	public final void pathSupplierIsMandatory() {
		assertThrows(NullPointerException.class, () -> service(null, new PassageFileExtension.PublicKey()));
	}

	@Test
	public final void extensionIsMandatory() {
		assertThrows(NullPointerException.class, () -> service(folder::toPath, null));
	}

	@Test
	public final void pathIsMandatoryOnAction() throws LicensingException {
		assertThrows(NullPointerException.class, () -> service(() -> null, new PassageFileExtension.PublicKey()).get());
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
			new File(folder, "inner").mkdir(); //$NON-NLS-1$
			new File(folder, //
					Paths.get("inner") //$NON-NLS-1$
							.resolve("hunted_inner" + hunted.get()) //$NON-NLS-1$
							.toString())
					.createNewFile();
			new File(folder, //
					Paths.get("inner") //$NON-NLS-1$
							.resolve("foreign_inner" + foreign) //$NON-NLS-1$
							.toString())
					.createNewFile();
			new File(folder, "hunted" + hunted.get()).createNewFile(); //$NON-NLS-1$
			new File(folder, "foreign" + foreign).createNewFile(); //$NON-NLS-1$
		} catch (IOException e) {
			Assumptions.abort(e.getMessage());
		}

		return folder.toPath();
	}

	private Path single() {
		try {
			String path = "single" + new PassageFileExtension.LicenseEncrypted().get(); //$NON-NLS-1$
			return Files.createFile(new File(folder, path).toPath());
		} catch (IOException e) {
			Assumptions.abort(e.getMessage());
			return folder.toPath(); // unreachable
		}
	}

	private Path outOfScope() {
		PassageFileExtension hunted = new PassageFileExtension.LicenseEncrypted();
		try {
			new File(folder, "out-of-scope").mkdir(); //$NON-NLS-1$
			new File(folder, //
					Paths.get("out-of-scope") //$NON-NLS-1$
							.resolve("not-to-be-found" + hunted.get()) //$NON-NLS-1$
							.toString())
					.createNewFile();
			return Files.createDirectory(new File(folder, "scope").toPath()); //$NON-NLS-1$
		} catch (IOException e) {
			Assumptions.abort(e.getMessage());
			return folder.toPath(); // unreachable
		}
	}

}
