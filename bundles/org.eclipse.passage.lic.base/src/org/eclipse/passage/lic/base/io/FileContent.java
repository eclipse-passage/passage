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
import java.util.Objects;

/**
 * @since 2.1
 */
public final class FileContent {

	private final Path file;

	public FileContent(Path file) {
		Objects.requireNonNull(file, "FileContent::file"); //$NON-NLS-1$
		this.file = file;
	}

	public byte[] get() throws IOException {
		return Files.readAllBytes(file);
	}
}
