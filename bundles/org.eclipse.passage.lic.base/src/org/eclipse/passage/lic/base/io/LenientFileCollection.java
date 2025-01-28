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
package org.eclipse.passage.lic.base.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensingException;

/**
 * <p>
 * Tolerant to base directory or file absence. Designed to return empty
 * collection in this case.
 * </p>
 * 
 * <p>
 * Otherwise functionally identical to {@linkplain FileCollection}. Literally.
 * Meaning if the {@code base} is a directory without <i>execute</i> permission
 * for a current user, or operating system denies the traversal due to any other
 * reason, the collecting process will actively fail.
 * </p>
 * 
 * <p>
 * These is no obligations as to how many times {@code base} supplier to be
 * called, so make sure your implementation is system-wide idempotent.
 * </p>
 * 
 * @since 4.1
 */
public final class LenientFileCollection implements CollectedFiles {

	private final Supplier<Path> base;
	private final PassageFileExtension extension;

	/**
	 * @param base is not suppose to return {@code null}, but can supply a path that
	 *             has no correspondence on the local file system.
	 */
	public LenientFileCollection(Supplier<Path> base, PassageFileExtension extension) {
		this.base = Objects.requireNonNull(base, "LenientFileCollection::base path"); //$NON-NLS-1$
		this.extension = Objects.requireNonNull(extension, "LenientFileCollection::extension"); //$NON-NLS-1$
	}

	@Override
	public Collection<Path> get() throws LicensingException {
		if (baseIsOk()) {
			return new FileCollection(base, extension).get();
		}
		return Collections.emptyList();
	}

	private boolean baseIsOk() {
		return Files.exists(base.get());
	}
}
