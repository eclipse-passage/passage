/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.report.internal.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.passage.loc.yars.internal.api.ReportException;

/**
 * FIXME doc
 * 
 * @since 0.1
 */
@SuppressWarnings("restriction")
final class ExistingFileStream {

	private final Path path;

	ExistingFileStream(Path path) {
		this.path = path;
	}

	File file() throws ReportException {
		if (Files.notExists(path)) {
			return create().toFile();
		} else if (Files.isDirectory(path)) {
			throw new ReportException(
					String.format("path %s points to a directory, but we expect a file to export to", path), null); //$NON-NLS-1$
		}
		return path.toFile();
	}

	Appendable stream() throws ReportException {
		try {
			return new OutputStreamWriter(new FileOutputStream(file()), StandardCharsets.UTF_8); // TODO: set encoding
		} catch (FileNotFoundException e) {
			throw new ReportException(String.format("file %s appears to be not-existing all of a sudden!", path), null); //$NON-NLS-1$
		}
	}

	private Path create() throws ReportException {
		try {
			return Files.createFile(path);
		} catch (IOException e) {
			throw new ReportException(String.format("failed to create file %s", path), e); //$NON-NLS-1$
		}
	}

}
