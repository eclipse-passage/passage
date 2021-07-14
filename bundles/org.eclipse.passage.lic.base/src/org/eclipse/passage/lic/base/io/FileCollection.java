/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.base.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.base.i18n.BaseMessages;

/**
 * @since 1.1
 */
public final class FileCollection {

	private final Supplier<Path> base;
	private final PassageFileExtension extensions;

	public FileCollection(Supplier<Path> base, PassageFileExtension extension) {
		Objects.requireNonNull(base, "FileCollection::base path"); //$NON-NLS-1$
		Objects.requireNonNull(extension, "FileCollection::extension"); //$NON-NLS-1$
		this.base = base;
		this.extensions = extension;
	}

	public Collection<Path> get() throws LicensingException {
		HunterFiles hunter = new HunterFiles(extensions);
		try {
			Files.walkFileTree(base.get(), hunter);
		} catch (IOException e) {
			new LicensingException(BaseMessages.getString("FileCollection.failure"), e); //$NON-NLS-1$
		}
		return hunter.findings();
	}

	private static final class HunterFiles extends SimpleFileVisitor<Path> {
		private final List<Path> findings = new ArrayList<>();
		private final PassageFileExtension extension;

		HunterFiles(PassageFileExtension extension) {
			this.extension = extension;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			if (file.getFileName().toString().endsWith(extension.get())) {
				findings.add(file);
			}
			return FileVisitResult.CONTINUE;
		}

		Collection<Path> findings() {
			return findings;
		}

	}
}
