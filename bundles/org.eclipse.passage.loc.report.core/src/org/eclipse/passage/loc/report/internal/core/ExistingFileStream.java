/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
 * <p>
 * Built over a {@linkplain Path} on a local file system, does one's best to
 * build a {@code UTF-8}-encoded output stream on top of the corresponding file.
 * </p>
 * <ul>
 * <li>If the file does not exist - it will be created</li>
 * <li>If the given {@code path} appears to point at an existing directory -
 * {@code ReportException} is thrown</li>
 * <li>{@code ReportException} is fired in the case of any denial from OS (file
 * creation, stream construction, whatever)</li>
 * </ul>
 * 
 * @since 0.1
 */
@SuppressWarnings("restriction")
public final class ExistingFileStream {

	private final Path path;

	/**
	 * Given {@code path} to be used to build an output stream on top of it.
	 * 
	 * @since 0.1
	 */
	public ExistingFileStream(Path path) {
		this.path = path;
	}

	/**
	 * Get a {@code UTF-8}-encoded output stream from a file where {@code path}
	 * points
	 * 
	 * @since 0.1
	 */
	Appendable stream() throws ReportException {
		try {
			return new OutputStreamWriter(new FileOutputStream(file()), StandardCharsets.UTF_8); // TODO: set encoding
		} catch (FileNotFoundException e) {
			throw new ReportException(String.format("file %s appears to be not-existing all of a sudden!", path), null); //$NON-NLS-1$
		}
	}

	private File file() throws ReportException {
		if (Files.notExists(path)) {
			return create().toFile();
		} else if (Files.isDirectory(path)) {
			throw new ReportException(
					String.format("path %s points to a directory, but we expect a file to export to", path), null); //$NON-NLS-1$
		}
		return path.toFile();
	}

	private Path create() throws ReportException {
		try {
			return Files.createFile(path);
		} catch (IOException e) {
			throw new ReportException(String.format("failed to create file %s", path), e); //$NON-NLS-1$
		}
	}

}
