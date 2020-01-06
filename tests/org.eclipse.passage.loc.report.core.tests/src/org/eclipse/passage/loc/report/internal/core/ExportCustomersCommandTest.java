/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeNoException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.eclipse.passage.loc.yars.internal.api.ReportException;
import org.junit.Test;

/**
 * plain unit test
 */
@SuppressWarnings("restriction")
public class ExportCustomersCommandTest {

	@Test
	public void tesCsvExport() {
		Path output = outputFile(""); //$NON-NLS-1$
		exportSilent(output);
		assertOutputLooksAsExpected(output);
	}

	@Test(expected = ReportException.class)
	public void testFailOnDirOutput() throws ReportException {
		export(outputDir()); // failure is expected here
	}

	/**
	 * Try to write to a file that cannot be created: most of practically used file
	 * systems forbid file names longer than 255 symbols
	 */
	@Test(expected = ReportException.class)
	public void testFailOnFileCannotBeCreated() throws ReportException {
		String prefix = IntStream.range(0, 255)//
				.mapToObj(i -> "x") //$NON-NLS-1$
				.collect(Collectors.joining());
		export(outputFile(prefix)); // failure is expected here
	}

	private Path outputFile(String prefix) {
		try {
			return Files.createTempFile("test_" + prefix, ".csv"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (IOException e) {
			assumeNoException("Failed to create temp file for test", e); //$NON-NLS-1$
			return null;
		}
	}

	private Path outputDir() {
		try {
			return Files.createTempDirectory("test_"); //$NON-NLS-1$
		} catch (IOException e) {
			assumeNoException("Failed to create temp directory for test", e); //$NON-NLS-1$
			return null;
		}
	}

	private void exportSilent(Path output) {
		try {
			export(output);
		} catch (ReportException e) {
			fail("Export failed"); //$NON-NLS-1$
		}
	}

	private void export(Path output) throws ReportException {
		new ProductCustomersToCsv(new FakeCustomersBase())//
				.export(fakeProducts(), output);
	}

	private Set<String> fakeProducts() {
		return new HashSet<>(Arrays.asList("culture")); //$NON-NLS-1$
	}

	private void assertOutputLooksAsExpected(Path output) {
		Set<String> expectation = new HashSet<>(Arrays.asList(//
				"email,name", //$NON-NLS-1$
				"erwin.schrodinger@gmail.com,Erwin Rudolf Josef Alexander Schrödinger", //$NON-NLS-1$
				"football-asia-cup-2007@gmail.com,오범석 呉範錫", //$NON-NLS-1$
				"lomonosov_1711@yandex.com,Михайло Васильевич Ломоносов", //$NON-NLS-1$
				"reiner.maria.rilke@gmail.com,René Karl Wilhelm Johann Josef Maria Rilke")); //$NON-NLS-1$
		assertEquals(expectation, results(output));
	}

	private Set<String> results(Path output) {
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

}
