/*******************************************************************************
 * Copyright (c) 2020, 2022 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     Hannes Wellmann (IILS mbH) - Simplify IO operations(#1071)
 *******************************************************************************/
package org.eclipse.passage.lic.base.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.internal.base.i18n.BaseMessages;

/**
 * Collects regular files of the given {@code extension} starting from the given
 * {@code base} path recursively. No particular order is guaranteed.
 * 
 * @since 2.1
 */
public final class FileCollection {

	private final Supplier<Path> base;
	private final PassageFileExtension extension;

	/**
	 * @param base expected to supply path to an existing directory
	 */
	public FileCollection(Supplier<Path> base, PassageFileExtension extension) {
		Objects.requireNonNull(base, "FileCollection::base path"); //$NON-NLS-1$
		Objects.requireNonNull(extension, "FileCollection::extension"); //$NON-NLS-1$
		this.base = base;
		this.extension = extension;
	}

	public Collection<Path> get() throws LicensingException {
		try (Stream<Path> all = files(base.get())) {
			return filtered(all);
		} catch (IOException e) {
			throw new LicensingException(
					String.format(BaseMessages.getString("FileCollection.failure"), extension.get(), base.get()), //$NON-NLS-1$
					e);
		}
	}

	private Stream<Path> files(Path path) throws IOException {
		return Files.walk(path) //
				.filter(Files::isRegularFile);
	}

	private List<Path> filtered(Stream<Path> files) {
		return files.filter(extension::ends) //
				.collect(Collectors.toList());
	}
}
