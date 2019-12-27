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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeNoException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.passage.loc.yars.internal.api.ReportException;
import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

// FIXME test all sort of expected failures
// FIXME integration-test over emf-based user registry for encoding purposes

@SuppressWarnings("restriction")
public class ExportCustomersCommandTest {

	private Path output;

	public void readContext() {
		Bundle bundle = FrameworkUtil.getBundle(ExportCustomersCommand.class);
		BundleContext context = bundle.getBundleContext();
		ServiceReference<ExportCustomerForProductsCommand> commandReference = context
				.getServiceReference(ExportCustomerForProductsCommand.class);
		ExportCustomerForProductsCommand command = context.getService(commandReference);
		assertNotNull(command);
	}

	@Before
	public void setUp() {
		try {
			output = Files.createTempFile("test_", ".csv"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (IOException e) {
			assumeNoException("Failed to create temp file for test", e); //$NON-NLS-1$
		}
	}

	@Test
	public void tesCsvExport() {
		try {
			exportCommand().execute(fakeProducts(), output);
		} catch (ReportException e) {
			fail("Export failed"); //$NON-NLS-1$
		}
		assertOutputLooksAsExpected();
	}

	private ExportCustomerForProductsCommand exportCommand() {
		ExportCustomersCommand command = new ExportCustomersCommand();
		command.installCustomers(new FakeCustomersBase());
		return command;
	}

	private Set<String> fakeProducts() {
		return new HashSet<>(Arrays.asList("culture")); //$NON-NLS-1$
	}

	private void assertOutputLooksAsExpected() {
		Set<String> result;
		try {
			result = new HashSet<>(Files.lines(output).collect(Collectors.toSet()));
		} catch (IOException e) {
			fail("Output is broken"); //$NON-NLS-1$
			return;
		}

		Set<String> expectation = new HashSet<>(Arrays.asList(//
				"email,name", //$NON-NLS-1$
				"erwin.schrodinger@gmail.com,Erwin Rudolf Josef Alexander Schrödinger", //$NON-NLS-1$
				"football-asia-cup-2007@gmail.com,오범석 呉範錫", //$NON-NLS-1$
				"lomonosov_1711@yandex.com,Михайло Васильевич Ломоносов", //$NON-NLS-1$
				"reiner.maria.rilke@gmail.com,René Karl Wilhelm Johann Josef Maria Rilke")); //$NON-NLS-1$
		assertEquals(expectation, result);
	}

}
