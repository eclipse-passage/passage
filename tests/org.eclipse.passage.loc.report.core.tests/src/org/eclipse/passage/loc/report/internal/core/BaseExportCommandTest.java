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

import static org.junit.Assert.fail;
import static org.junit.Assume.assumeNoException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.passage.loc.yars.internal.api.ReportException;

@SuppressWarnings("restriction")
public abstract class BaseExportCommandTest<T extends TestData<?>> {

	protected final Path outputFile(String prefix) {
		try {
			return Files.createTempFile("test_" + prefix, ".csv"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (IOException e) {
			assumeNoException("Failed to create temp file for test", e); //$NON-NLS-1$
			return null;
		}
	}

	protected final void exportSilent(T data, Path output) {
		try {
			export(data, output);
		} catch (ReportException e) {
			fail("Export failed"); //$NON-NLS-1$
		}
	}

	protected final Set<String> results(Path output) {
		try {
			return new HashSet<>(//
					Files.lines(output, StandardCharsets.UTF_8)//
							.collect(Collectors.toSet()));
		} catch (Throwable e) {
			e.printStackTrace();
			fail("Output is broken"); //$NON-NLS-1$
			return null;
		}
	}

	protected abstract void export(T data, Path output) throws ReportException;

}
