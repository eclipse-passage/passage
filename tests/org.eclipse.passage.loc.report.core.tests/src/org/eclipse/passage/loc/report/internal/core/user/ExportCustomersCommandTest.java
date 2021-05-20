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
package org.eclipse.passage.loc.report.internal.core.user;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.passage.loc.report.internal.core.ExportCommandTest;
import org.eclipse.passage.loc.yars.internal.api.Progress;
import org.eclipse.passage.loc.yars.internal.api.ReportException;

/**
 * plain unit test
 */
@SuppressWarnings("restriction")
public class ExportCustomersCommandTest extends ExportCommandTest<TestCustomers> {

	@Override
	protected TestCustomers some() {
		return new TestCustomers.Some();
	}

	@Override
	protected TestCustomers none() {
		return new TestCustomers.Empty();
	}

	@Override
	protected void export(TestCustomers data, Path output) throws ReportException {
		new ProductCustomersToCsv(data.storage())//
				.export( //
						products(), //
						output, //
						new Progress.Inane<ProductCustomer>()//
				);
	}

	private Set<String> products() {
		return new HashSet<>(Arrays.asList("culture")); //$NON-NLS-1$
	}

}
