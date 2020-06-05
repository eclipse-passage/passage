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
package org.eclipse.passage.loc.report.internal.core;

import static org.junit.Assume.assumeNoException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.eclipse.passage.loc.yars.internal.api.ReportException;
import org.junit.Test;

/**
 * <p>
 * Supplies two failure tests for each export operation. The one should fail if
 * </p>
 * <ul>
 * <li>a target output file creation is denied by OS and</li>
 * <li>if the target path is not a file</li>
 * </ul>
 * 
 * @param <T> domain-specific subclass of {@linkplain TestData}
 */
@SuppressWarnings("restriction")
public abstract class ExportCommandFailureTest<T extends TestData<?>> extends BaseExportCommandTest<T> {

	/**
	 * Try to write to a file that cannot be created: most of practically used file
	 * systems forbid file names longer than 255 symbols
	 */
	@Test(expected = ReportException.class)
	public void testFailOnFileCannotBeCreated() throws ReportException {
		String name = IntStream.range(0, 255)//
				.mapToObj(i -> "x") //$NON-NLS-1$
				.collect(Collectors.joining()) //
				+ ".txt"; //$NON-NLS-1$
		export(data(), outputDir().resolve(name)); // failure is expected here
	}

	/**
	 * Target path should point to a file, not to a directory. Failure is expected
	 * otherwise.
	 */
	@Test(expected = ReportException.class)
	public void testFailOnDirOutput() throws ReportException {
		export(data(), outputDir()); // failure is expected here
	}

	protected final Path outputDir() {
		try {
			return Files.createTempDirectory("test_"); //$NON-NLS-1$
		} catch (IOException e) {
			assumeNoException("Failed to create temp directory for test", e); //$NON-NLS-1$
			return null;
		}
	}

	protected abstract T data();

}
